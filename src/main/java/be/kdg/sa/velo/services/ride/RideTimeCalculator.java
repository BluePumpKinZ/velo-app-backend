package be.kdg.sa.velo.services.ride;

import be.kdg.sa.velo.domain.rides.Ride;

import java.time.Duration;

/**
 * Jonas Leijzen
 * 10/10/2022
 */
public interface RideTimeCalculator {
	Duration getRideDuration (Ride ride);
}
