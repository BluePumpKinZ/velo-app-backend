package be.kdg.sa.velo.exceptions;


public class LockNotFoundException extends DomainObjectNotFoundException {
	
	public LockNotFoundException (int id) {
		super (id, LockNotFoundException.class);
	}
	
	public LockNotFoundException (int id, Exception exception) {
		super (id, LockNotFoundException.class, exception);
	}
}
