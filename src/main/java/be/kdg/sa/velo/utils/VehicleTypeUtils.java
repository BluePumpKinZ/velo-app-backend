package be.kdg.sa.velo.utils;

import be.kdg.sa.velo.domain.vehicles.VehicleType;
import be.kdg.sa.velo.domain.vehicles.VehicleTypeEnum;


public class VehicleTypeUtils {
	
	public static VehicleTypeEnum getVehicleTypeEnum (VehicleType vehicleType) {
		return switch (vehicleType.getDescription ()) {
			case "Velo Bike" -> VehicleTypeEnum.VELO_BIKE;
			case "Velo E-Bike" -> VehicleTypeEnum.VELO_EBIKE;
			case "Step" -> VehicleTypeEnum.STEP;
			case "Scooter" -> VehicleTypeEnum.SCOOTER;
			default -> throw new RuntimeException (String.format ("VehicleType '%s' not found", vehicleType.getDescription ()));
		};
	}
	
}
