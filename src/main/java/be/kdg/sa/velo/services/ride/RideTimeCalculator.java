package be.kdg.sa.velo.services.ride;

import be.kdg.sa.velo.domain.rides.Ride;

import java.time.Duration;


public interface RideTimeCalculator {
	Duration getRideDuration (Ride ride);
}
