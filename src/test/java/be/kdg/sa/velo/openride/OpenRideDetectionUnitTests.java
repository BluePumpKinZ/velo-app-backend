package be.kdg.sa.velo.openride;

import be.kdg.sa.velo.VeloApplicationTests;
import be.kdg.sa.velo.configuration.OpenRideDetectionProperties;
import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import be.kdg.sa.velo.repositories.RideRepository;
import be.kdg.sa.velo.repositories.VehicleLocationRepository;
import be.kdg.sa.velo.utils.PointUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class OpenRideDetectionUnitTests extends VeloApplicationTests {

	@Autowired
	private OpenRideDetection openRideDetection;
	@MockBean
	private OpenRideDetectionProperties properties;
	@MockBean
	private RideRepository rideRepository;
	@MockBean
	private VehicleLocationRepository vehicleLocationRepository;
	@MockBean
	private OpenRideCloser openRideCloser;
	
	@BeforeAll
	void setup () {
		
		given (properties.getMaxRideDuration ()).willReturn (Duration.ofHours (1));
		given (properties.getMaxNotMovingDuration ()).willReturn (Duration.ofMinutes (15));
		given (properties.getMaxNotMovingDistance ()).willReturn (25.0);
		
		Ride ride1 = new Ride ();
		ride1.setId (1);
		Ride ride2 = new Ride ();
		ride2.setId (2);
		Ride ride3 = new Ride ();
		ride3.setId (3);
		Ride ride4 = new Ride ();
		ride4.setId (4);
		
		Vehicle vehicle1 = new Vehicle ();
		vehicle1.setId (1);
		Vehicle vehicle2 = new Vehicle ();
		vehicle2.setId (2);
		
		VehicleLocation vehicleLocation1 = new VehicleLocation (vehicle1, PointUtils.createPoint (51.1, 3.9));
		vehicleLocation1.setTimestamp (LocalDateTime.now ().minusMinutes (5));
		VehicleLocation vehicleLocation2 = new VehicleLocation (vehicle1, PointUtils.createPoint (51.2, 4.0));
		vehicleLocation2.setTimestamp (LocalDateTime.now ().minusMinutes (6));
		VehicleLocation vehicleLocation3 = new VehicleLocation (vehicle1, PointUtils.createPoint (51.3, 3.9));
		vehicleLocation3.setTimestamp (LocalDateTime.now ().minusMinutes (7));
		VehicleLocation vehicleLocation4 = new VehicleLocation (vehicle1, PointUtils.createPoint (51.4, 4.0));
		vehicleLocation4.setTimestamp (LocalDateTime.now ().minusMinutes (8));
		
		VehicleLocation vehicleLocation5 = new VehicleLocation (vehicle2, PointUtils.createPoint (51.00000, 4.0));
		vehicleLocation5.setTimestamp (LocalDateTime.now ().minusMinutes (9));
		VehicleLocation vehicleLocation6 = new VehicleLocation (vehicle2, PointUtils.createPoint (51.00001, 4.0));
		vehicleLocation6.setTimestamp (LocalDateTime.now ().minusMinutes (10));
		VehicleLocation vehicleLocation7 = new VehicleLocation (vehicle2, PointUtils.createPoint (51.00002, 4.0));
		vehicleLocation7.setTimestamp (LocalDateTime.now ().minusMinutes (11));
		VehicleLocation vehicleLocation8 = new VehicleLocation (vehicle2, PointUtils.createPoint (51.00003, 4.0));
		vehicleLocation8.setTimestamp (LocalDateTime.now ().minusMinutes (12));
		
		given(rideRepository.getOpenRidesWithMinLength ((int)properties.getMaxRideDuration ().getSeconds ()))
				.willReturn (List.of(ride1, ride2));
		given(rideRepository.getOpenRides ()).willReturn (List.of(ride3, ride4));
		given(vehicleLocationRepository.getVehicleLocationsByRideId (ride1.getId ()))
				.willReturn (List.of());
		given(vehicleLocationRepository.getVehicleLocationsByRideId (ride2.getId ()))
				.willReturn (List.of());
		given(vehicleLocationRepository.getVehicleLocationsByRideId (ride3.getId ()))
				.willReturn (List.of(vehicleLocation1, vehicleLocation2, vehicleLocation3, vehicleLocation4));
		given(vehicleLocationRepository.getVehicleLocationsByRideId (ride4.getId ()))
				.willReturn (List.of(vehicleLocation5, vehicleLocation6, vehicleLocation7, vehicleLocation8));
	}
	
	@Test
	void OpenRideDurationDetectorTriggers () {
		openRideDetection.checkOpenRides ();
		
		ArgumentCaptor<Ride> rideArgumentCaptor = ArgumentCaptor.forClass (Ride.class);
		verify (openRideCloser, times(3)).closeRide (rideArgumentCaptor.capture ());
		
		var rideIds = rideArgumentCaptor.getAllValues ().stream().map (Ride::getId).sorted ().toList ();
		assertEquals (List.of(1, 2, 4), rideIds);
	}

}
