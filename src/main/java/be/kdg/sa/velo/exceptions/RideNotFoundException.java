package be.kdg.sa.velo.exceptions;

/**
 * Jonas Leijzen
 * 10/10/2022
 */
public class RideNotFoundException extends DomainObjectNotFoundException {
	
	public RideNotFoundException (int id) {
		super (id, RideNotFoundException.class);
	}
	
	public RideNotFoundException (int id, Exception exception) {
		super (id, RideNotFoundException.class, exception);
	}
}

