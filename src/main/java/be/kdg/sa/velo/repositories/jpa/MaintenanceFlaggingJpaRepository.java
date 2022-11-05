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
			SELECT new be.kdg.sa.velo.models.maintenance.MaintenanceFlag(MF.id, V.serialNumber, BT.description, MF.reason)
			FROM MaintenanceFlaggings MF
			         INNER JOIN Vehicles V ON MF.vehicle.id = V.id
			         Inner Join Bikelots B on V.lot.id = B.id
			         Inner Join BikeTypes BT on B.type.id = BT.id
			WHERE MF.id NOT IN (SELECT MA.flagging.id FROM MaintenanceActions MA)
			""", nativeQuery = false)
	List<MaintenanceFlag> getMaintenanceFlaggings();
}
