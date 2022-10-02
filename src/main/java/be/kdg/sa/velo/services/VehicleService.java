package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import be.kdg.sa.velo.domain.vehicles.VehicleLot;
import be.kdg.sa.velo.events.vehicles.messages.VehicleLocationPingEvent;
import be.kdg.sa.velo.repositories.VehicleLocationRepository;
import be.kdg.sa.velo.repositories.VehicleLotRepository;
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
	private final VehicleLotRepository vehicleLotRepository;
	
	public VehicleService (VehicleRepository vehicleRepository, VehicleLocationRepository vehicleLocationRepository, VehicleLotRepository vehicleLotRepository) {
		this.vehicleRepository = vehicleRepository;
		this.vehicleLocationRepository = vehicleLocationRepository;
		this.vehicleLotRepository = vehicleLotRepository;
	}
	
	public List<Vehicle> getAllVehicles () {
		return vehicleRepository.findAll ();
	}
	
	public VehicleLocation vehicleLocationPing (VehicleLocationPingEvent vehicleLocationPingEvent) {
		var vehicle = vehicleRepository.findById (vehicleLocationPingEvent.getVehicleId ()).orElseThrow ();
		var locationPing = new VehicleLocation (vehicle, vehicleLocationPingEvent.getLatitude (), vehicleLocationPingEvent.getLongitude ());
		return vehicleLocationRepository.save (locationPing);
	}
	
	public Vehicle addVehicle (Vehicle vehicle) {
		return vehicleRepository.save (vehicle);
	}
	
	public Vehicle getVehicleById (int vehicleId) {
		return vehicleRepository.findById (vehicleId).orElseThrow ();
	}
	
	public Vehicle updateVehicle (Vehicle vehicle) {
		return vehicleRepository.save (vehicle);
	}
	
	public void deleteVehicle (int vehicleId) {
		vehicleRepository.deleteById (vehicleId);
	}
	
	public List<VehicleLot> getAllVehicleLots () {
		return vehicleLotRepository.findAll ();
	}
	
	public VehicleLot addVehicleLot (VehicleLot vehicleLot) {
		return vehicleLotRepository.save (vehicleLot);
	}
	
	public VehicleLot getVehicleLotById (int vehicleLotId) {
		return vehicleLotRepository.findById (vehicleLotId).orElseThrow ();
	}
	
	public VehicleLot updateVehicleLot (VehicleLot vehicleLot) {
		return vehicleLotRepository.save (vehicleLot);
	}
	
	public void deleteVehicleLot (int vehicleLotId) {
		vehicleLotRepository.deleteById (vehicleLotId);
	}
}
