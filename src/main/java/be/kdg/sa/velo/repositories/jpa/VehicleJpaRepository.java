package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Jonas Leijzen
 * 23/09/2022
 */
@Repository
public interface VehicleJpaRepository extends JpaRepository<Vehicle, Integer> {
	
	@Query (value = "SELECT V.VEHICLEID, V.SERIALNUMBER, V.BIKELOTID, V.LASTMAINTENANCEON, V.LOCKID, V.POINT FROM VEHICLES V\n" +
			"JOIN VEHICLELOCATIONS VL ON V.VEHICLEID = VL.VEHICLEID\n" +
			"ORDER BY geography::STGeomFromText((?1).ToString(), 4326)" +
			".STDistance(geography::STGeomFromText(VL.LOCATION))", nativeQuery = true)
	Vehicle findClosestVehicle (Point point);
}
