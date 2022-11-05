package be.kdg.sa.velo.models.maintenance;

import lombok.NoArgsConstructor;

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
	
	public int getVehicleId () {
		return vehicleId;
	}
	
	public void setVehicleId (int vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	public String getSerialNumber () {
		return serialNumber;
	}
	
	public void setSerialNumber (String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public String getType () {
		return type;
	}
	
	public void setType (String type) {
		this.type = type;
	}
}
