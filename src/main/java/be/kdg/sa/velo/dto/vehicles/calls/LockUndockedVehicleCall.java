package be.kdg.sa.velo.dto.vehicles.calls;


public final class LockUndockedVehicleCall extends LockVehicleCall {
	
	private double latitude;
	private double longitude;
	
	public LockUndockedVehicleCall (int vehicleId, int userId, boolean defect, double latitude, double longitude) {
		super (vehicleId, userId, defect);
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public LockUndockedVehicleCall () {
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

