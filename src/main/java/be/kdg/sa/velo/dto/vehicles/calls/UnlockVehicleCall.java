package be.kdg.sa.velo.dto.vehicles.calls;

import be.kdg.sa.velo.dto.vehicles.VehicleCall;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
public abstract class UnlockVehicleCall extends VehicleCall {
	
	private final int userId;
	
	protected UnlockVehicleCall (int vehicleId, int userId) {
		super (vehicleId);
		this.userId = userId;
	}
	
	public int getUserId () {
		return userId;
	}
}
