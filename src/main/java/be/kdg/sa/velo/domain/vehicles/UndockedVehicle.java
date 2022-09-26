package be.kdg.sa.velo.domain.vehicles;

import java.time.LocalDate;

/**
 * Jonas Leijzen
 * 23/09/2022
 */
public class UndockedVehicle extends Vehicle {
	public UndockedVehicle (long id, String serialNumber, VehicleType type) {
		super (id, serialNumber, type);
	}
}
