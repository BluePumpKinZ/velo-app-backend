package be.kdg.sa.velo.dto.vehicles.calls;

import be.kdg.sa.velo.dto.vehicles.VehicleCall;


public abstract class UnlockVehicleCall extends VehicleCall {
	
	private int userId;
	
	protected UnlockVehicleCall (int vehicleId, int userId) {
		super (vehicleId);
		this.userId = userId;
	}
	
	public UnlockVehicleCall () {
	}
	
	public int getUserId () {
		return userId;
	}
	
	public void setUserId (int userId) {
		this.userId = userId;
	}
}
