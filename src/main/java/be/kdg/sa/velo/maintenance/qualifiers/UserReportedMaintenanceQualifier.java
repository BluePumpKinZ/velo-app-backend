package be.kdg.sa.velo.maintenance.qualifiers;

import org.springframework.stereotype.Component;


@Component
public class UserReportedMaintenanceQualifier implements MaintenanceQualifier {
	
	@Override
	public boolean isMaintenanceNeeded (MaintenanceQualifyContext context) {
		return context.getEvent ().isDefect ();
	}
}
