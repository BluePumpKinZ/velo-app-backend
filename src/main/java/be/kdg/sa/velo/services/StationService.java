package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.stations.Lock;
import be.kdg.sa.velo.repositories.LockRepository;
import be.kdg.sa.velo.repositories.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Jonas Leijzen
 * 28/09/2022
 */
@Service
public class StationService {
	
	private final LockRepository lockRepository;
	private final StationRepository stationRepository;
	
	public StationService (LockRepository lockRepository, StationRepository stationRepository) {
		this.lockRepository = lockRepository;
		this.stationRepository = stationRepository;
	}
	
	public List<Lock> getAvailableLocksForStation (int stationId) {
		return lockRepository.getLocksByStationIdAndVehicleIsNull (stationId);
	}
}
