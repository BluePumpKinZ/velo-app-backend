package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.exceptions.LockNotFoundException;
import be.kdg.sa.velo.exceptions.RideNotFoundException;
import be.kdg.sa.velo.exceptions.VehicleNotFoundException;
import be.kdg.sa.velo.maintenance.qualifiers.MaintenanceQualifier;
import be.kdg.sa.velo.maintenance.qualifiers.MaintenanceQualifyContext;
import be.kdg.sa.velo.repositories.LockRepository;
import be.kdg.sa.velo.repositories.RideRepository;
import be.kdg.sa.velo.repositories.VehicleRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Component
public class MaintenanceService {

	private final VehicleRepository vehicleRepository;
	private final LockRepository lockRepository;
	private final RideRepository rideRepository;
	private final Collection<MaintenanceQualifier> maintenanceQualifiers;
	
	public MaintenanceService (VehicleRepository vehicleRepository, LockRepository lockRepository, RideRepository rideRepository, Collection<MaintenanceQualifier> maintenanceQualifiers) {
		this.vehicleRepository = vehicleRepository;
		this.lockRepository = lockRepository;
		this.rideRepository = rideRepository;
		this.maintenanceQualifiers = maintenanceQualifiers;
	}
	
	public boolean addVehicleToMaintenanceIfRequired (MaintenanceQualifyContext context) {
		
		if (maintenanceQualifiers.stream ().noneMatch (q -> q.isMaintenanceNeeded (context)))
			return false;
		
		AddVehicleToMaintenance (context.getVehicleId ());
		return true;
	}
	
	public void AddVehicleToMaintenance (int vehicleId) {
		var vehicle = vehicleRepository.findById (vehicleId).orElseThrow (() -> new VehicleNotFoundException (vehicleId));
		var lock = lockRepository.getClosestLock (vehicle.getLocation ().getX (), vehicle.getLocation ().getY ()).orElseThrow (() -> new LockNotFoundException (0));
		var ride = new Ride (vehicle, vehicle.getLocation (), lock, null);
		rideRepository.save (ride);
	}
	
	public void removeVehicleFromMaintenance (int vehicleId) {
		var vehicle = vehicleRepository.findById (vehicleId).orElseThrow (() -> new VehicleNotFoundException (vehicleId));
		vehicle.setLastMaintenanceDate (LocalDateTime.now ());
		var ride = rideRepository.getLastRideForVehicle (vehicleId).orElseThrow (() -> new RideNotFoundException (0));
		ride.setEndLock (ride.getStartLock ());
		ride.setEndPoint (ride.getStartPoint ());
		ride.setEndTime (LocalDateTime.now ());
		rideRepository.save (ride);
	}
	
	public List<Vehicle> getVehiclesInMaintenance () {
		return rideRepository.getVehiclesInMaintenance ();
	}
	
}
