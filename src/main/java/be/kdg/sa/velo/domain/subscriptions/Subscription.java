package be.kdg.sa.velo.domain.subscriptions;

import be.kdg.sa.velo.domain.users.User;

/**
 * @author Maxim Derboven
 * @version 1.0 20/09/2022 12:48
 */
public class Subscription {
	private final long id;
	private User user;
	private SubscriptionType subscriptionType;
	private long startDate;
	private boolean isActive;
	
public Subscription (long id, User user, SubscriptionType subscriptionType, long startDate, boolean isActive) {
		this.id = id;
		setUser (user);
		setSubscriptionType (subscriptionType);
		setStartDate (startDate);
		setActive (isActive);
	}
	
	public long getId () {
		return id;
	}
	
	public User getUser () {
		return user;
	}
	
	public void setUser (User user) {
		this.user = user;
	}
	
	public SubscriptionType getSubscriptionType () {
		return subscriptionType;
	}
	
	public void setSubscriptionType (SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	
	public long getStartDate () {
		return startDate;
	}
	
	public void setStartDate (long startDate) {
		this.startDate = startDate;
	}
	
	public boolean isActive () {
		return isActive;
	}
	
	public void setActive (boolean active) {
		isActive = active;
	}
	
}
