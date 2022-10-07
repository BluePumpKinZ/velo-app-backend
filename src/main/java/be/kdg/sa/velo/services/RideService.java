package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.models.vehicles.calls.UnlockDockedVehicleCall;
import be.kdg.sa.velo.models.vehicles.calls.UnlockUndockedVehicleCall;
import be.kdg.sa.velo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
