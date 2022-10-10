package be.kdg.sa.velo.exceptions;

/**
 * Jonas Leijzen
 * 10/10/2022
 */
public class LockNotFoundException extends DomainObjectNotFoundException {
	
	public LockNotFoundException (int id) {
		super (id, LockNotFoundException.class);
	}
	
	public LockNotFoundException (int id, Exception exception) {
		super (id, LockNotFoundException.class, exception);
	}
}
