package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.rides.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Jonas Leijzen
 * 2/10/2022
 */
@Repository
public interface RideJpaRepository extends JpaRepository<Ride, Integer> {
	
	// Select new be.kdg.sa.velo.domain.vehicles.VehicleLocation (vl.latitude, vl.longitude)
	@Query (value = "SELECT TOP(1) * FROM RIDES " +
			"WHERE ENDTIME IS NULL " +
			"AND VEHICLEID = ?1 " +
			"ORDER BY STARTTIME DESC", nativeQuery = true)
	Optional<Ride> getLastRideForVehicle (int vehicleId);
}
