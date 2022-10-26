package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.stations.Lock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LockJpaRepository extends JpaRepository<Lock, Integer> {
	
	List<Lock> getLocksByStationIdAndVehicleIsNull (int stationId);
	
	List<Lock> getLocksByStationIdAndVehicleIsNotNull (int stationId);
	
	@Query(value = "SELECT TOP(1) L.LOCKID, L.STATIONLOCKNR, L.STATIONID, L.VEHICLEID FROM LOCKS L\n" +
			"JOIN STATIONS S ON L.STATIONID = S.STATIONID\n" +
			"ORDER BY S.GPSCOORD.STDistance(CONCAT('POINT(', CONCAT(?1, CONCAT(' ', CONCAT(?2, ')')))))", nativeQuery = true)
	Optional<Lock> getClosestLock (double x, double y);
	
}
