package be.kdg.sa.velo.domain.vehicles;

import be.kdg.sa.velo.domain.stations.Lock;
import ch.qos.logback.classic.db.names.ColumnName;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Maxim Derboven
 * @version 1.0 20/09/2022 12:38
 */
@Entity(name = "Vehicles")
public class Vehicle {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name="VehicleId")
	private long id;
	private String serialNumber;
	private VehicleType type;
	@OneToOne (optional = true)
	private Lock lock;
	
	public Vehicle () {
	}
	
	public Vehicle (long id, String serialNumber, VehicleType type) {
		this.id = id;
		this.serialNumber = serialNumber;
		this.type = type;
	}
	
	public long getId () {
		return id;
	}
	
	public void setId (long id) {
		this.id = id;
	}
	
	public String getSerialNumber () {
		return serialNumber;
	}
	
	public void setSerialNumber (String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public VehicleType getType () {
		return type;
	}
	
	public void setType (VehicleType type) {
		this.type = type;
	}
	
}
