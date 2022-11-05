package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.maintenance.MaintenanceAction;
import be.kdg.sa.velo.domain.maintenance.MaintenanceFlagging;
import be.kdg.sa.velo.models.maintenance.MaintenanceFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceFlaggingJpaRepository extends JpaRepository<MaintenanceFlagging, Integer> {
	@Query(value = """
			SELECT new be.kdg.sa.velo.models.maintenance.MaintenanceFlag(MF.id, V.serialNumber, BT.description, MF.reason) FROM Vehicles V
			JOIN Bikelots BL ON V.lot = BL
			JOIN BikeTypes BT ON BL.type = BT
			JOIN MaintenanceFlaggings MF on V.id = MF.vehicle.id
			WHERE V.id IN
			(SELECT MF.vehicle.id FROM MaintenanceFlaggings MF
			WHERE MF.id NOT IN (SELECT MA.flagging.id FROM MaintenanceActions MA))
						""")
	List<MaintenanceFlag> getMaintenanceFlaggings ();
}
