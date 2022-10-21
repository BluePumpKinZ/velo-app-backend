package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Jonas Leijzen
 * 29/09/2022
 */
@Repository
public interface VehicleLocationJpaRepository extends JpaRepository<VehicleLocation, Integer> {
	
	@Query (value = "SELECT * FROM VEHICLELOCATIONS WHERE VEHICLEID = ?1" +
			"ORDER BY TIME", nativeQuery = true)
	Point findLatestLocationByVehicleId (int vehicleId);
}
