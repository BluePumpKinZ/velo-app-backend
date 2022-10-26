package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import be.kdg.sa.velo.domain.vehicles.VehicleLot;
import be.kdg.sa.velo.dto.stations.AddVehicleDTO;
import be.kdg.sa.velo.dto.vehicles.ClosestVehicle;
import be.kdg.sa.velo.dto.vehicles.messages.VehicleLocationPingMessage;
import be.kdg.sa.velo.exceptions.VehicleLotNotFoundException;
import be.kdg.sa.velo.exceptions.VehicleNotFoundException;
import be.kdg.sa.velo.repositories.VehicleLocationRepository;
import be.kdg.sa.velo.repositories.VehicleLotRepository;
import be.kdg.sa.velo.repositories.VehicleRepository;
import be.kdg.sa.velo.utils.LocalDateTimeUtils;
import be.kdg.sa.velo.utils.PointUtils;
import be.kdg.sa.velo.utils.VehicleTypeUtils;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


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
	
	public Vehicle addVehicle (AddVehicleDTO vehicleDTO) {
		var vehicle = vehicleDTO.toVehicle ();
		vehicle.setLot (vehicleLotRepository.findById (vehicleDTO.vehicleLotId).orElseThrow (() -> new VehicleLotNotFoundException (vehicleDTO.vehicleLotId)));
		return vehicleRepository.save (vehicle);
	}
	
	public Vehicle getVehicle (int vehicleId) {
		return vehicleRepository.findById (vehicleId).orElseThrow (() -> new VehicleNotFoundException (vehicleId));
	}
	
	public Vehicle updateVehicle (int vehicleId, AddVehicleDTO vehicleDTO) {
		var vehicle = vehicleRepository.findById (vehicleId).orElseThrow (() -> new VehicleLotNotFoundException (vehicleId));
		vehicle.setSerialNumber (vehicleDTO.serialNumber);
		vehicle.setLastMaintenanceDate (LocalDateTimeUtils.fromUTCMillis (vehicleDTO.lastMaintenanceOn));
		vehicle.setLocation (PointUtils.createPoint (vehicleDTO.latitude, vehicleDTO.longitude));
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
