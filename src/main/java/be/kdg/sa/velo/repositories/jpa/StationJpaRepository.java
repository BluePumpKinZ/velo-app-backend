package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.stations.Station;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Jonas Leijzen
 * 28/09/2022
 */
public interface StationJpaRepository extends JpaRepository<Station, Integer> {
	

	
}
