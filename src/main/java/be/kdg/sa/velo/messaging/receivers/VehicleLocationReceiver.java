package be.kdg.sa.velo.messaging.receivers;

import be.kdg.sa.velo.models.vehicles.messages.VehicleLocationPingMessage;
import be.kdg.sa.velo.services.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Jonas Leijzen
 * 5/10/2022
 */
@Component
public class VehicleLocationReceiver extends JsonReceiver<VehicleLocationPingMessage> {
	private final VehicleService vehicleService;
	
	public VehicleLocationReceiver (ObjectMapper objectMapper, VehicleService vehicleService) {
		super (objectMapper, LoggerFactory.getLogger (VehicleLocationReceiver.class));
		this.vehicleService = vehicleService;
	}
	
	@Override
	@RabbitListener (queues = "${messaging.vehicle-location-queue}")
	public void receive (String message) {
		super.receive (message);
	}
	
	@Override
	void process (VehicleLocationPingMessage messageObject) {
		vehicleService.vehicleLocationPing (messageObject);
	}
}
