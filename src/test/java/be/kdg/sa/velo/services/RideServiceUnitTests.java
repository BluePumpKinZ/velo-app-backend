package be.kdg.sa.velo.services;

import be.kdg.sa.velo.VeloApplicationTests;
import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.stations.Lock;
import be.kdg.sa.velo.domain.stations.Station;
import be.kdg.sa.velo.domain.subscriptions.Subscription;
import be.kdg.sa.velo.domain.subscriptions.SubscriptionType;
import be.kdg.sa.velo.domain.users.User;
import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import be.kdg.sa.velo.domain.vehicles.VehicleLot;
import be.kdg.sa.velo.domain.vehicles.VehicleType;
import be.kdg.sa.velo.dto.vehicles.calls.LockDockedVehicleCall;
import be.kdg.sa.velo.dto.vehicles.calls.LockUndockedVehicleCall;
import be.kdg.sa.velo.dto.vehicles.calls.UnlockDockedVehicleCall;
import be.kdg.sa.velo.dto.vehicles.calls.UnlockUndockedVehicleCall;
import be.kdg.sa.velo.messaging.senders.InvoiceXmlSender;
import be.kdg.sa.velo.models.invoices.Invoice;
import be.kdg.sa.velo.repositories.LockRepository;
import be.kdg.sa.velo.repositories.RideRepository;
import be.kdg.sa.velo.repositories.VehicleLocationRepository;
import be.kdg.sa.velo.repositories.VehicleRepository;
import be.kdg.sa.velo.utils.LocalDateTimeUtils;
import be.kdg.sa.velo.utils.PointUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


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
	@MockBean
	private VehicleLocationRepository vehicleLocationRepository;
	
	@MockBean
	private InvoiceXmlSender invoiceXmlSender;
	
	@BeforeEach
	void setUp () {
		Station station = new Station ();
		station.setId (5);
		station.setGpsCoord (PointUtils.createPoint (51.219, 4.402));
		
		VehicleType vehicleType = new VehicleType ();
		vehicleType.setId (1);
		vehicleType.setDescription ("Velo Bike");
		
		given (vehicleRepository.getVehicleType (1)).willReturn (vehicleType);
		given (vehicleRepository.getVehicleType (4)).willReturn (vehicleType);
		given (vehicleRepository.getVehicleType (6)).willReturn (vehicleType);
		given (vehicleRepository.getVehicleType (7)).willReturn (vehicleType);
		
		VehicleLot vehicleLot = new VehicleLot ();
		vehicleLot.setId (1);
		vehicleLot.setType (vehicleType);
		
		Vehicle vehicle1 = new Vehicle ();
		vehicle1.setId (4);
		vehicle1.setLot (vehicleLot);
		vehicle1.setLastMaintenanceDate (LocalDateTime.now ().minusDays (1));
		given (vehicleRepository.findById (4)).willReturn (Optional.of (vehicle1));
		
		Vehicle vehicle2 = new Vehicle ();
		vehicle2.setId (1);
		vehicle2.setLot (vehicleLot);
		vehicle2.setLastMaintenanceDate (LocalDateTime.now ().minusDays (1));
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
		given (lockRepository.findById (10)).willReturn (Optional.of (lock1));
		given (lockRepository.findById (20)).willReturn (Optional.of (lock2));
		
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
		
		Vehicle vehicle3 = new Vehicle ();
		vehicle3.setId (6);
		vehicle3.setLot (vehicleLot);
		vehicle3.setLastMaintenanceDate (LocalDateTime.now ().minusDays (1));
		given (vehicleRepository.findById (6)).willReturn (Optional.of (vehicle3));
		
		Vehicle vehicle4 = new Vehicle ();
		vehicle4.setId (7);
		vehicle4.setLot (vehicleLot);
		vehicle4.setLastMaintenanceDate (LocalDateTime.now ().minusDays (1));
		given (vehicleRepository.findById (7)).willReturn (Optional.of (vehicle4));
		
		Ride ride1 = new Ride ();
		ride1.setId (1);
		ride1.setSubscription (subscription);
		ride1.setVehicle (vehicle3);
		ride1.setStartTime (LocalDateTime.now ().minusMinutes (5));
		ride1.setStartLock (lock1);
		ride1.setStartPoint (PointUtils.createPoint (51.219, 4.402));
		given (rideRepository.getLastRideForVehicle (6)).willReturn (Optional.of (ride1));
		
		Ride ride2 = new Ride ();
		ride2.setId (2);
		ride2.setSubscription (subscription);
		ride2.setVehicle (vehicle4);
		ride2.setStartTime (LocalDateTime.now ().minusMinutes (5)); // 5 minutes ago
		ride2.setStartLock (lock1);
		given (rideRepository.getLastRideForVehicle (7)).willReturn (Optional.of (ride2));
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
		
		ArgumentCaptor<VehicleLocation> vehicleLocationArgumentCaptor = ArgumentCaptor.forClass (VehicleLocation.class);
		verify (vehicleLocationRepository, times (1)).save (vehicleLocationArgumentCaptor.capture ());
		
		VehicleLocation createdVehicleLocation = vehicleLocationArgumentCaptor.getValue ();
		assertEquals (4, createdVehicleLocation.getVehicle ().getId ());
		assertNotNull (createdVehicleLocation.getLocation ());
		
		ArgumentCaptor<Vehicle> vehicleArgumentCaptor = ArgumentCaptor.forClass (Vehicle.class);
		verify (vehicleRepository, times (1)).save (vehicleArgumentCaptor.capture ());
		
		Vehicle updatedVehicle = vehicleArgumentCaptor.getValue ();
		assertEquals (4, updatedVehicle.getId ());
		assertNotNull (updatedVehicle.getLocation ());
	}
	
	@Test
	void startUndockedRide () {
		
		var event = new UnlockUndockedVehicleCall (1, 2, 51.219, 4.402);
		rideService.startUndockedRide (event);
		
		ArgumentCaptor<Ride> rideArgumentCaptor = ArgumentCaptor.forClass (Ride.class);
		verify (rideRepository, times (1)).save (rideArgumentCaptor.capture ());
		Ride createdRide = rideArgumentCaptor.getValue ();
		assertEquals (3, createdRide.getSubscription ().getId ());
		assertNull (createdRide.getStartLock ());
		assertNull (createdRide.getEndLock ());
		assertEquals(1, createdRide.getVehicle ().getId ());
		assertEquals(3, createdRide.getSubscription ().getId ());
		
		ArgumentCaptor<VehicleLocation> vehicleLocationArgumentCaptor = ArgumentCaptor.forClass (VehicleLocation.class);
		verify (vehicleLocationRepository, times (1)).save (vehicleLocationArgumentCaptor.capture ());
		
		VehicleLocation createdVehicleLocation = vehicleLocationArgumentCaptor.getValue ();
		assertEquals (1, createdVehicleLocation.getVehicle ().getId ());
		assertNotNull (createdVehicleLocation.getLocation ());
		
		ArgumentCaptor<Vehicle> vehicleArgumentCaptor = ArgumentCaptor.forClass (Vehicle.class);
		verify (vehicleRepository, times (1)).save (vehicleArgumentCaptor.capture ());
		
		Vehicle updatedVehicle = vehicleArgumentCaptor.getValue ();
		assertEquals (1, updatedVehicle.getId ());
	}
	
	@Test
	void endDockedRide () {
		
		var event = new LockDockedVehicleCall (6, 3, false, 20);
		rideService.endDockedRide (event);
		
		ArgumentCaptor<Lock> lockArgumentCaptor = ArgumentCaptor.forClass (Lock.class);
		verify (lockRepository, times (1)).save (lockArgumentCaptor.capture ());
		
		Lock lock = lockArgumentCaptor.getValue ();
		assertEquals (6, lock.getVehicle().getId ());
		Ride ride = rideRepository.getLastRideForVehicle (6).orElseThrow ();
		assertEquals (lock.getId (), ride.getEndLock ().getId ());
		assertEquals (LocalDateTimeUtils.toUTCMillis (LocalDateTime.now ()), LocalDateTimeUtils.toUTCMillis (ride.getEndTime ()), 1000); // within 1 second
		
		ArgumentCaptor<Invoice> invoiceArgumentCaptor = ArgumentCaptor.forClass (Invoice.class);
		verify (invoiceXmlSender, times (1)).send (invoiceArgumentCaptor.capture ());
		
		Invoice invoice = invoiceArgumentCaptor.getValue ();
		assertNotNull (invoice);
		assertNotEquals (0, invoice.getTotalPrice ());
		
		ArgumentCaptor<VehicleLocation> vehicleLocationArgumentCaptor = ArgumentCaptor.forClass (VehicleLocation.class);
		verify (vehicleLocationRepository, times (1)).save (vehicleLocationArgumentCaptor.capture ());
		
		VehicleLocation createdVehicleLocation = vehicleLocationArgumentCaptor.getValue ();
		assertEquals (6, createdVehicleLocation.getVehicle ().getId ());
		assertNotNull (createdVehicleLocation.getLocation ());
		
		ArgumentCaptor<Vehicle> vehicleArgumentCaptor = ArgumentCaptor.forClass (Vehicle.class);
		verify (vehicleRepository, times (1)).save (vehicleArgumentCaptor.capture ());
		
		Vehicle updatedVehicle = vehicleArgumentCaptor.getValue ();
		assertEquals (6, updatedVehicle.getId ());
	}
	
	@Test
	void endUndockedRide () {
		
		var event = new LockUndockedVehicleCall (7, 4, false, 51.0, 4.0);
		rideService.endUndockedRide (event);
		
		ArgumentCaptor<Ride> rideArgumentCaptor = ArgumentCaptor.forClass (Ride.class);
		verify (rideRepository, times (1)).save (rideArgumentCaptor.capture ());
		
		Ride ride = rideArgumentCaptor.getValue ();
		assertEquals (7, ride.getVehicle ().getId ());
		assertEquals (LocalDateTimeUtils.toUTCMillis (LocalDateTime.now ()), LocalDateTimeUtils.toUTCMillis (ride.getEndTime ()), 1000); // within 1 second
		
		ArgumentCaptor<Invoice> invoiceArgumentCaptor = ArgumentCaptor.forClass (Invoice.class);
		verify (invoiceXmlSender, times (1)).send (invoiceArgumentCaptor.capture ());
		
		Invoice invoice = invoiceArgumentCaptor.getValue ();
		assertNotNull (invoice);
		assertNotEquals (0, invoice.getTotalPrice ());
		
		ArgumentCaptor<VehicleLocation> vehicleLocationArgumentCaptor = ArgumentCaptor.forClass (VehicleLocation.class);
		verify (vehicleLocationRepository, times (1)).save (vehicleLocationArgumentCaptor.capture ());
		
		VehicleLocation createdVehicleLocation = vehicleLocationArgumentCaptor.getValue ();
		assertEquals (7, createdVehicleLocation.getVehicle ().getId ());
		
		ArgumentCaptor<Vehicle> vehicleArgumentCaptor = ArgumentCaptor.forClass (Vehicle.class);
		verify (vehicleRepository, times (1)).save (vehicleArgumentCaptor.capture ());
		
		Vehicle updatedVehicle = vehicleArgumentCaptor.getValue ();
		assertEquals (7, updatedVehicle.getId ());
	}
	
}
