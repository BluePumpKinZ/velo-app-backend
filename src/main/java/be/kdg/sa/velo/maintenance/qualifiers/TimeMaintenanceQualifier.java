package be.kdg.sa.velo.maintenance.qualifiers;

import be.kdg.sa.velo.exceptions.VehicleNotFoundException;
import be.kdg.sa.velo.repositories.VehicleRepository;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;


@Component
public class TimeMaintenanceQualifier implements MaintenanceQualifier {
	
	private final VehicleRepository vehicleRepository;
	
	public TimeMaintenanceQualifier (VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}
	
	private Duration getDurationFromLastMaintenance (MaintenanceQualifyContext context) {
		return Duration.between (vehicleRepository.findById (context.getVehicleId ())
						.orElseThrow (() -> new VehicleNotFoundException (context.getVehicleId ())).getLastMaintenanceDate (),
				LocalDateTime.now ());
	}
	
	@Override
	public boolean isMaintenanceNeeded (MaintenanceQualifyContext context) {
	
		int days = context.getVehicleTypeEnum ().isDocked () ? 8 * 7 : 3 * 7; // Eight or three weeks
		return getDurationFromLastMaintenance (context).toDays () > days;
	
	}

	@Override
	public String getReason() {
		return "Too long since last maintenance, time to check the vehicle";
	}
}
