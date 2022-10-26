package be.kdg.sa.velo.repositories;

import be.kdg.sa.velo.repositories.jpa.LockJpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LockRepository extends LockJpaRepository {
}
