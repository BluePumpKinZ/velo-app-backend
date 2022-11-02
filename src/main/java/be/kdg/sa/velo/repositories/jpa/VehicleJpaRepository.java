package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.domain.vehicles.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleJpaRepository extends JpaRepository<Vehicle, Integer> {
	
	String findClosestVehicleQuery = """
			SELECT TOP(1) V.VEHICLEID, V.SERIALNUMBER, V.BIKELOTID, V.LASTMAINTENANCEON, V.LOCKID, V.POINT FROM VEHICLES V
			JOIN BIKELOTS BL ON BL.BIKELOTID = V.BIKELOTID
			WHERE BL.BIKETYPEID IN (3, 4)
			ORDER BY Geography\\:\\:STGeomFromText(V.Point.MakeValid().STAsText(),4326)
			.STDistance(Geography\\:\\:STGeomFromText(Geometry\\:\\:Point(?1,?2,4326).MakeValid().STAsText(),4326))
			""";
	
	@Query (value = findClosestVehicleQuery, nativeQuery = true)
	Vehicle findClosestVehicle (double latitude, double longitude);
	
	@Query (value = "SELECT BK.BIKETYPEDESCRIPTION FROM BIKETYPES BK\n" +
			"JOIN BIKELOTS BL ON BK.BIKETYPEID = BL.BIKETYPEID\n" +
			"JOIN VEHICLES V ON BL.BIKELOTID = V.BIKELOTID\n" +
			"WHERE V.VEHICLEID = ?1", nativeQuery = true)
	VehicleType getVehicleType (int vehicleId);
}
