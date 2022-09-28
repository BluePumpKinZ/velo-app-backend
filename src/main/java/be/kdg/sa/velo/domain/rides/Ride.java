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
	@Column (name = "RideId")
	private int id;
	@ManyToOne
	@JoinColumn (name = "VehicleId", foreignKey = @ForeignKey (name = "VehicleId"))
	private Vehicle vehicle;
	@OneToOne (optional = true)
	@JoinColumn (name = "StartLockId", foreignKey = @ForeignKey (name = "StartLockId"))
	private Lock startPoint;
	@OneToOne (optional = true)
	@JoinColumn (name = "EndLockId", foreignKey = @ForeignKey (name = "EndLockId"))
	private Lock endPoint;
	private long startTime;
	@Column (name = "EndTime", nullable = true)
	private long endTime;
	@ManyToOne (optional = false)
	@JoinColumn (name = "SubscriptionId", foreignKey = @ForeignKey (name = "SubscriptionId"))
	private Subscription subscription;
	
	public Ride () {
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
