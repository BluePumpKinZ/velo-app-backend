package be.kdg.sa.velo.openride.detectors;

import be.kdg.sa.velo.configuration.OpenRideDetectionProperties;
import be.kdg.sa.velo.openride.OpenRideCloser;
import be.kdg.sa.velo.repositories.RideRepository;
import org.springframework.stereotype.Component;

@Component
public class RideDurationOpenRideDetector implements OpenRideDetector {
	
	private final OpenRideDetectionProperties properties;
	private final RideRepository rideRepository;
	private final OpenRideCloser openRideCloser;
	
	public RideDurationOpenRideDetector (OpenRideDetectionProperties properties, RideRepository rideRepository, OpenRideCloser openRideCloser) {
		this.properties = properties;
		this.rideRepository = rideRepository;
		this.openRideCloser = openRideCloser;
	}
	
	@Override
	public int checkOpenRides () {
		var rides = rideRepository.getOpenRidesWithMinLength ((int)properties.getMaxRideDuration ().getSeconds ());
		rides.forEach (openRideCloser::closeRide);
		return rides.size ();
	}
}
