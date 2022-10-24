package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.subscriptions.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Jonas Leijzen
 * 2/10/2022
 */
@Repository
public interface SubscriptionJpaRepository extends JpaRepository<Subscription, Integer> {
	
	@Query ("SELECT s FROM Subscriptions s left outer JOIN FETCH s.subscriptionType WHERE s.user.id = :userId")
	List<Subscription> getActiveSubscriptionsByUserId (int userId);
	
}

