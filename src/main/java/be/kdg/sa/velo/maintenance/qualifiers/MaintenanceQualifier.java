package be.kdg.sa.velo.maintenance.qualifiers;


public interface MaintenanceQualifier {
	
	boolean isMaintenanceNeeded (MaintenanceQualifyContext context);

	String getReason();
	
}
