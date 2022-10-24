package be.kdg.sa.velo.services.ride;

import be.kdg.sa.velo.domain.rides.Ride;

/**
 * Jonas Leijzen
 * 10/10/2022
 */
// This interface only exists to remove the cicular
public interface RideDistanceCalculator {
	double getRideDistance (Ride ride);
}
