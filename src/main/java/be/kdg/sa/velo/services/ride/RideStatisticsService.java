package be.kdg.sa.velo.services.ride;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.utils.PointUtils;
import be.kdg.sa.velo.utils.RideUtils;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * Jonas Leijzen
 * 10/10/2022
 */
@Component
public class RideStatisticsService implements RideDistanceCalculator, RideTimeCalculator {
	
	// Returns the distance of a ride in kilometers
	@Override
	public double getRideDistance (Ride ride) {
		return PointUtils.getDistanceBetweenPoints (
				ride.getStartPoint (), ride.getEndPoint ());
	}
	
	@Override
	public Duration getRideDuration (Ride ride) {
		return RideUtils.getRideDuration (ride);
	}
}
