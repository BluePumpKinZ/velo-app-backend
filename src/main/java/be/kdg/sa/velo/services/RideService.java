package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import be.kdg.sa.velo.models.invoices.Invoice;
import be.kdg.sa.velo.models.vehicles.calls.UnlockDockedVehicleCall;
import be.kdg.sa.velo.models.vehicles.calls.UnlockUndockedVehicleCall;
import be.kdg.sa.velo.repositories.LockRepository;
import be.kdg.sa.velo.repositories.RideRepository;
import be.kdg.sa.velo.repositories.VehicleRepository;
import be.kdg.sa.velo.services.prices.DockedRidePriceCalculator;
import be.kdg.sa.velo.services.prices.RidePriceCalculator;
import be.kdg.sa.velo.services.prices.UndockedRidePriceCalculator;
import be.kdg.sa.velo.utils.PointFactory;
import be.kdg.sa.velo.utils.RideUtils;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.operation.distance.DistanceOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Jonas Leijzen
 * 2/10/2022
 */
@Service
public class RideService {
	
	private final Random random;
	private final StationService stationService;
	private final SubscriptionService subscriptionService;
	private final RideRepository rideRepository;
	private final VehicleRepository vehicleRepository;
	private final LockRepository lockRepository;
	
	@Autowired
	public RideService (Random random, StationService stationService, SubscriptionService subscriptionService, RideRepository rideRepository, VehicleRepository vehicleRepository, LockRepository lockRepository) {
		this.random = random;
		this.stationService = stationService;
		this.subscriptionService = subscriptionService;
		this.rideRepository = rideRepository;
		this.vehicleRepository = vehicleRepository;
		this.lockRepository = lockRepository;
	}
	
	
	// Returns the distance of a ride in kilometers
	public double getRideDistance (Ride ride) {
		List<VehicleLocation> vehicleLocations = rideRepository.getVehicleLocationsForRide (ride.getId ());
		double totalDistance = 0;
		for (int i = 0; i < vehicleLocations.size () - 1; i++) {
			totalDistance += getDistanceBetweenLocations (vehicleLocations.get (i), vehicleLocations.get (i + 1));
		}
		return totalDistance;
	}
	
	private double getDistanceBetweenLocations (VehicleLocation location1, VehicleLocation location2) {
		Point point1 = PointFactory.createPoint (location1.getLatitude (), location1.getLongitude ());
		Point point2 = PointFactory.createPoint (location2.getLatitude (), location2.getLongitude ());
		return DistanceOp.distance (point1, point2);
	}
	
	public int startDockedRide (UnlockDockedVehicleCall event) {
		var locks = stationService.getFilledLocksForStation (event.getStationId ());
		var lock = locks.get (random.nextInt (locks.size ()));
		var vehicle = lock.getVehicle ();
		var subscription = subscriptionService.getCurrentUserSubscription (event.getUserId ());
		var ride = new Ride (vehicle, lock, subscription);
		rideRepository.save (ride);
		lock.setVehicle (null);
		lockRepository.save (lock);
		return lock.getId ();
	}
	
	public void startUndockedRide (UnlockUndockedVehicleCall event) {
		var vehicle = vehicleRepository.findById (event.getVehicleId ()).orElseThrow ();
		var subscription = subscriptionService.getCurrentUserSubscription (event.getUserId ());
		var ride = new Ride (vehicle, subscription);
		rideRepository.save (ride);
	}
	
	private Invoice getRideInvoice (Ride ride) {
		var priceCalculator = getPriceRideCalculator (ride);
		if (priceCalculator == null)
			return null;
		var invoiceLineItems = priceCalculator.getInvoiceLineItems (ride);
		var invoice = new Invoice ();
		invoiceLineItems.forEach (invoice::addLineItem);
		return invoice;
	}
	
	private RidePriceCalculator getPriceRideCalculator (Ride ride) {
		return switch (RideUtils.getRideType (ride)) {
			case DOCKED -> new DockedRidePriceCalculator ();
			case UNDOCKED -> new UndockedRidePriceCalculator ();
			case MAINTENANCE -> null;
		};
	}
	
}
