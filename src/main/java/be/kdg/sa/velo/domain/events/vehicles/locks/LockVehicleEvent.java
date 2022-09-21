package be.kdg.sa.velo.domain.events.vehicles.locks;

import be.kdg.sa.velo.domain.events.vehicles.VehicleEvent;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
public abstract class LockVehicleEvent extends VehicleEvent {
	
	private final long userId;
	private final boolean defect;
	
	protected LockVehicleEvent (long vehicleId, long userId, boolean defect) {
		super (vehicleId);
		this.userId = userId;
		this.defect = defect;
	}
	
	public long getUserId () {
		return userId;
	}
	
	public boolean isDefect () {
		return defect;
	}
}
