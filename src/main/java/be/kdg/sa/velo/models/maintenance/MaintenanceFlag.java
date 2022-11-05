package be.kdg.sa.velo.models.maintenance;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MaintenanceFlag {
	private int maintenanceFlagId;
	private String serialNumber;
	private String type;

	private String reason;
	
	public MaintenanceFlag(int maintenanceFlagId, String serialNumber, String type, String reason) {
		this.maintenanceFlagId = maintenanceFlagId;
		this.serialNumber = serialNumber;
		this.type = type;
		this.reason = reason;
	}

	public String getInformation() {
		return String.format("%10s (%11s) - %20s", serialNumber, type, reason);
	}
	
	public int getMaintenanceFlagId() {
		return maintenanceFlagId;
	}
	
	public void setMaintenanceFlagId(int maintenanceFlagId) {
		this.maintenanceFlagId = maintenanceFlagId;
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

	public String getReason () {
		return reason;
	}

	public void setReason (String reason) {
		this.reason = reason;
	}
}
