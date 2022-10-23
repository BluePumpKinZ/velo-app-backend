package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import be.kdg.sa.velo.domain.vehicles.VehicleTypeEnum;
import be.kdg.sa.velo.dto.vehicles.calls.*;
import be.kdg.sa.velo.exceptions.LockNotFoundException;
import be.kdg.sa.velo.exceptions.RideNotFoundException;
import be.kdg.sa.velo.exceptions.VehicleNotFoundException;
import be.kdg.sa.velo.maintenance.qualifiers.MaintenanceQualifyContext;
import be.kdg.sa.velo.messaging.senders.InvoiceXmlSender;
import be.kdg.sa.velo.models.invoices.Invoice;
import be.kdg.sa.velo.repositories.LockRepository;
import be.kdg.sa.velo.repositories.RideRepository;
import be.kdg.sa.velo.repositories.VehicleLocationRepository;
import be.kdg.sa.velo.repositories.VehicleRepository;
import be.kdg.sa.velo.services.priceitems.PriceItem;
import be.kdg.sa.velo.utils.PointUtils;
import be.kdg.sa.velo.utils.VehicleTypeUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
	private final MaintenanceService maintenanceService;
	private final VehicleLocationRepository vehicleLocationRepository;
	private final Collection<PriceItem> priceItems;
	
	public RideService (Random random, StationService stationService, SubscriptionService subscriptionService, RideRepository rideRepository, VehicleRepository vehicleRepository, LockRepository lockRepository, InvoiceXmlSender invoiceXmlSender, MaintenanceService maintenanceService, VehicleLocationRepository vehicleLocationRepository, Collection<PriceItem> priceItems) {
		this.random = random;
		this.stationService = stationService;
		this.subscriptionService = subscriptionService;
		this.rideRepository = rideRepository;
		this.vehicleRepository = vehicleRepository;
		this.lockRepository = lockRepository;
		this.invoiceXmlSender = invoiceXmlSender;
		this.maintenanceService = maintenanceService;
		this.vehicleLocationRepository = vehicleLocationRepository;
		this.priceItems = priceItems;
	}
	
	public int startDockedRide (UnlockDockedVehicleCall event) {
		var locks = stationService.getFilledLocksForStation (event.getStationId ());
		var lock = locks.get (random.nextInt (locks.size ()));
		var vehicle = lock.getVehicle ();
		var subscription = subscriptionService.getCurrentUserSubscription (event.getUserId ());
		var location = lock.getStation ().getGpsCoord ();
		var ride = new Ride (vehicle, location, lock, subscription);
		vehicleLocationRepository.save (new VehicleLocation (vehicle, location));
		vehicle.setLocation (location);
		vehicleRepository.save (vehicle);
		rideRepository.save (ride);
		lock.setVehicle (null);
		lockRepository.save (lock);
		return lock.getId ();
	}
	
	public void startUndockedRide (UnlockUndockedVehicleCall event) {
		var vehicle = vehicleRepository.findById (event.getVehicleId ()).orElseThrow (() -> new VehicleNotFoundException (event.getVehicleId ()));
		var subscription = subscriptionService.getCurrentUserSubscription (event.getUserId ());
		var startPoint = PointUtils.createPoint (event.getLatitude (), event.getLongitude ());
		vehicleLocationRepository.save (new VehicleLocation (vehicle, startPoint));
		vehicle.setLocation (startPoint);
		vehicleRepository.save (vehicle);
		var ride = new Ride (vehicle, startPoint, subscription);
		rideRepository.save (ride);
	}
	
	public void endDockedRide (LockDockedVehicleCall event) {
		var lock = lockRepository.findById (event.getLockId ()).orElseThrow (() -> new LockNotFoundException (event.getLockId ()));
		var vehicle = vehicleRepository.findById (event.getVehicleId ()).orElseThrow (() -> new VehicleNotFoundException (event.getVehicleId ()));
		var ride = rideRepository.getLastRideForVehicle (vehicle.getId ()).orElseThrow (() -> new RideNotFoundException (0));
		var location = lock.getStation ().getGpsCoord ();
		vehicleLocationRepository.save (new VehicleLocation (vehicle, location));
		vehicle.setLocation (location);
		vehicleRepository.save (vehicle);
		ride.setEndTime (System.currentTimeMillis ());
		ride.setEndLock (lock);
		ride.setEndPoint (location);
		rideRepository.save (ride);
		lock.setVehicle (vehicle);
		lockRepository.save (lock);
		HandlePayment (ride, event);
		maintenanceService.addVehicleToMaintenanceIfRequired (new MaintenanceQualifyContext () {
			@Override public int getVehicleId () {
				return vehicle.getId ();
			}
			@Override public LockVehicleCall getEvent () {
				return event;
			}
			@Override public VehicleTypeEnum getVehicleTypeEnum () { return VehicleTypeUtils.getVehicleTypeEnum (vehicleRepository.getVehicleType ( vehicle.getId ())); }
		});
	}
	
	public void endUndockedRide (LockUndockedVehicleCall event) {
		var vehicle = vehicleRepository.findById (event.getVehicleId ()).orElseThrow (() -> new VehicleNotFoundException (event.getVehicleId ()));
		var ride = rideRepository.getLastRideForVehicle (vehicle.getId ()).orElseThrow (() -> new RideNotFoundException (0));
		var endPoint = PointUtils.createPoint (event.getLatitude (), event.getLongitude ());
		vehicleLocationRepository.save (new VehicleLocation (vehicle, endPoint));
		vehicle.setLocation (endPoint);
		vehicleRepository.save (vehicle);
		ride.setEndTime (System.currentTimeMillis ());
		ride.setEndPoint (endPoint);
		rideRepository.save (ride);
		HandlePayment (ride, event);
		maintenanceService.addVehicleToMaintenanceIfRequired (new MaintenanceQualifyContext () {
			@Override public int getVehicleId () {
				return vehicle.getId ();
			}
			@Override public LockVehicleCall getEvent () {
				return event;
			}
			@Override public VehicleTypeEnum getVehicleTypeEnum () { return VehicleTypeUtils.getVehicleTypeEnum (vehicleRepository.getVehicleType ( vehicle.getId ())); }
		});
	}
	
	public void HandlePayment (Ride ride, LockVehicleCall event) {
		if (event.isDefect ())
			return;
		
		var invoice = getRideInvoice (ride);
		if (invoice.getLineItems ().size () == 0)
			return;
		invoiceXmlSender.send (invoice);
	}
	
	private Invoice getRideInvoice (Ride ride) {
		var invoice = new Invoice ();
		priceItems.stream ()
				.filter (priceItem -> priceItem.doesApply (ride))
				.map (priceItem -> priceItem.getInvoiceLineItem (ride))
				.forEach (invoice::addLineItem);
		return invoice;
	}
	
}
