package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Jonas Leijzen
 * 23/09/2022
 */
@Repository
public interface VehicleJpaRepository extends JpaRepository<Vehicle, Integer> {
	
}
