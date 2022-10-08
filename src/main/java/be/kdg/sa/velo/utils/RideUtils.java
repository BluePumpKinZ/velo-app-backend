package be.kdg.sa.velo.utils;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.rides.RideType;

import java.time.Duration;

/**
 * Jonas Leijzen
 * 8/10/2022
 */
public class RideUtils {
	
	public static RideType getRideType (Ride ride) {
		if (ride.getSubscription () == null)
			return RideType.MAINTENANCE;
		if (ride.getStartLock () == null)
			return RideType.UNDOCKED;
		return RideType.DOCKED;
	}
	
	public static Duration getRideDuration (Ride ride) {
		return Duration.ofMillis (ride.getEndTime () - ride.getStartTime ());
	}
}
