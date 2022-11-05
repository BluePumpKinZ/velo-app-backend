package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.models.maintenance.MaintenanceVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceJpaRepository extends JpaRepository<MaintenanceVehicle, Integer> {
	
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
