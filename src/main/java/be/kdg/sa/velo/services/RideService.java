package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import be.kdg.sa.velo.messaging.senders.InvoiceXmlSender;
import be.kdg.sa.velo.models.invoices.Invoice;
import be.kdg.sa.velo.models.vehicles.calls.*;
import be.kdg.sa.velo.repositories.LockRepository;
import be.kdg.sa.velo.repositories.RideRepository;
import be.kdg.sa.velo.repositories.VehicleRepository;
import be.kdg.sa.velo.services.prices.DockedRidePriceCalculator;
import be.kdg.sa.velo.services.prices.RidePriceCalculator;
import be.kdg.sa.velo.services.prices.UndockedRidePriceCalculator;
import be.kdg.sa.velo.utils.RideUtils;
import be.kdg.sa.velo.utils.VehicleLocationUtils;
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
	private final InvoiceXmlSender invoiceXmlSender;
	
	@Autowired
	public RideService (Random random, StationService stationService, SubscriptionService subscriptionService, RideRepository rideRepository, VehicleRepository vehicleRepository, LockRepository lockRepository, InvoiceXmlSender invoiceXmlSender) {
		this.random = random;
		this.stationService = stationService;
		this.subscriptionService = subscriptionService;
		this.rideRepository = rideRepository;
		this.vehicleRepository = vehicleRepository;
		this.lockRepository = lockRepository;
		this.invoiceXmlSender = invoiceXmlSender;
	}
	
	
	// Returns the distance of a ride in kilometers
	public double getRideDistance (Ride ride) {
		List<VehicleLocation> vehicleLocations = rideRepository.getVehicleLocationsForRide (ride.getId ());
		double totalDistance = 0;
		for (int i = 0; i < vehicleLocations.size () - 1; i++) {
			 totalDistance += VehicleLocationUtils.getDistanceBetweenLocations (vehicleLocations.get (i), vehicleLocations.get (i + 1));
		}
		return totalDistance;
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
	
	public void endDockedRide (LockDockedVehicleCall event) {
		var lock = lockRepository.findById (event.getLockId ()).orElseThrow ();
		var vehicle = vehicleRepository.findById (event.getVehicleId ()).orElseThrow ();
		var ride = rideRepository.getLastRideForVehicle (vehicle.getId ()).orElseThrow ();
		ride.setEndTime (System.currentTimeMillis ());
		ride.setEndLock (lock);
		rideRepository.save (ride);
		lock.setVehicle (vehicle);
		lockRepository.save (lock);
		HandlePayment (ride, event);
	}
	
	public void endUndockedRide (LockUndockedVehicleCall event) {
		var vehicle = vehicleRepository.findById (event.getVehicleId ()).orElseThrow ();
		var ride = rideRepository.getLastRideForVehicle (vehicle.getId ()).orElseThrow ();
		ride.setEndTime (System.currentTimeMillis ());
		rideRepository.save (ride);
		HandlePayment (ride, event);
	}
	
	private void HandlePayment (Ride ride, LockVehicleCall event) {
		if (event.isDefect ())
			// TODO add vehicle to maintenance
			return;
		
		var invoice = getRideInvoice (ride);
		if (invoice == null)
			throw new RuntimeException ("Could not create invoice for ride");
		invoiceXmlSender.send (invoice);
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
