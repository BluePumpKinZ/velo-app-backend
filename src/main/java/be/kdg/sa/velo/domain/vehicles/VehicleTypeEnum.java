package be.kdg.sa.velo.domain.vehicles;


public enum VehicleTypeEnum {
	VELO_BIKE (true),
	VELO_EBIKE (true),
	STEP (false),
	SCOOTER (false);
	
	private final boolean isDocked;
	
	VehicleTypeEnum (boolean isDocked) {
		this.isDocked = isDocked;
	}
	
	public boolean isDocked () {
		return isDocked;
	}
}
