package be.kdg.sa.velo.controllers;

import be.kdg.sa.velo.dto.stations.AddLockDTO;
import be.kdg.sa.velo.exceptions.DomainObjectNotFoundException;
import be.kdg.sa.velo.models.stations.LockModel;
import be.kdg.sa.velo.services.LockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Jonas Leijzen
 * 23/10/2022
 */
@Validated
@RestController
@RequestMapping(value = "/api/v1/locks")
public class LockController {
	
	private final LockService lockService;
	private final Logger logger = LoggerFactory.getLogger (LockController.class);
	
	public LockController (LockService lockService) {
		this.lockService = lockService;
	}
	
	@PostMapping (path="/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LockModel> addLock (@RequestBody AddLockDTO lock) {
		try {
			return new ResponseEntity<> (LockModel.FromLock (lockService.addLock (lock)), HttpStatus.CREATED);
		} catch (DomainObjectNotFoundException e) {
			logger.warn(e.getMessage (), e);
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error ("Error while adding lock", e);
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping (path="/{lockId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LockModel> getLock (@PathVariable int lockId) {
		try {
			return new ResponseEntity<> (LockModel.FromLock (lockService.getLock (lockId)), HttpStatus.OK);
		} catch (DomainObjectNotFoundException e) {
			logger.warn(e.getMessage (), e);
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error ("Error while getting lock", e);
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping (path="/{lockId}/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LockModel> updateLock (@PathVariable int lockId, @RequestBody AddLockDTO lock) {
		try {
			return new ResponseEntity<> (LockModel.FromLock (lockService.updateLock (lockId, lock)), HttpStatus.OK);
		} catch (DomainObjectNotFoundException e) {
			logger.warn(e.getMessage (), e);
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error ("Error while updating lock", e);
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping (path="/{lockId}/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LockModel> deleteLock (@PathVariable int lockId) {
		try {
			lockService.deleteLock (lockId);
			return new ResponseEntity<> (HttpStatus.OK);
		} catch (DomainObjectNotFoundException e) {
			logger.warn(e.getMessage (), e);
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error ("Error while deleting lock", e);
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
