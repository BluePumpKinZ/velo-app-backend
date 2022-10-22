package be.kdg.sa.velo.services.ride;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import be.kdg.sa.velo.repositories.VehicleLocationRepository;
import be.kdg.sa.velo.utils.RideUtils;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.stream.Stream;

/**
 * Jonas Leijzen
 * 10/10/2022
 */
@Component
public class RideStatisticsService implements RideDistanceCalculator, RideTimeCalculator {
	
	private final VehicleLocationRepository vehicleLocationRepository;
	
	public RideStatisticsService (VehicleLocationRepository vehicleLocationRepository) {
		this.vehicleLocationRepository = vehicleLocationRepository;
	}
	
	// Returns the distance of a ride in kilometers
	@Override
	public double getRideDistance (Ride ride) {
		Stream<Point> pointStream = Stream.empty ();
		if (ride.getStartPoint () != null)
			pointStream = Stream.concat (pointStream, Stream.of (ride.getStartPoint ()));
		
		pointStream = Stream.concat (pointStream, vehicleLocationRepository.getVehicleLocationsByRideId (ride.getId ())
				.stream ().map (VehicleLocation::getLocation));
		
		if (ride.getEndPoint () != null)
			pointStream = Stream.concat (pointStream, Stream.of (ride.getEndPoint ()));
		
		return RideUtils.getRideDistance (pointStream);
	}
	
	@Override
	public Duration getRideDuration (Ride ride) {
		return RideUtils.getRideDuration (ride);
	}
}
