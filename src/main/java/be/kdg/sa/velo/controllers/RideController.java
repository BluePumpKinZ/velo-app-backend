package be.kdg.sa.velo.controllers;

import be.kdg.sa.velo.events.vehicles.locks.UnlockDockedVehicleEvent;
import be.kdg.sa.velo.services.RideService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Jonas Leijzen
 * 2/10/2022
 */
@RestController
@RequestMapping (path = "/api/v1/rides")
public class RideController {
	
	private final RideService rideService;
	
	public RideController (RideService rideService) {
		this.rideService = rideService;
	}
	
	@PostMapping (path = "/start/docked")
	public int startDockedRide (@RequestBody UnlockDockedVehicleEvent event) {
		return rideService.startDockedRide(event);
	}

}
