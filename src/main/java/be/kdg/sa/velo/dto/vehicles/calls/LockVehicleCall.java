package be.kdg.sa.velo.dto.vehicles.calls;

import be.kdg.sa.velo.dto.vehicles.VehicleCall;


public abstract class LockVehicleCall extends VehicleCall {
	
	private int userId;
	private boolean defect;
	
	protected LockVehicleCall (int vehicleId, int userId, boolean defect) {
		super (vehicleId);
		this.userId = userId;
		this.defect = defect;
	}
	
	public LockVehicleCall () {
	}
	
	public int getUserId () {
		return userId;
	}
	
	public void setUserId (int userId) {
		this.userId = userId;
	}
	
	public boolean isDefect () {
		return defect;
	}
	
	public void setDefect (boolean defect) {
		this.defect = defect;
	}
	
}
