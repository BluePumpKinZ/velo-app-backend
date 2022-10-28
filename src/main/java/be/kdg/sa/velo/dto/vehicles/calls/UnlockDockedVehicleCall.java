package be.kdg.sa.velo.dto.vehicles.calls;


public final class UnlockDockedVehicleCall extends UnlockVehicleCall {
	
	private int stationId;
	
	public UnlockDockedVehicleCall (int userId, int stationId) {
		super (0, userId);
		this.stationId = stationId;
	}
	
	public UnlockDockedVehicleCall () {
	}
	
	public int getStationId () {
		return stationId;
	}
	
	public void setStationId (int stationId) {
		this.stationId = stationId;
	}
	
}
