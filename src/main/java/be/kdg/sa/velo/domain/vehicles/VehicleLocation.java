package be.kdg.sa.velo.domain.vehicles;

import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity (name = "VehicleLocations")
public class VehicleLocation {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "VehicleLocationId", columnDefinition = "SMALLINT")
	private int id;
	@Column (nullable = false, name = "TimeStamp")
	private LocalDateTime timestamp;
	@ManyToOne
	@JoinColumn (name = "VehicleId", columnDefinition = "SMALLINT")
	private Vehicle vehicle;
	@Column (nullable = false, name = "Location")
	private Point location;
	
	public VehicleLocation () {
	
	}
	
	public VehicleLocation (Vehicle vehicle, Point point) {
		setTimestamp (LocalDateTime.now ());
		setVehicle (vehicle);
		setLocation (point);
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public LocalDateTime getTimestamp () {
		return timestamp;
	}
	
	public void setTimestamp (LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	public Vehicle getVehicle () {
		return vehicle;
	}
	
	public void setVehicle (Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public Point getLocation () {
		return location;
	}
	
	public void setLocation (Point location) {
		this.location = location;
	}
}
