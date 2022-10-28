package be.kdg.sa.velo.services;

import be.kdg.sa.velo.VeloApplicationTests;
import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.stations.Lock;
import be.kdg.sa.velo.domain.subscriptions.Subscription;
import be.kdg.sa.velo.domain.subscriptions.SubscriptionType;
import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.dto.vehicles.calls.LockVehicleCall;
import be.kdg.sa.velo.messaging.senders.InvoiceXmlSender;
import be.kdg.sa.velo.models.invoices.Invoice;
import be.kdg.sa.velo.services.ride.RideDistanceCalculator;
import be.kdg.sa.velo.services.ride.RideTimeCalculator;
import be.kdg.sa.velo.utils.LocalDateTimeUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class PriceCalculationUnitTests extends VeloApplicationTests {
	
	@Autowired
	private RideService rideService;
	@MockBean
	private InvoiceXmlSender invoiceXmlSender;
	@MockBean
	private LockVehicleCall lockVehicleCall;
	@MockBean
	private RideTimeCalculator rideTimeCalculator;
	@MockBean
	private RideDistanceCalculator rideDistanceCalculator;
	
	@ParameterizedTest
	@MethodSource ("getRides")
	void calculatePrice (Ride ride, Duration rideDuration, double distance, double expectedPrice) {
		
		given(rideTimeCalculator.getRideDuration(ride)).willReturn(rideDuration);
		given(rideDistanceCalculator.getRideDistance(ride)).willReturn(distance);
		
		LockVehicleCall event = lockVehicleCall;
		given (event.isDefect ()).willReturn (false);
		
		rideService.HandlePayment (ride, event);
		
		ArgumentCaptor<Invoice> invoiceArgumentCaptor = ArgumentCaptor.forClass (Invoice.class);
		verify (invoiceXmlSender, times (1)).send (invoiceArgumentCaptor.capture ());
		
		Invoice invoice = invoiceArgumentCaptor.getValue ();
		assertEquals (expectedPrice, invoice.getTotalPrice (), 0.001); // Allow for a small differences in floating point numbers
		
	}
	
	private Stream<Arguments> getRides () {
		
		Lock startLock = new Lock ();
		Lock endLock = new Lock ();
		Vehicle vehicle = new Vehicle ();
		
		SubscriptionType subscriptionTypeDag = new SubscriptionType ("DAG");
		SubscriptionType subscriptionTypeMaand = new SubscriptionType ("MAAND");
		SubscriptionType subscriptionTypeJaar = new SubscriptionType ("JAAR");
		
		Subscription subscriptionDag = new Subscription ();
		Subscription subscriptionMaand = new Subscription ();
		Subscription subscriptionJaar = new Subscription ();
		
		subscriptionDag.setSubscriptionType (subscriptionTypeDag);
		subscriptionMaand.setSubscriptionType (subscriptionTypeMaand);
		subscriptionJaar.setSubscriptionType (subscriptionTypeJaar);
		
		var timeZero = LocalDateTimeUtils.fromUTCMillis (0);
		var dockedRides = Stream.of (
				Arguments.of (new Ride (vehicle, startLock, endLock, timeZero, timeZero, subscriptionDag), Duration.ofMinutes (2), 0.550, 0.30),
				Arguments.of (new Ride (vehicle, startLock, endLock, timeZero, timeZero, subscriptionMaand), Duration.ofMinutes (5), 1.400, 0.50),
				Arguments.of (new Ride (vehicle, startLock, endLock, timeZero, timeZero, subscriptionJaar), Duration.ofMinutes (17), 5.600, 1.36)
		);
		
		var undockedRides = Stream.of (
				Arguments.of (new Ride (vehicle, null, null, timeZero, timeZero, subscriptionDag), Duration.ofMinutes (2), 0.550, 2.065),
				Arguments.of (new Ride (vehicle, null, null, timeZero, timeZero, subscriptionMaand), Duration.ofMinutes (5), 1.400, 1.908),
				Arguments.of (new Ride (vehicle, null, null, timeZero, timeZero, subscriptionJaar), Duration.ofMinutes (17), 5.600, 3.032)
		);
		
		return Stream.concat (dockedRides, undockedRides);
	}
	
}
