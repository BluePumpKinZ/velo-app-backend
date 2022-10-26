package be.kdg.sa.velo.domain.vehicles;

import org.locationtech.jts.geom.Point;

import javax.persistence.*;


@Entity (name="VehicleLocations")
public class VehicleLocation {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "VehicleLocationId")
	private int id;
	@ManyToOne (optional = false)
	@JoinColumn (name = "VehicleId", columnDefinition = "SMALLINT")
	private Vehicle vehicle;
	@Column (name = "Location", nullable = false, columnDefinition = "GEOMETRY")
	private Point location;
	@Column (name = "Timestamp", nullable = false)
	private long timestamp;
	
	public VehicleLocation() {
	}
	
	public VehicleLocation(Vehicle vehicle, Point location) {
		this.vehicle = vehicle;
		this.location = location;
		this.timestamp = System.currentTimeMillis ();
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
	
	public Point getLocation () {
		return location;
	}
	
	public void setLocation (Point location) {
		this.location = location;
	}
	
	public long getTimestamp () {
		return timestamp;
	}
	
	public void setTimestamp (long timestamp) {
		this.timestamp = timestamp;
	}
}
