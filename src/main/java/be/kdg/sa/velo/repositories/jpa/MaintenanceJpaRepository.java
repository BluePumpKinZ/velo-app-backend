package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.maintenance.MaintenanceFlagging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceJpaRepository extends JpaRepository<MaintenanceFlagging, Integer> {

	
}
