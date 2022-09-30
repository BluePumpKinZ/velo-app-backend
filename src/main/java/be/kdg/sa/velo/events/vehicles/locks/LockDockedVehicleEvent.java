package be.kdg.sa.velo.events.vehicles.locks;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
public class LockDockedVehicleEvent extends LockVehicleEvent{
	
	private final int stationId;
	private final int lockId;
	
	protected LockDockedVehicleEvent (int vehicleId, int userId, boolean defect, int stationId, int lockId) {
		super (vehicleId, userId, defect);
		this.stationId = stationId;
		this.lockId = lockId;
	}
	
	public int getStationId () {
		return stationId;
	}
	
	public int getLockId () {
		return lockId;
	}
}
