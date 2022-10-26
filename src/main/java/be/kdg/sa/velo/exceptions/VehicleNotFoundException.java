package be.kdg.sa.velo.exceptions;

import be.kdg.sa.velo.domain.vehicles.Vehicle;


public class VehicleNotFoundException extends DomainObjectNotFoundException {
	
	public VehicleNotFoundException (int id) {
		super (id, Vehicle.class);
	}
	
	public VehicleNotFoundException (int id, Exception exception) {
		super (id, Vehicle.class, exception);
	}
}
