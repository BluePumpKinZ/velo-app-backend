package be.kdg.sa.velo.events.vehicles.locks;

import be.kdg.sa.velo.events.vehicles.VehicleEvent;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
public abstract class UnlockVehicleEvent extends VehicleEvent {
	
	private final int userId;
	
	protected UnlockVehicleEvent (int vehicleId, int userId) {
		super (vehicleId);
		this.userId = userId;
	}
	
	public int getUserId () {
		return userId;
	}
}
