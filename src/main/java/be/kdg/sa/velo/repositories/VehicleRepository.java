package be.kdg.sa.velo.repositories;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Jonas Leijzen
 * 23/09/2022
 */

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	
}
