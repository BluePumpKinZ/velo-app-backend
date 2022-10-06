package be.kdg.sa.velo.controllers;

import be.kdg.sa.velo.models.stations.AvailableLocks;
import be.kdg.sa.velo.services.StationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Jonas Leijzen
 * 28/09/2022
 */
@RestController
@RequestMapping (path = "/api/v1/stations", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class StationController {

	private final StationService stationService;
	
	public StationController (StationService stationService) {
		this.stationService = stationService;
	}
	
	@GetMapping (path="/{stationId}/locks/available")
	public AvailableLocks getAvailableLocksForStation (@PathVariable int stationId) {
		var locks = stationService.getAvailableLocksForStation (stationId);
		var result = new AvailableLocks ();
		result.locks = locks.stream ().map (lock -> new AvailableLocks.AvailableLock (lock.getId (), lock.getStationLockNr ())).toList ();
		return result;
	}

}
