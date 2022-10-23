package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import be.kdg.sa.velo.domain.vehicles.VehicleLot;
import be.kdg.sa.velo.dto.vehicles.ClosestVehicle;
import be.kdg.sa.velo.dto.vehicles.messages.VehicleLocationPingMessage;
import be.kdg.sa.velo.repositories.VehicleLocationRepository;
import be.kdg.sa.velo.repositories.VehicleLotRepository;
import be.kdg.sa.velo.repositories.VehicleRepository;
import be.kdg.sa.velo.utils.PointUtils;
import be.kdg.sa.velo.utils.VehicleTypeUtils;
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
	private final VehicleLocationRepository vehicleLocationRepository;
	
	public VehicleService (VehicleRepository vehicleRepository, VehicleLotRepository vehicleLotRepository, VehicleLocationRepository vehicleLocationRepository) {
		this.vehicleRepository = vehicleRepository;
		this.vehicleLotRepository = vehicleLotRepository;
		this.vehicleLocationRepository = vehicleLocationRepository;
	}
	
	public List<Vehicle> getAllVehicles () {
		return vehicleRepository.findAll ();
	}
	
	public void vehicleLocationPing (VehicleLocationPingMessage vehicleLocationPingEvent) {
		var vehicle = vehicleRepository.findById (vehicleLocationPingEvent.getVehicleId ()).orElseThrow ();
		var location = PointUtils.createPoint (vehicleLocationPingEvent.getLatitude (), vehicleLocationPingEvent.getLongitude ());
		vehicle.setLocation (location);
		vehicleRepository.save (vehicle);
		var vehicleLocation = new VehicleLocation (vehicle, location);
		vehicleLocationRepository.save (vehicleLocation);
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
	
	public boolean isVehicleDocked (int vehicleId) {
		var vehicleType = vehicleRepository.getVehicleType (vehicleId);
		return VehicleTypeUtils.getVehicleTypeEnum (vehicleType).isDocked ();
	}
}
