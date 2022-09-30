package be.kdg.sa.velo.events.vehicles.locks;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
public class UnlockDockedVehicleEvent extends UnlockVehicleEvent {
	
	private final int stationId;
	
	public UnlockDockedVehicleEvent (int userId, int stationId) {
		super (0, userId);
		this.stationId = stationId;
	}
	
	public int getStationId () {
		return stationId;
	}
}
