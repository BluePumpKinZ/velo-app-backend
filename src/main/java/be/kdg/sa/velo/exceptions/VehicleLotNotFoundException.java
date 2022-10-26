package be.kdg.sa.velo.exceptions;

import be.kdg.sa.velo.domain.vehicles.VehicleLot;


public class VehicleLotNotFoundException extends DomainObjectNotFoundException {
	
	public VehicleLotNotFoundException (int id) {
		super (id, VehicleLot.class);
	}
	
	public VehicleLotNotFoundException (int id, Exception exception) {
		super (id, VehicleLot.class, exception);
	}
}
