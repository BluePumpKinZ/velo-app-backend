package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import be.kdg.sa.velo.events.vehicles.messages.VehicleLocationPingEvent;
import be.kdg.sa.velo.repositories.VehicleLocationRepository;
import be.kdg.sa.velo.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Jonas Leijzen
 * 23/09/2022
 */
@Service
@Transactional
public class VehicleService {
	
	private final VehicleRepository vehicleRepository;
	private final VehicleLocationRepository vehicleLocationRepository;
	
	public VehicleService (VehicleRepository vehicleRepository, VehicleLocationRepository vehicleLocationRepository) {
		this.vehicleRepository = vehicleRepository;
		this.vehicleLocationRepository = vehicleLocationRepository;
	}
	
	public List<Vehicle> getAllVehicles () {
		return vehicleRepository.findAll ();
	}
	
	public VehicleLocation vehicleLocationPing (VehicleLocationPingEvent vehicleLocationPingEvent) {
		var vehicle = vehicleRepository.findById (vehicleLocationPingEvent.getVehicleId ()).orElseThrow ();
		var locationPing = new VehicleLocation (vehicle, vehicleLocationPingEvent.getLatitude (), vehicleLocationPingEvent.getLongitude ());
		return vehicleLocationRepository.save (locationPing);
	}
}
