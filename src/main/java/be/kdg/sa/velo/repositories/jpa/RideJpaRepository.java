package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.rides.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Jonas Leijzen
 * 2/10/2022
 */
public interface RideJpaRepository extends JpaRepository<Ride, Integer> {

}
