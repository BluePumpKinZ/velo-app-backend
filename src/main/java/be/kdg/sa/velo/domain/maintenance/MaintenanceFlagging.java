package be.kdg.sa.velo.domain.maintenance;

import be.kdg.sa.velo.domain.vehicles.Vehicle;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity (name = "MaintenanceFlaggings")
public class MaintenanceFlagging {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "MaintenanceFlaggingId", columnDefinition = "SMALLINT")
	private int id;
	@ManyToOne
	@JoinColumn (name = "VehicleId", columnDefinition = "SMALLINT")
	private Vehicle vehicle;
	private LocalDateTime timestamp;
	private String reason;

	public MaintenanceFlagging(Vehicle vehicle, LocalDateTime timestamp, String reason) {
		this.vehicle = vehicle;
		this.timestamp = timestamp;
		this.reason = reason;
	}

	public MaintenanceFlagging () {
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
	
	public LocalDateTime getTimestamp () {
		return timestamp;
	}
	
	public void setTimestamp (LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getReason () {
		return reason;
	}
	
	public void setReason (String reason) {
		this.reason = reason;
	}
}
