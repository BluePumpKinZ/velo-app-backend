package be.kdg.sa.velo.utils;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static be.kdg.sa.velo.utils.ExtraMath.dotProduct;
import static java.lang.Math.*;


public class PointUtils {
	
	public static Point createPoint (double latitude, double longitude) {
		return new Point (new Coordinate (latitude, longitude), null, 4326);
	}
	
	// Returns the physical distance between two vehiclelocations.
	// It is possible that there is a function to do this, but we couldn't find it.
	public static double getDistanceBetweenPoints (Point location1, Point location2) {
		var coordinate1 = getCartesianCoordinates (location1);
		var coordinate2 = getCartesianCoordinates (location2);
		return acos (dotProduct(coordinate1, coordinate2)) * 6371;
	}
	
	private static double[] getCartesianCoordinates (Point location) {
		double x, y, z;
		x = cos (toRadians (location.getX ())) * cos (toRadians (location.getY ()));
		y = cos (toRadians (location.getX ())) * sin (toRadians (location.getY ()));
		z = sin (toRadians (location.getX ()));
		return new double[] {x, y, z};
	}
	
	public static double maxPointsDistance (List<Point> locations) {
		AtomicReference<Double> maxDistance = new AtomicReference<> ((double) 0);
		locations.forEach (location1 -> {
			locations.forEach (location2 -> {
				double distance = getDistanceBetweenPoints (location1, location2);
				maxDistance.set (Math.max (maxDistance.get (), distance));
			});
		});
		return maxDistance.get ();
	}
	
}
