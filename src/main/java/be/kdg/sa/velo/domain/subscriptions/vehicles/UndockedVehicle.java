package be.kdg.sa.velo.domain.subscriptions.vehicles;

import java.time.LocalDate;
import java.util.Date;

/**
 * Jonas Leijzen
 * 23/09/2022
 */
public class UndockedVehicle extends Vehicle {
	public UndockedVehicle (long id, String serialNumber, int cityId, VehicleType type, String image, boolean isAvailable, LocalDate lastServiceDate) {
		super (id, serialNumber, cityId, type, image, isAvailable, lastServiceDate);
	}
}
