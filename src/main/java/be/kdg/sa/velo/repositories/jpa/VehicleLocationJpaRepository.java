package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Jonas Leijzen
 * 29/09/2022
 */
public interface VehicleLocationJpaRepository extends JpaRepository<VehicleLocation, Integer> {

}
