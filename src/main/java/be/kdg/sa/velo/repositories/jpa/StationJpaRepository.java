package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.stations.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StationJpaRepository extends JpaRepository<Station, Integer> {
	

	
}
