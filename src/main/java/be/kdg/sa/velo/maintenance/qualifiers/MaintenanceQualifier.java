package be.kdg.sa.velo.maintenance.qualifiers;

/**
 * Jonas Leijzen
 * 22/10/2022
 */
public interface MaintenanceQualifier {
	
	boolean isMaintenanceNeeded (MaintenanceQualifyContext context);
	
}
