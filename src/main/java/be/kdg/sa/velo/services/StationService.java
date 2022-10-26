package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.stations.Lock;
import be.kdg.sa.velo.domain.stations.Station;
import be.kdg.sa.velo.dto.stations.AddStationDTO;
import be.kdg.sa.velo.exceptions.StationNotFoundException;
import be.kdg.sa.velo.repositories.LockRepository;
import be.kdg.sa.velo.repositories.StationRepository;
import be.kdg.sa.velo.utils.PointUtils;
import org.springframework.stereotype.Service;

import java.util.List;


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
	
	public List<Lock> getFilledLocksForStation (int stationId) {
		return lockRepository.getLocksByStationIdAndVehicleIsNotNull (stationId);
	}
	
	public Station addStation (AddStationDTO stationDTO) {
		var station = new Station (stationDTO.objectId,
				stationDTO.stationNr,
				stationDTO.type,
				stationDTO.street,
				stationDTO.number,
				stationDTO.zipCode,
				stationDTO.district,
				PointUtils.createPoint (stationDTO.latitude, stationDTO.longitude),
				stationDTO.additionalInfo);
		
		return stationRepository.save (station);
	}
	
	public Station getStation (int stationId) {
		return stationRepository.findById (stationId).orElseThrow (() -> new StationNotFoundException (stationId));
	}
	
	public Station updateStation (int stationId, AddStationDTO station) {
		var stationToUpdate = getStation (stationId);
		
		stationToUpdate.setObjectId (station.objectId);
		stationToUpdate.setStationNr (station.stationNr);
		stationToUpdate.setType (station.type);
		stationToUpdate.setStreet (station.street);
		stationToUpdate.setNumber (station.number);
		stationToUpdate.setZipCode (station.zipCode);
		stationToUpdate.setDistrict (station.district);
		stationToUpdate.setGpsCoord (PointUtils.createPoint (station.latitude, station.longitude));
		stationToUpdate.setAdditionalInfo (station.additionalInfo);
		
		return stationRepository.save (stationToUpdate);
	}
	
	public void deleteStation (int stationId) {
		stationRepository.deleteById (stationId);
	}
}
