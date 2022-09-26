package be.kdg.sa.velo.domain.vehicles;

import be.kdg.sa.velo.domain.stations.Lock;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;

/**
 * Jonas Leijzen
 * 23/09/2022
 */
@Entity
public class DockedVehicle extends Vehicle {
	@OneToOne(optional = true)
	private Lock lock;
	
	public DockedVehicle () {
	}
	
	public DockedVehicle (long id, String serialNumber, VehicleType type, Lock lock) {
		super (id, serialNumber, type);
		setLock(lock);
	}
	
	public Lock getLock() {
		return lock;
	}

	public void setLock(Lock lock) {
		this.lock = lock;
	}
}
