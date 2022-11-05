package be.kdg.sa.velo.exceptions;

public class MaintenanceNotFoundException extends DomainObjectNotFoundException {

	public MaintenanceNotFoundException (int id) {
		super (id, MaintenanceNotFoundException.class);
	}

	public MaintenanceNotFoundException (int id, Exception exception) {
		super (id, MaintenanceNotFoundException.class, exception);
	}
}