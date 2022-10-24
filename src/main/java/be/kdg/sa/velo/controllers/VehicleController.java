package be.kdg.sa.velo.controllers;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.dto.stations.AddVehicleDTO;
import be.kdg.sa.velo.dto.vehicles.ClosestVehicle;
import be.kdg.sa.velo.dto.vehicles.messages.VehicleLocationPingMessage;
import be.kdg.sa.velo.exceptions.DomainObjectNotFoundException;
import be.kdg.sa.velo.models.stations.VehicleModel;
import be.kdg.sa.velo.services.VehicleService;
import be.kdg.sa.velo.utils.PointUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Jonas Leijzen
 * 23/09/2022
 */
@Validated
@RestController
@RequestMapping (path = "/api/v1/vehicles")
public class VehicleController {
	
	private final VehicleService vehicleService;
	private final Logger logger = LoggerFactory.getLogger (VehicleController.class);
	
	public VehicleController(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}
	
	@GetMapping (path="/all", produces = "application/json")
	public List<Vehicle> getAllVehicles () {
		return vehicleService.getAllVehicles();
	}
	
	@PostMapping (path="/location/new", consumes = "application/json", produces = "application/json")
	public void vehicleLocationPing (@RequestBody VehicleLocationPingMessage vehicleLocationPingEvent) {
		vehicleService.vehicleLocationPing(vehicleLocationPingEvent);
	}
	
	@GetMapping (path="/closest", produces = "application/json")
	public ClosestVehicle getClosestVehicle (@RequestParam double latitude, @RequestParam double longitude) {
		return vehicleService.getClosestVehicle(PointUtils.createPoint (latitude, longitude));
	}
	
	@GetMapping (path="/add", produces = "application/json")
	public ResponseEntity<VehicleModel> addVehicle (@RequestBody AddVehicleDTO vehicle) {
		try {
			return new ResponseEntity<> (VehicleModel.FromVehicle (vehicleService.addVehicle (vehicle)), HttpStatus.CREATED);
		} catch (DomainObjectNotFoundException e) {
			logger.warn (e.getMessage (), e);
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error ("Error while adding vehicle", e);
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping (path = "/{vehicleId}", produces = "application/json")
	public ResponseEntity<VehicleModel> getVehicle (@PathVariable int vehicleId) {
		try {
			return new ResponseEntity<> (VehicleModel.FromVehicle (vehicleService.getVehicle (vehicleId)), HttpStatus.OK);
		} catch (DomainObjectNotFoundException e) {
			logger.warn (e.getMessage (), e);
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error ("Error while getting vehicle", e);
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping (path = "/{vehicleId}/update", produces = "application/json")
	public ResponseEntity<VehicleModel> updateVehicle (@PathVariable int vehicleId, @RequestBody AddVehicleDTO vehicle) {
		try {
			return new ResponseEntity<> (VehicleModel.FromVehicle (vehicleService.updateVehicle (vehicleId, vehicle)), HttpStatus.OK);
		} catch (DomainObjectNotFoundException e) {
			logger.warn (e.getMessage (), e);
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error ("Error while updating vehicle", e);
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping (path = "/{vehicleId}/delete", produces = "application/json")
	public ResponseEntity<VehicleModel> deleteStation (@PathVariable int vehicleId) {
		try {
			vehicleService.deleteVehicle (vehicleId);
			return new ResponseEntity<> (HttpStatus.OK);
		} catch (DomainObjectNotFoundException e) {
			logger.warn (e.getMessage (), e);
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error ("Error while deleting vehicle", e);
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
