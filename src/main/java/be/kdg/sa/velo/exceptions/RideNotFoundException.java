package be.kdg.sa.velo.exceptions;


public class RideNotFoundException extends DomainObjectNotFoundException {
	
	public RideNotFoundException (int id) {
		super (id, RideNotFoundException.class);
	}
	
	public RideNotFoundException (int id, Exception exception) {
		super (id, RideNotFoundException.class, exception);
	}
}

