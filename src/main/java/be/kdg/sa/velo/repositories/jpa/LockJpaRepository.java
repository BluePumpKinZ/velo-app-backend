package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.stations.Lock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Jonas Leijzen
 * 28/09/2022
 */
public interface LockJpaRepository extends JpaRepository<Lock, Integer> {
	
	List<Lock> getLocksByStationIdAndVehicleIsNull (int stationId);
	
}
