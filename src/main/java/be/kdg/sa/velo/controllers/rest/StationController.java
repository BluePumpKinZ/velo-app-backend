package be.kdg.sa.velo.controllers.rest;

import be.kdg.sa.velo.dto.stations.AddStationDTO;
import be.kdg.sa.velo.exceptions.DomainObjectNotFoundException;
import be.kdg.sa.velo.models.stations.AvailableLocksModel;
import be.kdg.sa.velo.models.stations.StationModel;
import be.kdg.sa.velo.services.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RestController
@RequestMapping (path = "/api/v1/stations")
public class StationController {

	private final StationService stationService;
	private final Logger logger = LoggerFactory.getLogger (StationController.class);
	
	public StationController (StationService stationService) {
		this.stationService = stationService;
	}
	
	@GetMapping (path="/{stationId}/locks/available", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AvailableLocksModel> getAvailableLocksForStation (@PathVariable int stationId) {
		try {
			var locks = stationService.getAvailableLocksForStation (stationId);
			return ResponseEntity.ok (AvailableLocksModel.FromLocks (locks));
		} catch (DomainObjectNotFoundException e) {
			logger.warn (e.getMessage (), e);
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error ("Error while getting available locks for station", e);
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Crud
	@PostMapping (path="/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StationModel> addStation (@RequestBody AddStationDTO station) {
		try {
			return new ResponseEntity<> (StationModel.FromStation (stationService.addStation (station)), HttpStatus.CREATED);
		} catch (DomainObjectNotFoundException e) {
			logger.warn (e.getMessage (), e);
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error ("Error while adding station", e);
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping (path="/{stationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StationModel> getStation (@PathVariable int stationId) {
		try {
			var station = stationService.getStation (stationId);
			return ResponseEntity.ok (StationModel.FromStation (station));
		} catch (DomainObjectNotFoundException e) {
			logger.warn (e.getMessage (), e);
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error ("Error while getting station", e);
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping (path="/{stationId}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StationModel> updateStation (@PathVariable int stationId, @RequestBody AddStationDTO station) {
		try {
			var updatedStation = stationService.updateStation (stationId, station);
			return ResponseEntity.ok (StationModel.FromStation (updatedStation));
		} catch (DomainObjectNotFoundException e) {
			logger.warn (e.getMessage (), e);
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error ("Error while updating station", e);
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping (path="/{stationId}/delete")
	public ResponseEntity<StationModel> deleteStation (@PathVariable int stationId) {
		try {
			stationService.deleteStation (stationId);
			return new ResponseEntity<> (HttpStatus.OK);
		} catch (DomainObjectNotFoundException e) {
			logger.warn (e.getMessage (), e);
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error ("Error while deleting station", e);
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
