package be.kdg.sa.velo.utils;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;

/**
 * Jonas Leijzen
 * 6/10/2022
 */
public class PointFactory {
	
	public static Point createPoint (double latitude, double longitude) {
		return new Point (new Coordinate (latitude, longitude), null, 4326);
	}
	
}
