package be.kdg.sa.velo.domain;

/**
 * @author Maxim Derboven
 * @version 1.0 20/09/2022 12:44
 */
public class Ride {
	private long id;
	private Vehicle vehicle;
	private Lock startPoint;
	private Lock endPoint;
	private long startTime;
	private long endTime;
	private double distance;
	private boolean isFinished;
	private Subscription subscription;
}
