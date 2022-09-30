package be.kdg.sa.velo.events.vehicles.locks;

import be.kdg.sa.velo.events.vehicles.VehicleEvent;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
public abstract class LockVehicleEvent extends VehicleEvent {
	
	private final int userId;
	private final boolean defect;
	
	protected LockVehicleEvent (int vehicleId, int userId, boolean defect) {
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
