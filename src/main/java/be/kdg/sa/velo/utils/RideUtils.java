package be.kdg.sa.velo.utils;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.rides.RideType;
import org.locationtech.jts.geom.Point;

import java.time.Duration;
import java.util.stream.Stream;


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
	
	public static double getRideDistance (Stream<Point> pointStream) {
		double totalDistance = 0;
		Point lastPoint = null;
		for (Point point : (Iterable<Point>) pointStream::iterator) {
			if (lastPoint != null)
				totalDistance += PointUtils.getDistanceBetweenPoints (lastPoint, point);
			lastPoint = point;
		}
		return totalDistance;
	}
}
