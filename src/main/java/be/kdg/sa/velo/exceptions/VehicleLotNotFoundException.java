package be.kdg.sa.velo.exceptions;

import be.kdg.sa.velo.domain.vehicles.VehicleLot;

/**
 * Jonas Leijzen
 * 23/10/2022
 */
public class VehicleLotNotFoundException extends DomainObjectNotFoundException {
	
	public VehicleLotNotFoundException (int id) {
		super (id, VehicleLot.class);
	}
	
	public VehicleLotNotFoundException (int id, Exception exception) {
		super (id, VehicleLot.class, exception);
	}
}
