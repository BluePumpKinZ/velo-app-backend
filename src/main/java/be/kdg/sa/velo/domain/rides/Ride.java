package be.kdg.sa.velo.domain.rides;

import be.kdg.sa.velo.domain.stations.Lock;
import be.kdg.sa.velo.domain.subscriptions.Subscription;
import be.kdg.sa.velo.domain.vehicles.Vehicle;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity (name = "Rides")
public class Ride {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "RideId", columnDefinition = "SMALLINT", unique = true, nullable = false)
	private int id;
	@ManyToOne (optional = false)
	@JoinColumn (name = "VehicleId")
	private Vehicle vehicle;
	@JoinColumn (name = "StartPoint", nullable = false)
	private Point startPoint;
	@JoinColumn (name = "EndPoint")
	private Point endPoint;
	@OneToOne (optional = true)
	@JoinColumn (name = "StartLockId")
	private Lock startLock;
	@OneToOne (optional = true)
	@JoinColumn (name = "EndLockId")
	private Lock endLock;
	private LocalDateTime startTime;
	@Column (name = "EndTime", nullable = true)
	private LocalDateTime endTime;
	@ManyToOne
	@JoinColumn (name = "SubscriptionId")
	private Subscription subscription;
	
	public Ride () {
	}
	
	public Ride (Vehicle vehicle, Point startPoint, Lock startLock, Subscription subscription) {
		this(vehicle, startPoint, subscription);
		this.startLock = startLock;
	}
	
	public Ride (Vehicle vehicle, Lock startLock, Lock endLock, LocalDateTime startTime, LocalDateTime endTime, Subscription subscription) {
		this.vehicle = vehicle;
		this.startLock = startLock;
		this.endLock = endLock;
		this.startTime = startTime;
		this.endTime = endTime;
		this.subscription = subscription;
	}
	
	public Ride (Vehicle vehicle, Point startPoint, Subscription subscription) {
		this.vehicle = vehicle;
		this.startPoint = startPoint;
		this.subscription = subscription;
		startTime = LocalDateTime.now ();
	}
	
	public Ride (Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime, Subscription subscription) {
		this.vehicle = vehicle;
		this.subscription = subscription;
		this.startTime = startTime;
		this.endTime = endTime;
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
	
	public Point getStartPoint () {
		return startPoint;
	}
	
	public void setStartPoint (Point startPoint) {
		this.startPoint = startPoint;
	}
	
	public Point getEndPoint () {
		return endPoint;
	}
	
	public void setEndPoint (Point endPoint) {
		this.endPoint = endPoint;
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
	
	public LocalDateTime getStartTime () {
		return startTime;
	}
	
	public void setStartTime (LocalDateTime startTime) {
		this.startTime = startTime;
	}
	
	public LocalDateTime getEndTime () {
		return endTime;
	}
	
	public void setEndTime (LocalDateTime endTime) {
		this.endTime = endTime;
	}
	
	public Subscription getSubscription () {
		return subscription;
	}
	
	public void setSubscription (Subscription subscription) {
		this.subscription = subscription;
	}
}
