package be.kdg.sa.velo.controllers;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import be.kdg.sa.velo.events.vehicles.messages.VehicleLocationPingEvent;
import be.kdg.sa.velo.services.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Jonas Leijzen
 * 23/09/2022
 */

@RestController
@RequestMapping (path = "/api/v1/vehicles", produces = APPLICATION_JSON_VALUE)
public class VehicleController {
	
	private final VehicleService vehicleService;
	
	public VehicleController(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}
	
	@GetMapping (path="/all")
	public List<Vehicle> getAllVehicles () {
		return vehicleService.getAllVehicles();
	}
	
	@PostMapping (path="/location/new", produces = APPLICATION_JSON_VALUE)
	public VehicleLocation vehicleLocationPing (@RequestBody VehicleLocationPingEvent vehicleLocationPingEvent) {
		return vehicleService.vehicleLocationPing(vehicleLocationPingEvent);
	}
	
}
