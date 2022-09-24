package be.kdg.sa.velo.controllers;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.services.VehicleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
	@RequestMapping(path="all")
	public List<Vehicle> getAllVehicles () {
		return vehicleService.getAllVehicles();
	}
	
}
