package be.kdg.sa.velo.domain;

/**
 * @author Maxim Derboven
 * @version 1.0 20/09/2022 12:48
 */
public class Subscription {
	private long id;
	private User user;
	private SubscriptionType subscriptionType;
	private long startDate;
	private boolean isActive;
}
