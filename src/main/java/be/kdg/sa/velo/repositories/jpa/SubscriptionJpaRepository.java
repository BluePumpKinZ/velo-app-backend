package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.subscriptions.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Jonas Leijzen
 * 2/10/2022
 */
public interface SubscriptionJpaRepository extends JpaRepository<Subscription, Integer> {
	
	@Query (value = "SELECT * FROM subscriptions WHERE UserId = ?1 ORDER BY VALIDFROM DESC FETCH FIRST 1 ROW ONLY", nativeQuery = true)
	Subscription getActiveSubscriptionByUserId (int userId);
	
}

