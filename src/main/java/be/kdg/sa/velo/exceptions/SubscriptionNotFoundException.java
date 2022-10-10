package be.kdg.sa.velo.exceptions;

/**
 * Jonas Leijzen
 * 10/10/2022
 */
public class SubscriptionNotFoundException extends DomainObjectNotFoundException {
	
	public SubscriptionNotFoundException (int id) {
		super (id, SubscriptionNotFoundException.class);
	}
	
	public SubscriptionNotFoundException (int id, Exception exception) {
		super (id, SubscriptionNotFoundException.class, exception);
	}
}

