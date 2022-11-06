package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.maintenance.MaintenanceAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceActionJpaRepository extends JpaRepository<MaintenanceAction, Integer> {

}