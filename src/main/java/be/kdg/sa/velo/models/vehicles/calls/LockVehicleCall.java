package be.kdg.sa.velo.models.vehicles.calls;

import be.kdg.sa.velo.models.vehicles.VehicleCall;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
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
