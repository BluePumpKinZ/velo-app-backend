package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Jonas Leijzen
 * 22/10/2022
 */
@Repository
public interface VehicleLocationJpaRepository extends JpaRepository<VehicleLocation, Integer> {
	
	List<VehicleLocation> findAllByVehicleId(int vehicleId);
	
	@Query (value = "SELECT VL.VehicleLocationId, VL.Location, VL.Timestamp, VL.VehicleId FROM VehicleLocations VL\n" +
			"JOIN RIDES R ON VL.VEHICLEID = R.VEHICLEID\n" +
			"WHERE VL.TIMESTAMP BETWEEN R.STARTTIME AND R.ENDTIME", nativeQuery = true)
	List<VehicleLocation> getVehicleLocationsByRideId (int rideId);
}
