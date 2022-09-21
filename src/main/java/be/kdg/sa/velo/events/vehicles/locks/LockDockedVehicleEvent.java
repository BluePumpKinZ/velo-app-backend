package be.kdg.sa.velo.events.vehicles.locks;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
public class LockDockedVehicleEvent extends LockVehicleEvent{
	
	private final long stationId;
	private final long lockId;
	
	protected LockDockedVehicleEvent (long vehicleId, long userId, boolean defect, long stationId, long lockId) {
		super (vehicleId, userId, defect);
		this.stationId = stationId;
		this.lockId = lockId;
	}
	
	public long getStationId () {
		return stationId;
	}
	
	public long getLockId () {
		return lockId;
	}
}
