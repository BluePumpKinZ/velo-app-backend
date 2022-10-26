package be.kdg.sa.velo.exceptions;


public abstract class DomainObjectNotFoundException extends RuntimeException {
	
	private final int id;
	
	public <T>DomainObjectNotFoundException (int id, Class<T> clazz) {
		super(clazz.getName () + " with id " + id + " not found");
		this.id = id;
	}
	
	public <T>DomainObjectNotFoundException (int id, Class<T> clazz, Exception exception) {
		super(clazz.getName () + " with id " + id + " not found", exception);
		this.id = id;
	}
	
	public int getId () {
		return id;
	}
}
