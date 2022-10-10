package be.kdg.sa.velo.services.ride;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import be.kdg.sa.velo.repositories.RideRepository;
import be.kdg.sa.velo.utils.RideUtils;
import be.kdg.sa.velo.utils.VehicleLocationUtils;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

/**
 * Jonas Leijzen
 * 10/10/2022
 */
@Component
public class RideStatisticsService implements RideDistanceCalculator, RideTimeCalculator {
	// Returns the distance of a ride in kilometers
	private final RideRepository rideRepository;
	
	public RideStatisticsService (RideRepository rideRepository) {
		this.rideRepository = rideRepository;
	}
	
	@Override
	public double getRideDistance (Ride ride) {
		List<VehicleLocation> vehicleLocations = rideRepository.getVehicleLocationsForRide (ride.getId ());
		double totalDistance = 0;
		for (int i = 0; i < vehicleLocations.size () - 1; i++) {
			totalDistance += VehicleLocationUtils.getDistanceBetweenLocations (vehicleLocations.get (i), vehicleLocations.get (i + 1));
		}
		return totalDistance;
	}
	
	@Override
	public Duration getRideDuration (Ride ride) {
		return RideUtils.getRideDuration (ride);
	}
}
