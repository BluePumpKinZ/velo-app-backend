package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.domain.vehicles.VehicleLot;
import be.kdg.sa.velo.models.vehicles.ClosestVehicle;
import be.kdg.sa.velo.models.vehicles.messages.VehicleLocationPingMessage;
import be.kdg.sa.velo.repositories.VehicleLotRepository;
import be.kdg.sa.velo.repositories.VehicleRepository;
import be.kdg.sa.velo.utils.PointUtils;
import org.locationtech.jts.geom.Point;
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
	private final VehicleLotRepository vehicleLotRepository;
	
	public VehicleService (VehicleRepository vehicleRepository, VehicleLotRepository vehicleLotRepository) {
		this.vehicleRepository = vehicleRepository;
		this.vehicleLotRepository = vehicleLotRepository;
	}
	
	public List<Vehicle> getAllVehicles () {
		return vehicleRepository.findAll ();
	}
	
	public void vehicleLocationPing (VehicleLocationPingMessage vehicleLocationPingEvent) {
		var vehicle = vehicleRepository.findById (vehicleLocationPingEvent.getVehicleId ()).orElseThrow ();
		vehicle.setLocation (PointUtils.createPoint (vehicleLocationPingEvent.getLatitude (), vehicleLocationPingEvent.getLongitude ()));
		vehicleRepository.save (vehicle);
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
	
	public ClosestVehicle getClosestVehicle (Point point) {
		var closestVehicle = vehicleRepository.findClosestVehicle (point.getX (), point.getY ());
		return new ClosestVehicle (closestVehicle.getId (),
				closestVehicle.getSerialNumber (),
				closestVehicle.getLocation().getX (),
				closestVehicle.getLocation ().getY ());
	}
}
