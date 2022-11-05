package be.kdg.sa.velo.models.maintenance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class MaintenanceVehicle {
	@Id
	private int vehicleId;
	private String serialNumber;
	private String type;
	
	public MaintenanceVehicle(int vehicleId, String serialNumber, String type) {
		this.vehicleId = vehicleId;
		this.serialNumber = serialNumber;
		this.type = type;
	}
}
