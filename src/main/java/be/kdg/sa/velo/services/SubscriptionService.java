package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.subscriptions.Subscription;
import be.kdg.sa.velo.exceptions.SubscriptionNotFoundException;
import be.kdg.sa.velo.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;


@Service
public class SubscriptionService {
	
	private final SubscriptionRepository subscriptionRepository;
	
	public SubscriptionService (SubscriptionRepository subscriptionRepository) {
		this.subscriptionRepository = subscriptionRepository;
	}
	
	public Subscription getCurrentUserSubscription (int userId) {
		var subscriptions = subscriptionRepository.getActiveSubscriptionsByUserId (userId);
		return subscriptions.stream ()
				.filter (this::isSubscriptionValid)
				.max (Comparator.comparing (Subscription::getStartDate))
				.orElseThrow (() -> new SubscriptionNotFoundException (0));
	}
	
	private boolean isSubscriptionValid (Subscription subscription) {
		return subscription.getStartDate ().isBefore (LocalDate.now ()) &&
				getSubscriptionEndDate (subscription).isAfter (LocalDate.now ());
	}
	
	private LocalDate getSubscriptionEndDate (Subscription subscription) {
		return switch (subscription.getSubscriptionType ().getDescription ()) {
			case "DAG" -> subscription.getStartDate ().plusDays (1);
			case "MAAND" -> subscription.getStartDate ().plusMonths (1);
			case "JAAR" -> subscription.getStartDate ().plusYears (1);
			default -> throw new IllegalStateException ("Unexpected value: " + subscription.getSubscriptionType ().getDescription ());
		};
	}
	
}
