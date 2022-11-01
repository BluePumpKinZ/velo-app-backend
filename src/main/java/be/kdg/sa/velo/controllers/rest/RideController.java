package be.kdg.sa.velo.controllers.rest;

import be.kdg.sa.velo.exceptions.DomainObjectNotFoundException;
import be.kdg.sa.velo.dto.vehicles.calls.LockDockedVehicleCall;
import be.kdg.sa.velo.dto.vehicles.calls.LockUndockedVehicleCall;
import be.kdg.sa.velo.dto.vehicles.calls.UnlockDockedVehicleCall;
import be.kdg.sa.velo.dto.vehicles.calls.UnlockUndockedVehicleCall;
import be.kdg.sa.velo.services.RideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Validated
@RestController
@RequestMapping (path = "/api/v1/rides", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class RideController {
	
	private final RideService rideService;
	private final Logger logger = LoggerFactory.getLogger (RideController.class);
	
	public RideController (RideService rideService) {
		this.rideService = rideService;
	}
	
	@PostMapping (path = "start/docked")
	public ResponseEntity<?> startDockedRide (@RequestBody UnlockDockedVehicleCall event) {
		try {
			return ResponseEntity.ok (rideService.startDockedRide (event));
		} catch (DomainObjectNotFoundException e) {
			logger.error (e.getMessage ());
			return new ResponseEntity<> (e.getMessage (), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping (path = "start/undocked")
	public ResponseEntity<?> startUndockedRide (@RequestBody UnlockUndockedVehicleCall event) {
		try {
			rideService.startUndockedRide (event);
			return new ResponseEntity<> (HttpStatus.OK);
		} catch (DomainObjectNotFoundException e) {
			logger.error (e.getMessage ());
			return new ResponseEntity<> (e.getMessage (), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping (path = "end/docked")
	public ResponseEntity<?> endDockedRide (@RequestBody LockDockedVehicleCall event) {
		try {
			rideService.endDockedRide (event);
			return new ResponseEntity<> (HttpStatus.OK);
		} catch (DomainObjectNotFoundException e) {
			logger.error (e.getMessage ());
			return new ResponseEntity<> (e.getMessage (), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping (path = "end/undocked")
	public ResponseEntity<?> endUndockedRide (@RequestBody LockUndockedVehicleCall event) {
		try {
			rideService.endUndockedRide (event);
			return new ResponseEntity<> (HttpStatus.OK);
		} catch (DomainObjectNotFoundException e) {
			logger.error (e.getMessage ());
			return new ResponseEntity<> (e.getMessage (), HttpStatus.BAD_REQUEST);
		}
	}
	
}
