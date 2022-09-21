package be.kdg.sa.velo.events.vehicles;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
public abstract class VehicleEvent {
	
	private final long vehicleId;
	
	protected VehicleEvent (long vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	public long getVehicleId () {
		return vehicleId;
	}
}
