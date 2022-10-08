package be.kdg.sa.velo.models.vehicles.calls;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
public final class LockDockedVehicleCall extends LockVehicleCall {
	private final int lockId;
	
	public LockDockedVehicleCall (int vehicleId, int userId, boolean defect, int lockId) {
		super (vehicleId, userId, defect);
		this.lockId = lockId;
	}
	
	public int getLockId () {
		return lockId;
	}
}
