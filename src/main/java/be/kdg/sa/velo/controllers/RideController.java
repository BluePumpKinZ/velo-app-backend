package be.kdg.sa.velo.controllers;

import be.kdg.sa.velo.models.vehicles.calls.LockDockedVehicleCall;
import be.kdg.sa.velo.models.vehicles.calls.LockUndockedVehicleCall;
import be.kdg.sa.velo.models.vehicles.calls.UnlockDockedVehicleCall;
import be.kdg.sa.velo.models.vehicles.calls.UnlockUndockedVehicleCall;
import be.kdg.sa.velo.services.RideService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Jonas Leijzen
 * 2/10/2022
 */
@RestController
@RequestMapping (path = "/api/v1/rides", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class RideController {
	
	private final RideService rideService;
	
	public RideController (RideService rideService) {
		this.rideService = rideService;
	}
	
	@PostMapping (path = "/start/docked")
	public int startDockedRide (@RequestBody UnlockDockedVehicleCall event) {
		return rideService.startDockedRide(event);
	}
	
	@PostMapping (path = "/start/undocked")
	public void startUndockedRide (@RequestBody UnlockUndockedVehicleCall event) {
		rideService.startUndockedRide(event);
	}
	
	@PostMapping (path = "end/docked")
	public void endDockedRide (@RequestBody LockDockedVehicleCall event) {
		rideService.endDockedRide(event);
	}
	
	@PostMapping (path = "end/undocked")
	public void endUndockedRide (@RequestBody LockUndockedVehicleCall event) {
		rideService.endUndockedRide(event);
	}
	
}
