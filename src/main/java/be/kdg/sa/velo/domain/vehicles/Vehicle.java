package be.kdg.sa.velo.domain.vehicles;

import javax.persistence.*;

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
	private String serialNumber;
	@ManyToOne (optional = false)
	@JoinColumn(name = "BikeLotId", foreignKey = @ForeignKey(name = "BikeLotId"))
	private VehicleLot type;
	
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
	
}
