package be.kdg.sa.velo.services;

import be.kdg.sa.velo.VeloApplicationTests;
import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.stations.Lock;
import be.kdg.sa.velo.domain.stations.Station;
import be.kdg.sa.velo.domain.subscriptions.Subscription;
import be.kdg.sa.velo.domain.subscriptions.SubscriptionType;
import be.kdg.sa.velo.domain.users.User;
import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.models.vehicles.calls.UnlockDockedVehicleCall;
import be.kdg.sa.velo.models.vehicles.calls.UnlockUndockedVehicleCall;
import be.kdg.sa.velo.repositories.LockRepository;
import be.kdg.sa.velo.repositories.RideRepository;
import be.kdg.sa.velo.repositories.VehicleRepository;
import be.kdg.sa.velo.utils.PointFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Jonas Leijzen
 * 6/10/2022
 */
public class RideServiceUnitTests extends VeloApplicationTests {
	
	@Autowired
	private RideService rideService;
	
	@MockBean
	private StationService stationService;
	@MockBean
	private SubscriptionService subscriptionService;
	@MockBean
	private RideRepository rideRepository;
	@MockBean
	private VehicleRepository vehicleRepository;
	@MockBean
	private LockRepository lockRepository;
	
	@BeforeEach
	void setUp () {
		Station station = new Station ();
		station.setId (5);
		station.setGpsCoord (PointFactory.createPoint (51.219, 4.402));
		
		Vehicle vehicle1 = new Vehicle ();
		vehicle1.setId (4);
		given (vehicleRepository.findById (4)).willReturn (Optional.of (vehicle1));
		
		Vehicle vehicle2 = new Vehicle ();
		vehicle2.setId (1);
		given (vehicleRepository.findById (1)).willReturn (Optional.of (vehicle2));
		
		Lock lock1 = new Lock ();
		lock1.setId (10);
		lock1.setStationLockNr (1);
		lock1.setStation (station);
		lock1.setVehicle (vehicle1);
		Lock lock2 = new Lock ();
		lock2.setId (20);
		lock2.setStationLockNr (2);
		lock2.setStation (station);
		given (stationService.getFilledLocksForStation (5)).willReturn (List.of (lock1));
		given (stationService.getAvailableLocksForStation (5)).willReturn (List.of (lock2));
		
		SubscriptionType type = new SubscriptionType ();
		type.setDescription ("MAAND");
		
		User user = new User ();
		user.setId (2);
		
		Subscription subscription = new Subscription ();
		subscription.setId (3);
		subscription.setStartDate (LocalDate.now ().minusDays (1));
		subscription.setSubscriptionType (type);
		subscription.setUser (user);
		given(subscriptionService.getCurrentUserSubscription (2)).willReturn (subscription);
		
	}
	
	@Test
	void startDockedRide () {
	
		var event = new UnlockDockedVehicleCall (2, 5);
		int lockId = rideService.startDockedRide (event);
		assertEquals(10, lockId);
		
		ArgumentCaptor<Ride> rideArgumentCaptor = ArgumentCaptor.forClass (Ride.class);
		verify (rideRepository, times (1)).save (rideArgumentCaptor.capture ());
		Ride createdRide = rideArgumentCaptor.getValue ();
		assertEquals (3, createdRide.getSubscription ().getId ());
		assertEquals (10, createdRide.getStartLock ().getId ());
		assertNull (createdRide.getEndLock ());
		assertEquals(4, createdRide.getVehicle ().getId ());
	}
	
	@Test
	void startUndockedRide () {
		
		var event = new UnlockUndockedVehicleCall (1, 2);
		rideService.startUndockedRide (event);
		
		ArgumentCaptor<Ride> rideArgumentCaptor = ArgumentCaptor.forClass (Ride.class);
		verify (rideRepository, times (1)).save (rideArgumentCaptor.capture ());
		Ride createdRide = rideArgumentCaptor.getValue ();
		assertEquals (3, createdRide.getSubscription ().getId ());
		assertNull (createdRide.getStartLock ());
		assertNull (createdRide.getEndLock ());
		assertEquals(1, createdRide.getVehicle ().getId ());
		assertEquals(3, createdRide.getSubscription ().getId ());
		
	}
	
}
