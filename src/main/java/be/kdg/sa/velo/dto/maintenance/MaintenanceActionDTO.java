package be.kdg.sa.velo.dto.maintenance;

import be.kdg.sa.velo.domain.maintenance.MaintenanceAction;
import be.kdg.sa.velo.domain.maintenance.MaintenanceFlagging;

import java.time.LocalDateTime;


public class MaintenanceActionDTO {
	public int flaggingId;
	public String solution;

	public MaintenanceActionDTO(int flaggingId, String solution) {
		this.flaggingId = flaggingId;
		this.solution = solution;
	}

	public MaintenanceActionDTO() {
	}

	public int getflaggingId() {
		return flaggingId;
	}

	public void setflaggingId(int flaggingId) {
		this.flaggingId = flaggingId;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public MaintenanceAction toMaintenanceAction(MaintenanceFlagging flagging) {
		var maintenanceAction = new MaintenanceAction();
		maintenanceAction.setSolution(this.solution);
		maintenanceAction.setFlagging(flagging);
		maintenanceAction.setTimestamp(LocalDateTime.now());
		return maintenanceAction;
	}
}
