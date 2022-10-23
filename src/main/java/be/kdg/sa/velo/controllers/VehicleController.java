package be.kdg.sa.velo.controllers;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.dto.vehicles.ClosestVehicle;
import be.kdg.sa.velo.dto.vehicles.messages.VehicleLocationPingMessage;
import be.kdg.sa.velo.services.VehicleService;
import be.kdg.sa.velo.utils.PointUtils;
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
	public void vehicleLocationPing (@RequestBody VehicleLocationPingMessage vehicleLocationPingEvent) {
		vehicleService.vehicleLocationPing(vehicleLocationPingEvent);
	}
	
	@GetMapping (path="/closest")
	public ClosestVehicle getClosestVehicle (@RequestParam double latitude, @RequestParam double longitude) {
		return vehicleService.getClosestVehicle(PointUtils.createPoint (latitude, longitude));
	}
	
}
