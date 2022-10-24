package be.kdg.sa.velo.maintenance.qualifiers;

import org.springframework.stereotype.Component;

/**
 * Jonas Leijzen
 * 23/10/2022
 */
@Component
public class UserReportedMaintenanceQualifier implements MaintenanceQualifier {
	
	@Override
	public boolean isMaintenanceNeeded (MaintenanceQualifyContext context) {
		return context.getEvent ().isDefect ();
	}
}
