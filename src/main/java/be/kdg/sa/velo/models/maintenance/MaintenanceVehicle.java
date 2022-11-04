package be.kdg.sa.velo.models.maintenance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MaintenanceVehicle {
	private int vehicleId;
	private String serialNumber;
	private String type;

	public MaintenanceVehicle(int vehicleId, String serialNumber, String type) {
		this.vehicleId = vehicleId;
		this.serialNumber = serialNumber;
		this.type = type;
	}
}
