package be.kdg.sa.velo.dto.vehicles;


public abstract class VehicleCall {
	
	private int vehicleId;
	
	protected VehicleCall (int vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	public VehicleCall () {
	}
	
	public int getVehicleId () {
		return vehicleId;
	}
	
	public void setVehicleId (int vehicleId) {
		this.vehicleId = vehicleId;
	}
}
