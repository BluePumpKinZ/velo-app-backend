package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.subscriptions.vehicles.UndockedVehicle;
import be.kdg.sa.velo.domain.subscriptions.vehicles.Vehicle;
import be.kdg.sa.velo.domain.subscriptions.vehicles.VehicleLocation;
import be.kdg.sa.velo.domain.subscriptions.vehicles.VehicleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class Simulator {
	private static final Logger logger = LoggerFactory.getLogger (Simulator.class);
	
	Generator generator;
	Messenger<VehicleLocation> msg;
	
	public Simulator (Generator generator, @Qualifier ("XML") Messenger<VehicleLocation> msg) {
		this.generator = generator;
		this.msg = msg;
	}
	
	public void sendVehicleMessage (int nr) {
		var vehicle = new UndockedVehicle (0, "GEKKENUMBER", 0, VehicleType.BMX, "", true, LocalDate.now ());
		for (int i = 0; i < nr; i++) {
			msg.sendMessage (generator.getRandomVehicleLocation (vehicle));
		}
	}
}
