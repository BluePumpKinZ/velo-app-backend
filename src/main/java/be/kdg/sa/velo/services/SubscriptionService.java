package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.subscriptions.Subscription;
import be.kdg.sa.velo.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

/**
 * Jonas Leijzen
 * 2/10/2022
 */
@Service
public class SubscriptionService {
	
	private final SubscriptionRepository subscriptionRepository;
	
	public SubscriptionService (SubscriptionRepository subscriptionRepository) {
		this.subscriptionRepository = subscriptionRepository;
	}
	
	public Subscription getCurrenctUserSubscription (int userId) {
		return subscriptionRepository.getActiveSubscriptionByUserId (userId);
	}
	
}
