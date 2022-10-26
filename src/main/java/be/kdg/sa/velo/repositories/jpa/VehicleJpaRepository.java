package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.domain.vehicles.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleJpaRepository extends JpaRepository<Vehicle, Integer> {
	
	@Query (value = """
			SELECT TOP(1) * FROM VEHICLES
			ORDER BY POINT.STDistance(CONCAT('POINT(', CONCAT(?1, CONCAT(' ', CONCAT(?2, ')')))))
			""", nativeQuery = true)
	Vehicle findClosestVehicle (double latitude, double longitude);
	
	@Query(value = "SELECT BK.BIKETYPEDESCRIPTION FROM BikeTypes BK\n" +
			"JOIN Bikelots BL ON BK.BIKETYPEID = BL.BIKETYPEID\n" +
			"JOIN VEHICLES V ON BL.BIKELOTID = V.BIKELOTID\n" +
			"WHERE V.VEHICLEID = ?1", nativeQuery = true)
	VehicleType getVehicleType (int vehicleId);
}
