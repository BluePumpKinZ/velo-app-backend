package be.kdg.sa.velo.utils;

import be.kdg.sa.velo.domain.vehicles.VehicleLocation;

import static be.kdg.sa.velo.utils.ExtraMath.dotProduct;
import static java.lang.Math.*;
import static java.lang.Math.toRadians;

/**
 * Jonas Leijzen
 * 8/10/2022
 */
public class VehicleLocationUtils {
	
	// Returns the physical distance between two vehiclelocations.
	// It is possible that there is a function to do this, but we couldn't find it.
	public static double getDistanceBetweenLocations (VehicleLocation location1, VehicleLocation location2) {
		var coordinate1 = getCartesianCoordinates (location1);
		var coordinate2 = getCartesianCoordinates (location2);
		return acos (dotProduct(coordinate1, coordinate2)) * 6371;
	}
	
	private static double[] getCartesianCoordinates (VehicleLocation location) {
		double x, y, z;
		x = cos (toRadians (location.getLocation ().getX ())) * cos (toRadians (location.getLocation ().getY ()));
		y = cos (toRadians (location.getLocation ().getX ())) * sin (toRadians (location.getLocation ().getY ()));
		z = sin (toRadians (location.getLocation ().getX ()));
		return new double[] {x, y, z};
	}
	
}
