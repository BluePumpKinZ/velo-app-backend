package be.kdg.sa.velo.exceptions;

public class SubscriptionNotFoundException extends DomainObjectNotFoundException {
	
	public SubscriptionNotFoundException (int id) {
		super (id, SubscriptionNotFoundException.class);
	}
	
	public SubscriptionNotFoundException (int id, Exception exception) {
		super (id, SubscriptionNotFoundException.class, exception);
	}
}

