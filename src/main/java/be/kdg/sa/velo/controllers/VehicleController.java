package be.kdg.sa.velo.controllers;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import be.kdg.sa.velo.models.vehicles.ClosestVehicle;
import be.kdg.sa.velo.models.vehicles.messages.VehicleLocationPingMessage;
import be.kdg.sa.velo.services.VehicleService;
import be.kdg.sa.velo.utils.PointFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Jonas Leijzen
 * 23/09/2022
 */
@Validated
@RestController
@RequestMapping (path = "/api/v1/vehicles", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class VehicleController {
	
	private final VehicleService vehicleService;
	
	public VehicleController(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}
	
	@GetMapping (path="/all")
	public List<Vehicle> getAllVehicles () {
		return vehicleService.getAllVehicles();
	}
	
	@PostMapping (path="/location/new")
	public VehicleLocation vehicleLocationPing (@RequestBody VehicleLocationPingMessage vehicleLocationPingEvent) {
		return vehicleService.vehicleLocationPing(vehicleLocationPingEvent);
	}
	
	@GetMapping (path="/closest")
	public ClosestVehicle getClosestVehicle (@RequestParam double latitude, @RequestParam double longitude) {
		return vehicleService.getClosestVehicle(PointFactory.createPoint (latitude, longitude));
	}
	
}
