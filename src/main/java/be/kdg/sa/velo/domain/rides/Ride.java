package be.kdg.sa.velo.domain.rides;

import be.kdg.sa.velo.domain.stations.Lock;
import be.kdg.sa.velo.domain.subscriptions.Subscription;
import be.kdg.sa.velo.domain.vehicles.Vehicle;

import javax.persistence.*;

/**
 * @author Maxim Derboven
 * @version 1.0 20/09/2022 12:44
 */

@Entity (name = "Rides")
public class Ride {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "RideId", columnDefinition = "SMALLINT", unique = true, nullable = false)
	private int id;
	@ManyToOne
	@JoinColumn (name = "VehicleId", foreignKey = @ForeignKey (name = "VehicleId"))
	private Vehicle vehicle;
	@OneToOne (optional = true)
	@JoinColumn (name = "StartLockId", foreignKey = @ForeignKey (name = "StartLockId"))
	private Lock startLock;
	@OneToOne (optional = true)
	@JoinColumn (name = "EndLockId", foreignKey = @ForeignKey (name = "EndLockId"))
	private Lock endLock;
	private long startTime;
	@Column (name = "EndTime", nullable = true)
	private Long endTime;
	@ManyToOne (optional = false)
	@JoinColumn (name = "SubscriptionId", foreignKey = @ForeignKey (name = "SubscriptionId"))
	private Subscription subscription;
	
	public Ride () {
	}
	
	public Ride (Vehicle vehicle, Lock startLock, Subscription subscription) {
		this.vehicle = vehicle;
		this.startLock = startLock;
		startTime = System.currentTimeMillis ();
		this.subscription = subscription;
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public Vehicle getVehicle () {
		return vehicle;
	}
	
	public void setVehicle (Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public Lock getStartLock () {
		return startLock;
	}
	
	public void setStartLock (Lock startPoint) {
		this.startLock = startPoint;
	}
	
	public Lock getEndLock () {
		return endLock;
	}
	
	public void setEndLock (Lock endPoint) {
		this.endLock = endPoint;
	}
	
	public long getStartTime () {
		return startTime;
	}
	
	public void setStartTime (long startTime) {
		this.startTime = startTime;
	}
	
	public Long getEndTime () {
		return endTime;
	}
	
	public void setEndTime (Long endTime) {
		this.endTime = endTime;
	}
	
	public Subscription getSubscription () {
		return subscription;
	}
	
	public void setSubscription (Subscription subscription) {
		this.subscription = subscription;
	}
}
