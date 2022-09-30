package be.kdg.sa.velo.events.vehicles;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
public abstract class VehicleEvent {
	
	private final int vehicleId;
	
	protected VehicleEvent (int vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	public int getVehicleId () {
		return vehicleId;
	}
}
