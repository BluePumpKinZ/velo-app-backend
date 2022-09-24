package be.kdg.sa.velo.domain.vehicles;

import be.kdg.sa.velo.domain.stations.Lock;

import java.time.LocalDate;

/**
 * Jonas Leijzen
 * 23/09/2022
 */
public class DockedVehicle extends Vehicle {
	private Lock lock;
	
	public DockedVehicle (long id, String serialNumber, int cityId, VehicleType type, String image, boolean isAvailable, LocalDate lastServiceDate, Lock lock) {
		super (id, serialNumber, cityId, type, image, isAvailable, lastServiceDate);
		setLock(lock);
	}
	
	public Lock getLock() {
		return lock;
	}

	public void setLock(Lock lock) {
		this.lock = lock;
	}
}
