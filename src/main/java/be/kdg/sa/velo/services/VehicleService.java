package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Jonas Leijzen
 * 23/09/2022
 */
@Service
@Transactional
public class VehicleService {
	
	private final VehicleRepository vehicleRepository;
	
	public VehicleService (VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}
	
	public List<? extends Vehicle> getAllVehicles () {
		return vehicleRepository.findAll ();
	}
}
