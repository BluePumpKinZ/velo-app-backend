package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.maintenance.MaintenanceFlagging;
import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.dto.maintenance.MaintenanceActionDTO;
import be.kdg.sa.velo.exceptions.LockNotFoundException;
import be.kdg.sa.velo.exceptions.MaintenanceNotFoundException;
import be.kdg.sa.velo.exceptions.RideNotFoundException;
import be.kdg.sa.velo.exceptions.VehicleNotFoundException;
import be.kdg.sa.velo.maintenance.qualifiers.MaintenanceQualifier;
import be.kdg.sa.velo.maintenance.qualifiers.MaintenanceQualifyContext;
import be.kdg.sa.velo.models.maintenance.MaintenanceFlag;
import be.kdg.sa.velo.repositories.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Component
public class MaintenanceService {

	private final VehicleRepository vehicleRepository;
	private final LockRepository lockRepository;
	private final RideRepository rideRepository;
	private final MaintenanceFlaggingRepository maintenanceFlaggingRepository;
	private final MaintenanceActionRepository maintenanceActionRepository;
	private final Collection<MaintenanceQualifier> maintenanceQualifiers;
	
	public MaintenanceService (VehicleRepository vehicleRepository, LockRepository lockRepository, RideRepository rideRepository, Collection<MaintenanceQualifier> maintenanceQualifiers, MaintenanceFlaggingRepository maintenanceFlaggingRepository, MaintenanceActionRepository maintenanceActionRepository) {
		this.vehicleRepository = vehicleRepository;
		this.lockRepository = lockRepository;
		this.rideRepository = rideRepository;
		this.maintenanceQualifiers = maintenanceQualifiers;
		this.maintenanceFlaggingRepository = maintenanceFlaggingRepository;
		this.maintenanceActionRepository = maintenanceActionRepository;
	}
	
	public boolean addVehicleToMaintenanceIfRequired (MaintenanceQualifyContext context) {
		
		var maintenanceQualifier = maintenanceQualifiers.stream ()
				.filter (q -> q.isMaintenanceNeeded (context))
				.findFirst ();
		if (maintenanceQualifier.isEmpty()) return false;

		
		addVehicleToMaintenance(context.getVehicleId (), maintenanceQualifier.get ().getReason());
		return true;
	}
	
	public void addVehicleToMaintenance(int vehicleId, String reason) {
		var vehicle = vehicleRepository.findById (vehicleId).orElseThrow (() -> new VehicleNotFoundException (vehicleId));
		var lock = lockRepository.getClosestLock (vehicle.getLocation ().getX (), vehicle.getLocation ().getY ()).orElseThrow (() -> new LockNotFoundException (0));
		var ride = new Ride (vehicle, vehicle.getLocation (), lock, null);
		rideRepository.save (ride);
		maintenanceFlaggingRepository.save (new MaintenanceFlagging (vehicle, LocalDateTime.now (), reason));
	}
	
	public void removeVehicleFromMaintenance (MaintenanceActionDTO maintenanceActionDTO) {
		var maintenanceFlag = maintenanceFlaggingRepository.findById (maintenanceActionDTO.getflaggingId()).orElseThrow (() -> new MaintenanceNotFoundException (maintenanceActionDTO.getflaggingId()));
		
		var vehicle = vehicleRepository.findById (maintenanceFlag.getVehicle().getId()).orElseThrow (() -> new VehicleNotFoundException (maintenanceFlag.getVehicle().getId()));
		vehicle.setLastMaintenanceDate (LocalDateTime.now ());
		var ride = rideRepository.getLastRideForVehicle (maintenanceFlag.getVehicle().getId()).orElseThrow (() -> new RideNotFoundException(maintenanceFlag.getVehicle().getId()));
		ride.setEndLock (ride.getStartLock ());
		ride.setEndPoint (ride.getStartPoint ());
		ride.setEndTime (LocalDateTime.now ());
		rideRepository.save (ride);
		
		var maintenanceAction = maintenanceActionDTO.toMaintenanceAction(maintenanceFlag);
		maintenanceActionRepository.save(maintenanceAction);
	}
	
	public List<MaintenanceFlag> getMaintenanceFlaggings () {
		return maintenanceFlaggingRepository.getMaintenanceFlaggings ();
	}
	
}
