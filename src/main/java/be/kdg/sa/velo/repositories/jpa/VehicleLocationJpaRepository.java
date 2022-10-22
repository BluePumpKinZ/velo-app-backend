package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Jonas Leijzen
 * 22/10/2022
 */
public interface VehicleLocationJpaRepository extends JpaRepository<VehicleLocation, Integer> {
	
	List<VehicleLocation> findAllByVehicleId(int vehicleId);
	
}
