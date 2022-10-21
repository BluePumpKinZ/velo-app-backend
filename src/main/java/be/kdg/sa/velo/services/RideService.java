package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.exceptions.LockNotFoundException;
import be.kdg.sa.velo.exceptions.RideNotFoundException;
import be.kdg.sa.velo.exceptions.VehicleNotFoundException;
import be.kdg.sa.velo.messaging.senders.InvoiceXmlSender;
import be.kdg.sa.velo.models.invoices.Invoice;
import be.kdg.sa.velo.models.vehicles.calls.*;
import be.kdg.sa.velo.repositories.LockRepository;
import be.kdg.sa.velo.repositories.RideRepository;
import be.kdg.sa.velo.repositories.VehicleRepository;
import be.kdg.sa.velo.services.priceitems.PriceItem;
import be.kdg.sa.velo.utils.PointFactory;
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
	private final Collection<PriceItem> priceItems;
	
	public RideService (Random random, StationService stationService, SubscriptionService subscriptionService, RideRepository rideRepository, VehicleRepository vehicleRepository, LockRepository lockRepository, InvoiceXmlSender invoiceXmlSender, Collection<PriceItem> priceItems) {
		this.random = random;
		this.stationService = stationService;
		this.subscriptionService = subscriptionService;
		this.rideRepository = rideRepository;
		this.vehicleRepository = vehicleRepository;
		this.lockRepository = lockRepository;
		this.invoiceXmlSender = invoiceXmlSender;
		this.priceItems = priceItems;
	}
	
	public int startDockedRide (UnlockDockedVehicleCall event) {
		var locks = stationService.getFilledLocksForStation (event.getStationId ());
		var lock = locks.get (random.nextInt (locks.size ()));
		var vehicle = lock.getVehicle ();
		var subscription = subscriptionService.getCurrentUserSubscription (event.getUserId ());
		var ride = new Ride (vehicle, lock.getStation ().getGpsCoord (), lock, subscription);
		rideRepository.save (ride);
		lock.setVehicle (null);
		lockRepository.save (lock);
		return lock.getId ();
	}
	
	public void startUndockedRide (UnlockUndockedVehicleCall event) {
		var vehicle = vehicleRepository.findById (event.getVehicleId ()).orElseThrow (() -> new VehicleNotFoundException (event.getVehicleId ()));
		var subscription = subscriptionService.getCurrentUserSubscription (event.getUserId ());
		var startPoint = PointFactory.createPoint (event.getLatitude (), event.getLongitude ());
		var ride = new Ride (vehicle, startPoint, subscription);
		rideRepository.save (ride);
	}
	
	public void endDockedRide (LockDockedVehicleCall event) {
		var lock = lockRepository.findById (event.getLockId ()).orElseThrow (() -> new LockNotFoundException (event.getLockId ()));
		var vehicle = vehicleRepository.findById (event.getVehicleId ()).orElseThrow (() -> new VehicleNotFoundException (event.getVehicleId ()));
		var ride = rideRepository.getLastRideForVehicle (vehicle.getId ()).orElseThrow (() -> new RideNotFoundException (0));
		ride.setEndTime (System.currentTimeMillis ());
		ride.setEndLock (lock);
		ride.setEndPoint (lock.getStation ().getGpsCoord ());
		rideRepository.save (ride);
		lock.setVehicle (vehicle);
		lockRepository.save (lock);
		HandlePayment (ride, event);
	}
	
	public void endUndockedRide (LockUndockedVehicleCall event) {
		var vehicle = vehicleRepository.findById (event.getVehicleId ()).orElseThrow (() -> new VehicleNotFoundException (event.getVehicleId ()));
		var ride = rideRepository.getLastRideForVehicle (vehicle.getId ()).orElseThrow (() -> new RideNotFoundException (0));
		var endPoint = PointFactory.createPoint (event.getLatitude (), event.getLongitude ());
		ride.setEndTime (System.currentTimeMillis ());
		ride.setEndPoint (endPoint);
		rideRepository.save (ride);
		HandlePayment (ride, event);
	}
	
	public void HandlePayment (Ride ride, LockVehicleCall event) {
		if (event.isDefect ())
			// TODO add vehicle to maintenance
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
