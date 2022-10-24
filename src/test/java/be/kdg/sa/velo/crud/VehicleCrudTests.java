package be.kdg.sa.velo.crud;

import be.kdg.sa.velo.VeloApplicationTests;
import be.kdg.sa.velo.domain.vehicles.VehicleLot;
import be.kdg.sa.velo.dto.stations.AddVehicleDTO;
import be.kdg.sa.velo.exceptions.VehicleNotFoundException;
import be.kdg.sa.velo.services.VehicleService;
import be.kdg.sa.velo.utils.LocalDateTimeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Jonas Leijzen
 * 2/10/2022
 */
public class VehicleCrudTests extends VeloApplicationTests {
	@Autowired
	private VehicleService vehicleService;
	private final List<VehicleLot> lots = new ArrayList<> ();
	private final Random random = new Random ();
	
	@BeforeEach
	void setUp () {
		lots.addAll (vehicleService.getAllVehicleLots ());
	}
	
	private <T>T getRandomItemFromList (List<T> list) {
		return list.get (random.nextInt (list.size ()));
	}
	
	@Test
	void testCrud () {
		var vehicle = new AddVehicleDTO ();
		vehicle.serialNumber = "123456789";
		vehicle.vehicleLotId = getRandomItemFromList (lots).getId ();
		int vehicleId = vehicleService.addVehicle (vehicle).getId ();
		var addedVehicle = vehicleService.getVehicle (vehicleId);
		assertNotEquals (0, addedVehicle.getId ());
		assertEquals ("123456789", addedVehicle.getSerialNumber ());
		var updateVehicleDTO = new AddVehicleDTO ();
		updateVehicleDTO.serialNumber = "123000789";
		updateVehicleDTO.vehicleLotId = addedVehicle.getLot ().getId ();
		updateVehicleDTO.lastMaintenanceOn = LocalDateTimeUtils.toUTCMillis (addedVehicle.getLastMaintenanceDate ());
		updateVehicleDTO.latitude = addedVehicle.getLocation ().getX ();
		updateVehicleDTO.longitude = addedVehicle.getLocation ().getY ();
		var updatedVehicle = vehicleService.updateVehicle (addedVehicle.getId (), updateVehicleDTO);
		assertEquals ("123000789", updatedVehicle.getSerialNumber ());
		assertDoesNotThrow (() -> vehicleService.deleteVehicle (updatedVehicle.getId ()));
		assertThrows (VehicleNotFoundException.class, () -> vehicleService.getVehicle (updatedVehicle.getId ()));
	}
	
}
