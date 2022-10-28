package be.kdg.sa.velo.dto.vehicles.calls;


public final class LockDockedVehicleCall extends LockVehicleCall {
	private int lockId;
	
	public LockDockedVehicleCall (int vehicleId, int userId, boolean defect, int lockId) {
		super (vehicleId, userId, defect);
		this.lockId = lockId;
	}
	
	public LockDockedVehicleCall () {
	}
	
	public int getLockId () {
		return lockId;
	}
	
	public void setLockId (int lockId) {
		this.lockId = lockId;
	}
}
