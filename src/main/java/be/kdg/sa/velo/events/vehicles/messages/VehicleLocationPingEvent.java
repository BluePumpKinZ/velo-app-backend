package be.kdg.sa.velo.events.vehicles.messages;

import be.kdg.sa.velo.events.vehicles.VehicleEvent;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
public final class VehicleLocationPingEvent extends VehicleEvent {
	
	private final long timeStamp;
	private final double latitude;
	private final double longitude;
	
	public VehicleLocationPingEvent (long timeStamp, int vehicleId, double latitude, double longitude) {
		super (vehicleId);
		this.timeStamp = timeStamp;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public long getTimeStamp () {
		return timeStamp;
	}
	
	public double getLatitude () {
		return latitude;
	}
	
	public double getLongitude () {
		return longitude;
	}
}
