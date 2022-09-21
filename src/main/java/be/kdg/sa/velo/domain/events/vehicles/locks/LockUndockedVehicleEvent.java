package be.kdg.sa.velo.domain.events.vehicles.locks;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
public class LockUndockedVehicleEvent extends LockVehicleEvent {
	
	private final double latitude;
	private final double longitude;
	
	protected LockUndockedVehicleEvent (long vehicleId, long userId, boolean defect, double latitude, double longitude) {
		super (vehicleId, userId, defect);
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

