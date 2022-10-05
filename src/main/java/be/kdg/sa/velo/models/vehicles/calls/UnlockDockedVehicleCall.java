package be.kdg.sa.velo.models.vehicles.calls;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
public class UnlockDockedVehicleCall extends UnlockVehicleCall {
	
	private final int stationId;
	
	public UnlockDockedVehicleCall (int userId, int stationId) {
		super (0, userId);
		this.stationId = stationId;
	}
	
	public int getStationId () {
		return stationId;
	}
}
