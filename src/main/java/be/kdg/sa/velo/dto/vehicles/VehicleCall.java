package be.kdg.sa.velo.dto.vehicles;


public abstract class VehicleCall {
	
	private final int vehicleId;
	
	protected VehicleCall (int vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	public int getVehicleId () {
		return vehicleId;
	}
}
