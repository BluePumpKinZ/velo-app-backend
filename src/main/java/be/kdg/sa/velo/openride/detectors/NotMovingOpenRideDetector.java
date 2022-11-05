package be.kdg.sa.velo.openride.detectors;

import be.kdg.sa.velo.configuration.OpenRideDetectionProperties;
import be.kdg.sa.velo.openride.OpenRideCloser;
import be.kdg.sa.velo.repositories.RideRepository;
import org.springframework.stereotype.Component;

@Component
public class NotMovingOpenRideDetector implements OpenRideDetector {
	
	private final OpenRideDetectionProperties properties;
	private final OpenRideCloser openRideCloser;
	private final RideRepository rideRepository;
	
	public NotMovingOpenRideDetector (OpenRideDetectionProperties properties, OpenRideCloser openRideCloser, RideRepository rideRepository) {
		this.properties = properties;
		this.openRideCloser = openRideCloser;
		this.rideRepository = rideRepository;
	}
	
	public int checkOpenRides () {
		var rides = rideRepository.getNotMovingOpenRides ((int)properties.getMaxNotMovingDuration ().getSeconds (),
				properties.getMaxNotMovingDistance ());
		rides.forEach (openRideCloser::closeRide);
		return rides.size ();
	}
	
}
