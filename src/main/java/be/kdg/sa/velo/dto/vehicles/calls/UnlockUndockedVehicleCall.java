package be.kdg.sa.velo.dto.vehicles.calls;


public final class UnlockUndockedVehicleCall extends UnlockVehicleCall {
	
	private double latitude;
	private double longitude;
	public UnlockUndockedVehicleCall (int vehicleId, int userId, double latitude, double longitude) {
		super (vehicleId, userId);
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public UnlockUndockedVehicleCall () {
	}
	
	public double getLatitude () {
		return latitude;
	}
	
	public void setLatitude (double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude () {
		return longitude;
	}
	
	public void setLongitude (double longitude) {
		this.longitude = longitude;
	}
	
}

