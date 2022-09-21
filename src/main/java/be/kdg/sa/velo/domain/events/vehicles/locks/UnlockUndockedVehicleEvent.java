package be.kdg.sa.velo.domain.events.vehicles.locks;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
public class UnlockUndockedVehicleEvent extends UnlockVehicleEvent {
	
	public UnlockUndockedVehicleEvent (long vehicleId, long userId) {
		super (vehicleId, userId);
	}
}

