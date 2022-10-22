package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.vehicles.Vehicle;
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
	
	// Select new be.kdg.sa.velo.domain.vehicles.VehicleLocation (vl.latitude, vl.longitude)
	@Query (value = "SELECT TOP(1) * FROM RIDES " +
			"WHERE ENDTIME IS NULL " +
			"AND VEHICLEID = ?1 " +
			"ORDER BY STARTTIME DESC", nativeQuery = true)
	Optional<Ride> getLastRideForVehicle (int vehicleId);
	
	@Query (value = "SELECT V.VEHICLEID, SERIALNUMBER, BIKELOTID, LASTMAINTENANCEON, POINT FROM Vehicles V " +
			"JOIN Rides R ON V.VEHICLEID = R.VEHICLEID " +
			"WHERE R.ENDTIME IS NULL " +
			"AND R.STARTTIME = (SELECT MAX(STARTTIME) FROM RIDES R1" +
			"   WHERE R1.SUBSCRIPTIONID IS NULL" +
			"   AND R1.VEHICLEID = R.VEHICLEID)", nativeQuery = true)
	List<Vehicle> getVehiclesInMaintenance ();
}
