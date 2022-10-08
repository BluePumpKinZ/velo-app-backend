package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Jonas Leijzen
 * 2/10/2022
 */
@Repository
public interface RideJpaRepository extends JpaRepository<Ride, Integer> {
	
	@Query (value = "SELECT VL.LATITUDE, VL.LONGITUDE FROM RIDES R " +
			"LEFT JOIN VEHICLES V ON R.VEHICLEID = V.VEHICLEID " +
			"LEFT JOIN VEHICLELOCATIONS VL ON V.VEHICLEID = VL.VEHICLEID " +
			"WHERE VL.TIMESTAMP BETWEEN R.STARTTIME AND R.ENDTIME " +
			"AND R.RIDEID = ?1 " +
			"ORDER BY VL.TIMESTAMP", nativeQuery = true)
	List<VehicleLocation> getVehicleLocationsForRide (int rideId);
	
	@Query (value = "SELECT TOP(1) * FROM RIDES " +
			"WHERE ENDTIME IS NULL " +
			"AND VEHICLEID = ?1 " +
			"ORDER BY STARTTIME DESC", nativeQuery = true)
	Optional<Ride> getLastRideForVehicle (int vehicleId);
}
