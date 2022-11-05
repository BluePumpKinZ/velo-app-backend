package be.kdg.sa.velo.openride.detectors;

import be.kdg.sa.velo.configuration.OpenRideDetectionProperties;
import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import be.kdg.sa.velo.openride.OpenRideCloser;
import be.kdg.sa.velo.repositories.RideRepository;
import be.kdg.sa.velo.repositories.VehicleLocationRepository;
import be.kdg.sa.velo.utils.PointUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotMovingOpenRideDetector implements OpenRideDetector {
	
	private final OpenRideDetectionProperties properties;
	private final OpenRideCloser openRideCloser;
	private final RideRepository rideRepository;
	private final VehicleLocationRepository vehicleLocationRepository;
	
	public NotMovingOpenRideDetector (OpenRideDetectionProperties properties, OpenRideCloser openRideCloser, RideRepository rideRepository, VehicleLocationRepository vehicleLocationRepository) {
		this.properties = properties;
		this.openRideCloser = openRideCloser;
		this.rideRepository = rideRepository;
		this.vehicleLocationRepository = vehicleLocationRepository;
	}
	
	@Override
	public int checkOpenRides () {
		var rides = rideRepository.getOpenRides ();
		var ridesToClose = rides.stream().filter (this::isRideNotMoving).toList ();
		ridesToClose.forEach (openRideCloser::closeRide);
		return (int)ridesToClose.size ();
	}
	
	private boolean isRideNotMoving (Ride ride) {
		var vehicleLocations = vehicleLocationRepository.getVehicleLocationsByRideId (ride.getId ());
		var lastCoupleVehicleLocations = vehicleLocations.stream().filter (vl -> vl.getTimestamp ()
				.plusSeconds (properties.getMaxNotMovingDuration ().getSeconds ())
				.isAfter (LocalDateTime.now ()))
				.map (VehicleLocation::getLocation).toList ();
		
		double maxDistance = PointUtils.maxPointsDistance (lastCoupleVehicleLocations) * 1000;
		return maxDistance < properties.getMaxNotMovingDistance ();
	}
	
}
