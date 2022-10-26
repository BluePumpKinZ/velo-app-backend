package be.kdg.sa.velo.maintenance.qualifiers;

import be.kdg.sa.velo.domain.vehicles.VehicleTypeEnum;
import be.kdg.sa.velo.dto.vehicles.calls.LockVehicleCall;


public interface MaintenanceQualifyContext {
	int getVehicleId ();
	LockVehicleCall getEvent ();
	VehicleTypeEnum getVehicleTypeEnum ();
}
