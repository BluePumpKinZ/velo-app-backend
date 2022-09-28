package be.kdg.sa.velo.domain.vehicles;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Jonas Leijzen
 * 27/09/2022
 */
@Entity(name = "Bikelots")
public class VehicleLot {
	@Id
	@Column(name = "BikeLotId")
	private int id;
	private LocalDate deliveryDate;
	@ManyToOne (optional = false)
	@JoinColumn(name = "BikeTypeId", foreignKey = @ForeignKey(name = "BikeTypeId"))
	private VehicleType type;
	
	public VehicleLot () {
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public LocalDate getDeliveryDate () {
		return deliveryDate;
	}
	
	public void setDeliveryDate (LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	public VehicleType getType () {
		return type;
	}
	
	public void setType (VehicleType type) {
		this.type = type;
	}
}
