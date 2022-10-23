package be.kdg.sa.velo.maintenance.qualifiers;

import be.kdg.sa.velo.domain.vehicles.VehicleTypeEnum;
import be.kdg.sa.velo.models.vehicles.calls.LockVehicleCall;

/**
 * Jonas Leijzen
 * 22/10/2022
 */
public interface MaintenanceQualifyContext {
	int getVehicleId ();
	LockVehicleCall getEvent ();
	VehicleTypeEnum getVehicleTypeEnum ();
}
