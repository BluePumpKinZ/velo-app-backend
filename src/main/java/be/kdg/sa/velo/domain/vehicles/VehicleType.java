package be.kdg.sa.velo.domain.vehicles;

import javax.persistence.*;
import java.util.List;


@Entity(name = "BikeTypes")
public class VehicleType {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "BikeTypeId")
	private int id;
	@Column (name = "BikeTypeDescription", nullable = false)
	private String description;
	@OneToMany (mappedBy = "lot")
	private List<Vehicle> vehicles;
	
	public VehicleType () {
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public String getDescription () {
		return description;
	}
	
	public void setDescription (String description) {
		this.description = description;
	}
	
	public List<Vehicle> getVehicles () {
		return vehicles;
	}
	
	public void setVehicles (List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
}
