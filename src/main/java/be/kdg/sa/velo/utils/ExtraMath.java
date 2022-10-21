package be.kdg.sa.velo.utils;

/**
 * Jonas Leijzen
 * 21/10/2022
 */
public class ExtraMath {
	
	public static double dotProduct (double[] a, double[] b) {
		if (a.length != b.length) {
			throw new IllegalArgumentException ("The arrays must be of the same length.");
		}
		double result = 0;
		for (int i = 0; i < a.length; i++) {
			result += a[i] * b[i];
		}
		return result;
	}
	
}
