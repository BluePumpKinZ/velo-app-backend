package be.kdg.sa.velo.dto.vehicles.calls;


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

