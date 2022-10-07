package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Jonas Leijzen
 * 29/09/2022
 */
@Repository
public interface VehicleLocationJpaRepository extends JpaRepository<VehicleLocation, Integer> {

}
