package be.kdg.sa.velo.events.vehicles.locks;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
public class UnlockDockedVehicleEvent extends UnlockVehicleEvent {
	
	private final long stationId;
	
	public UnlockDockedVehicleEvent (long userId, long stationId) {
		super (0, userId);
		this.stationId = stationId;
	}
	
	public long getStationId () {
		return stationId;
	}
}
