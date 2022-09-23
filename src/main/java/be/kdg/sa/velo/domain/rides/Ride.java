package be.kdg.sa.velo.domain.rides;

import be.kdg.sa.velo.domain.stations.Lock;
import be.kdg.sa.velo.domain.subscriptions.Subscription;
import be.kdg.sa.velo.domain.subscriptions.vehicles.Vehicle;

/**
 * @author Maxim Derboven
 * @version 1.0 20/09/2022 12:44
 */
public class Ride {
	private final long id;
	private Vehicle vehicle;
	private Lock startPoint;
	private Lock endPoint;
	private long startTime;
	private long endTime;
	private double distance;
	private boolean isFinished;
	private Subscription subscription;
	
	public Ride (long id, Vehicle vehicle, long startTime, long endTime, double distance, boolean isFinished, Subscription subscription) {
		this.id = id;
		setVehicle (vehicle);
		setStartTime (startTime);
		setEndTime (endTime);
		setDistance (distance);
		setFinished (isFinished);
		setSubscription (subscription);
	}
	
	public long getId () {
		return id;
	}
	
	public Vehicle getVehicle () {
		return vehicle;
	}
	
	public void setVehicle (Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public Lock getStartPoint () {
		return startPoint;
	}
	
	public void setStartPoint (Lock startPoint) {
		this.startPoint = startPoint;
	}
	
	public Lock getEndPoint () {
		return endPoint;
	}
	
	public void setEndPoint (Lock endPoint) {
		this.endPoint = endPoint;
	}
	
	public long getStartTime () {
		return startTime;
	}
	
	public void setStartTime (long startTime) {
		this.startTime = startTime;
	}
	
	public long getEndTime () {
		return endTime;
	}
	
	public void setEndTime (long endTime) {
		this.endTime = endTime;
	}
	
	public double getDistance () {
		return distance;
	}
	
	public void setDistance (double distance) {
		this.distance = distance;
	}
	
	public boolean isFinished () {
		return isFinished;
	}
	
	public void setFinished (boolean finished) {
		isFinished = finished;
	}
	
	public Subscription getSubscription () {
		return subscription;
	}
	
	public void setSubscription (Subscription subscription) {
		this.subscription = subscription;
	}
}
