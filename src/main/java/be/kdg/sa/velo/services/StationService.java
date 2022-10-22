package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.stations.Lock;
import be.kdg.sa.velo.repositories.LockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Jonas Leijzen
 * 28/09/2022
 */
@Service
public class StationService {
	
	private final LockRepository lockRepository;
	
	public StationService (LockRepository lockRepository) {
		this.lockRepository = lockRepository;
	}
	
	public List<Lock> getAvailableLocksForStation (int stationId) {
		return lockRepository.getLocksByStationIdAndVehicleIsNull (stationId);
	}
	
	public List<Lock> getFilledLocksForStation (int stationId) {
		return lockRepository.getLocksByStationIdAndVehicleIsNotNull (stationId);
	}
}
