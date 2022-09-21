package be.kdg.sa.velo.domain.events.vehicles.locks;

import be.kdg.sa.velo.domain.events.vehicles.VehicleEvent;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
public abstract class UnlockVehicleEvent extends VehicleEvent {
	
	private final long userId;
	
	protected UnlockVehicleEvent (long vehicleId, long userId) {
		super (vehicleId);
		this.userId = userId;
	}
	
	public long getUserId () {
		return userId;
	}
}
