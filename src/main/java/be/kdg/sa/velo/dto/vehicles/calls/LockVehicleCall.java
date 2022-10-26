package be.kdg.sa.velo.dto.vehicles.calls;

import be.kdg.sa.velo.dto.vehicles.VehicleCall;


public abstract class LockVehicleCall extends VehicleCall {
	
	private final int userId;
	private final boolean defect;
	
	protected LockVehicleCall (int vehicleId, int userId, boolean defect) {
		super (vehicleId);
		this.userId = userId;
		this.defect = defect;
	}
	
	public int getUserId () {
		return userId;
	}
	
	public boolean isDefect () {
		return defect;
	}
}
