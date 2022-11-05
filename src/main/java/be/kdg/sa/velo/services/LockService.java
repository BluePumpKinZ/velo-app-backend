package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.stations.Lock;
import be.kdg.sa.velo.dto.stations.AddLockDTO;
import be.kdg.sa.velo.exceptions.LockNotFoundException;
import be.kdg.sa.velo.exceptions.StationNotFoundException;
import be.kdg.sa.velo.repositories.LockRepository;
import be.kdg.sa.velo.repositories.StationRepository;
import be.kdg.sa.velo.repositories.VehicleRepository;
import org.springframework.stereotype.Service;


@Service
public class LockService {
	
	private final LockRepository lockRepository;
	private final StationRepository stationRepository;
	private final VehicleRepository vehicleRepository;
	
	public LockService (LockRepository lockRepository, StationRepository stationRepository, VehicleRepository vehicleRepository) {
		this.lockRepository = lockRepository;
		this.stationRepository = stationRepository;
		this.vehicleRepository = vehicleRepository;
	}
	
	
	public Lock addLock (AddLockDTO lockDTO) {
		var lock = new Lock (lockDTO.lockId,lockDTO.stationLockNr);
		lock.setStation (stationRepository.findById (lockDTO.stationId).orElseThrow (() -> new StationNotFoundException (lockDTO.stationId)));
		lock.setVehicle (vehicleRepository.findById (lockDTO.vehicleId).orElse (null));
		return lockRepository.save (lock);
	}
	
	public Lock getLock (int lockId) {
		return lockRepository.findById (lockId).orElseThrow (() -> new StationNotFoundException (lockId));
	}
	
	public Lock updateLock (int lockId, AddLockDTO lockDTO) {
		var lock = lockRepository.findById (lockId).orElseThrow (() -> new LockNotFoundException (lockId));
		lock.setStationLockNr (lockDTO.stationLockNr);
		lock.setStation (stationRepository.findById (lockDTO.stationId).orElseThrow (() -> new StationNotFoundException (lockDTO.stationId)));
		lock.setVehicle (vehicleRepository.findById (lockDTO.vehicleId).orElse (null));
		return lockRepository.save (lock);
	}
	
	public void deleteLock (int lockId) {
		lockRepository.deleteById (lockId);
	}
	
}
