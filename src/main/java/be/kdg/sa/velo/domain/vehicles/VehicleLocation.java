package be.kdg.sa.velo.domain.vehicles;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity (name = "VehicleLocations")
public class VehicleLocation {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "VehicleLocationId", columnDefinition = "SMALLINT")
	private int id;
	@Column (nullable = false)
	private LocalDateTime timestamp;
	@ManyToOne
	@JoinColumn (name = "VehicleId", foreignKey = @ForeignKey (name = "VehicleId"), columnDefinition = "SMALLINT")
	private Vehicle vehicle;
	private double latitude;
	private double longitude;
	
	public VehicleLocation () {
	
	}
	
	public VehicleLocation (int id, LocalDateTime timestamp, Vehicle vehicle, double latitude, double longitude) {
		setId (id);
		setTimestamp (timestamp);
		setVehicle (vehicle);
		setLatitude (latitude);
		setLongitude (longitude);
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
	
	public double getLatitude () {
		return latitude;
	}
	
	public void setLatitude (double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude () {
		return longitude;
	}
	
	public void setLongitude (double longitude) {
		this.longitude = longitude;
	}
}
