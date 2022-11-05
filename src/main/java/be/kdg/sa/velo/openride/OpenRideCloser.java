package be.kdg.sa.velo.openride;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.repositories.RideRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OpenRideCloser {

	private final RideRepository rideRepository;
	
	public OpenRideCloser (RideRepository rideRepository) {
		this.rideRepository = rideRepository;
	}
	
	public void closeRide (Ride ride) {
		var vehicle = ride.getVehicle ();
		ride.setEndPoint (vehicle.getLocation ());
		ride.setEndTime (LocalDateTime.now ());
		rideRepository.save (ride);
	}

}
