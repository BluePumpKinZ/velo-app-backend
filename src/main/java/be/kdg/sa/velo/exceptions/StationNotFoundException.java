package be.kdg.sa.velo.exceptions;


public class StationNotFoundException extends DomainObjectNotFoundException {
	
	public StationNotFoundException (int id) {
		super (id, StationNotFoundException.class);
	}
	
	public StationNotFoundException (int id, Exception exception) {
		super (id, StationNotFoundException.class, exception);
	}
}
