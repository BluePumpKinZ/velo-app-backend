package be.kdg.sa.velo.events.vehicles.locks;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
public class UnlockUndockedVehicleEvent extends UnlockVehicleEvent {
	
	public UnlockUndockedVehicleEvent (int vehicleId, int userId) {
		super (vehicleId, userId);
	}
}

