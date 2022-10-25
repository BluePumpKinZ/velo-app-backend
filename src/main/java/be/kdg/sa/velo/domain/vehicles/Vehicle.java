package be.kdg.sa.velo.domain.vehicles;

import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Maxim Derboven
 * @version 1.0 20/09/2022 12:38
 */
@Entity(name = "Vehicles")
public class Vehicle {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "VehicleId")
	private int id;
	@Column (name = "SerialNumber", columnDefinition = "NVARCHAR", length = 20)
	private String serialNumber;
	@ManyToOne (optional = false)
	@JoinColumn(name = "BikeLotId")
	private VehicleLot lot;
	@Column (name = "LastMaintenanceOn")
	private LocalDateTime lastMaintenanceDate;
	@Column (name = "Point", columnDefinition = "GEOMETRY")
	private Point location;
	
	public Vehicle () {
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public String getSerialNumber () {
		return serialNumber;
	}
	
	public void setSerialNumber (String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public VehicleLot getLot () {
		return lot;
	}
	
	public void setLot (VehicleLot type) {
		this.lot = type;
	}
	
	public LocalDateTime getLastMaintenanceDate () {
		return lastMaintenanceDate;
	}
	
	public void setLastMaintenanceDate (LocalDateTime lastMaintenanceDate) {
		this.lastMaintenanceDate = lastMaintenanceDate;
	}
	
	public Point getLocation () {
		return location;
	}
	
	public void setLocation (Point location) {
		this.location = location;
	}
	
}
