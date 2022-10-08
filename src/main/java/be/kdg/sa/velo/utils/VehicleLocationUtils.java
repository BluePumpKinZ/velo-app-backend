package be.kdg.sa.velo.utils;

import be.kdg.sa.velo.domain.vehicles.VehicleLocation;

/**
 * Jonas Leijzen
 * 8/10/2022
 */
public class VehicleLocationUtils {
	
	// Returns the physical distance between two vehiclelocations.
	// It is possible that there is a function to do this, but we couldn't find it.
	public static double getDistanceBetweenLocations (VehicleLocation location1, VehicleLocation location2) {
		double x1, x2, y1, y2, z1, z2;
		x1 = Math.cos (Math.toRadians (location1.getLatitude ())) * Math.cos (Math.toRadians (location1.getLongitude ()));
		y1 = Math.cos (Math.toRadians (location1.getLatitude ())) * Math.sin (Math.toRadians (location1.getLongitude ()));
		z1 = Math.sin (Math.toRadians (location1.getLatitude ()));
		x2 = Math.cos (Math.toRadians (location2.getLatitude ())) * Math.cos (Math.toRadians (location2.getLongitude ()));
		y2 = Math.cos (Math.toRadians (location2.getLatitude ())) * Math.sin (Math.toRadians (location2.getLongitude ()));
		z2 = Math.sin (Math.toRadians (location2.getLatitude ()));
		return Math.acos (x1 * x2 + y1 * y2 + z1 * z2) * 6371;
	}
	
}
