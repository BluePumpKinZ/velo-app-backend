package be.kdg.sa.velo.crud;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.domain.vehicles.VehicleLot;
import be.kdg.sa.velo.services.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Jonas Leijzen
 * 2/10/2022
 */
@SpringBootTest
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
public class VehicleTests {
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
	void createdelete () {
		var vehicle = new Vehicle ();
		vehicle.setSerialNumber ("123456789");
		vehicle.setLot (getRandomItemFromList (lots));
		var addedVehicle = vehicleService.addVehicle (vehicle);
		assertNotEquals (0, addedVehicle.getId ());
		assertDoesNotThrow (() -> vehicleService.deleteVehicle (addedVehicle.getId ()));
	}
	
	@Test
	void update () {
		var vehicle = new Vehicle ();
		vehicle.setSerialNumber ("123000789");
		vehicle = vehicleService.addVehicle (vehicle);
		var updatedVehicle = vehicleService.updateVehicle (vehicle);
		assertEquals ("987654321", updatedVehicle.getSerialNumber ());
		
	}
	
}
