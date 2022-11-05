package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.maintenance.MaintenanceAction;
import be.kdg.sa.velo.models.maintenance.MaintenanceFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Maxim Derboven
 * @version 1.0 5/11/2022 12:59
 */
@Repository
public interface MaintenanceActionJpaRepository extends JpaRepository<MaintenanceAction, Integer> {

}