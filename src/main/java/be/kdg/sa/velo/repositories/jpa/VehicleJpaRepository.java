package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.domain.vehicles.VehicleType;
import be.kdg.sa.velo.models.maintenance.MaintenanceVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


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

	@Query (value = """
			SELECT BT FROM BikeTypes BT
			JOIN Bikelots BL on BT = BL.type
			JOIN Vehicles V on V.lot = BL
			WHERE V.id = :vehicleId
			""")
	VehicleType getVehicleType (int vehicleId);

	@Query (value = """
			SELECT V.VEHICLEID FROM VEHICLES V
			JOIN BIKELOTS B ON B.BIKELOTID = V.BIKELOTID
			WHERE B.BIKETYPEID IN (3, 4)
			ORDER BY V.VEHICLEID
			""", nativeQuery = true)
	List<Integer> getValidSimulatorVehicleIds ();
	
	@Query (value = """
			SELECT new be.kdg.sa.velo.models.maintenance.MaintenanceVehicle (V.id, V.serialNumber, BT.description) FROM Vehicles V
			JOIN Bikelots BL ON V.lot = BL
			JOIN BikeTypes BT ON BL.type = BT
			WHERE V.id IN
			(SELECT MF.vehicle.id FROM MaintenanceFlaggings MF
			WHERE MF.id NOT IN (SELECT MA.flagging.id FROM MaintenanceActions MA))
						""")
	List<MaintenanceVehicle> getVehiclesInMaintenance ();
}
