package be.kdg.sa.velo.repositories;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import java.util.List;

/**
 * Jonas Leijzen
 * 23/09/2022
 */

public interface VehicleRepository {
	
	List<Vehicle> readAllVehicles ();
	
}
