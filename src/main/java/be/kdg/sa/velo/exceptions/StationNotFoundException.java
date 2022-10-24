package be.kdg.sa.velo.exceptions;

/**
 * Jonas Leijzen
 * 10/10/2022
 */
public class StationNotFoundException extends DomainObjectNotFoundException {
	
	public StationNotFoundException (int id) {
		super (id, StationNotFoundException.class);
	}
	
	public StationNotFoundException (int id, Exception exception) {
		super (id, StationNotFoundException.class, exception);
	}
}
