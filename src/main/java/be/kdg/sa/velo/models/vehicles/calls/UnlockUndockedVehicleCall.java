package be.kdg.sa.velo.models.vehicles.calls;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
public final class UnlockUndockedVehicleCall extends UnlockVehicleCall {
	
	private final double latitude;
	private final double longitude;
	public UnlockUndockedVehicleCall (int vehicleId, int userId, double latitude, double longitude) {
		super (vehicleId, userId);
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLatitude () {
		return latitude;
	}
	
	public double getLongitude () {
		return longitude;
	}
}

