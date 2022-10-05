package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.models.vehicles.calls.UnlockDockedVehicleCall;
import be.kdg.sa.velo.repositories.RideRepository;
import be.kdg.sa.velo.repositories.SubscriptionRepository;
import be.kdg.sa.velo.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Jonas Leijzen
 * 2/10/2022
 */
@Service
public class RideService {
	
	private final Random random;
	private final RideRepository rideRepository;
	private final StationService stationService;
	private final SubscriptionRepository subscriptionRepository;
	private final VehicleRepository vehicleRepository;
	
	public RideService (Random random, RideRepository rideRepository, StationService stationService, SubscriptionRepository subscriptionRepository, VehicleRepository vehicleService) {
		this.random = random;
		this.rideRepository = rideRepository;
		this.stationService = stationService;
		this.subscriptionRepository = subscriptionRepository;
		this.vehicleRepository = vehicleService;
	}
	
	public int startDockedRide (UnlockDockedVehicleCall event) {
		var locks = stationService.getAvailableLocksForStation (event.getStationId ());
		var lock = locks.get (random.nextInt (locks.size ()));
		var vehicle = vehicleRepository.findById (event.getVehicleId ()).orElseThrow ();
		var subscription = subscriptionRepository.getActiveSubscriptionByUserId (event.getUserId ());
		var ride = new Ride (vehicle, lock, subscription);
		rideRepository.save (ride);
		return lock.getId ();
	}
}
