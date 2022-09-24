package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Jonas Leijzen
 * 23/09/2022
 */
@Service
public class VehicleServiceImpl implements VehicleService {
	
	private final VehicleRepository vehicleRepository;
	
	public VehicleServiceImpl (VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}
	
	@Override
	public List<Vehicle> getAllVehicles () {
		return vehicleRepository.readAllVehicles();
	}
}
