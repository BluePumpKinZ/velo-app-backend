package be.kdg.sa.velo.dto.vehicles;

/**
 * Jonas Leijzen
 * 21/09/2022
 */
public abstract class VehicleCall {
	
	private final int vehicleId;
	
	protected VehicleCall (int vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	public int getVehicleId () {
		return vehicleId;
	}
}
