package be.kdg.sa.velo.models.vehicles;


public class ClosestVehicle {
	
	public int vehicleId;
	public String serialNumber;
	public double latitude;
	public double longitude;
	
	public ClosestVehicle (int vehicleId, String serialNumber, double latitude, double longitude) {
		this.vehicleId = vehicleId;
		this.serialNumber = serialNumber;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
