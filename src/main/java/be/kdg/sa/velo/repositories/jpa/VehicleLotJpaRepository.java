package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.vehicles.VehicleLot;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Jonas Leijzen
 * 2/10/2022
 */
public interface VehicleLotJpaRepository extends JpaRepository<VehicleLot, Integer> {

}
