package be.kdg.sa.velo.exceptions;

import be.kdg.sa.velo.domain.vehicles.Vehicle;

/**
 * Jonas Leijzen
 * 10/10/2022
 */
public class VehicleNotFoundException extends DomainObjectNotFoundException {
	
	public VehicleNotFoundException (int id) {
		super (id, Vehicle.class);
	}
	
	public VehicleNotFoundException (int id, Exception exception) {
		super (id, Vehicle.class, exception);
	}
}
