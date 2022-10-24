package be.kdg.sa.velo.utils;

import be.kdg.sa.velo.VeloApplicationTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * Jonas Leijzen
 * 8/10/2022
 */

public class VehicleLocationUtilsUnitTests extends VeloApplicationTests {
	
	@ParameterizedTest
	@MethodSource ("getDistanceBetweenLocationsTestCases")
	public void TestDistanceBetweenLocations (double latitude1, double longitude1, double latitude2, double longitude2, double expectedDistance) {
		
		var location1 = PointUtils.createPoint (latitude1, longitude1);
		var location2 = PointUtils.createPoint (latitude2, longitude2);
		
		var distance = PointUtils.getDistanceBetweenPoints (location1, location2);
		Assertions.assertEquals (expectedDistance, distance, expectedDistance * 0.005); // Half a % error margin
		System.out.println (distance);
		
	}
	
	public Stream<Arguments> getDistanceBetweenLocationsTestCases () {
		// source for distances: https://www.calculator.net/distance-calculator.html
		return Stream.of (
				Arguments.of (51.2181687, 4.4008055, 51.2259715, 4.4343299, 2.50),
				Arguments.of (40.4381311, -3.8196195, 29.7601805, -95.3694983, 8055.8),
				Arguments.of (-34.8912093, -56.1873508, 23.8112929, 90.4123552, 16577.3),
				Arguments.of(-34.9286266, 138.5999476, 19.4304655, -99.1357327, 14136.2),
				Arguments.of (82.7661881, -40.2573039, -79.782346, 152.5923973, 19608.0),
				Arguments.of(51.2190968, 4.4016595, 50.8455211, 4.3569943, 41.68)
		);
	}
}
