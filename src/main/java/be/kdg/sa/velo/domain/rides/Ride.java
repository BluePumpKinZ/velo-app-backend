package be.kdg.sa.velo.domain.rides;

import be.kdg.sa.velo.domain.stations.Lock;
import be.kdg.sa.velo.domain.subscriptions.Subscription;
import be.kdg.sa.velo.domain.vehicles.Vehicle;

import javax.persistence.*;

/**
 * @author Maxim Derboven
 * @version 1.0 20/09/2022 12:44
 */

@Entity(name = "Rides")
public class Ride {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RideId")
	private long id;
	@ManyToOne
	private Vehicle vehicle;
	@OneToOne (optional = true)
	private Lock startPoint;
	@OneToOne (optional = true)
	private Lock endPoint;
	private long startTime;
	@Column (name = "EndTime", nullable = true)
	private long endTime;
	@ManyToOne (optional = false)
	private Subscription subscription;
	
	public Ride () {
	
	}
	
	public Ride (long id, Vehicle vehicle, long startTime, long endTime, Subscription subscription) {
		this.id = id;
		setVehicle (vehicle);
		setStartTime (startTime);
		setEndTime (endTime);
		setSubscription (subscription);
	}
	
	public long getId () {
		return id;
	}
	
	public void setId (long id) {
		this.id = id;
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
	
	public Subscription getSubscription () {
		return subscription;
	}
	
	public void setSubscription (Subscription subscription) {
		this.subscription = subscription;
	}
}
