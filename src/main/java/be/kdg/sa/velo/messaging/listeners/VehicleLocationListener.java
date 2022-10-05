package be.kdg.sa.velo.messaging.listeners;

import be.kdg.sa.velo.models.vehicles.messages.VehicleLocationPingMessage;
import be.kdg.sa.velo.services.VehicleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Jonas Leijzen
 * 2/10/2022
 */
@Component
public class VehicleLocationListener {
	
	private final VehicleService vehicleService;
	private final ObjectMapper objectMapper;
	private final Logger logger = LoggerFactory.getLogger (VehicleLocationListener.class);
	
	public VehicleLocationListener (VehicleService vehicleService, ObjectMapper objectMapper) {
		this.vehicleService = vehicleService;
		this.objectMapper = objectMapper;
	}
	
	private void receiveVehicleLocation (VehicleLocationPingMessage vehicleLocation) {
		vehicleService.vehicleLocationPing (vehicleLocation);
	}
	
	@RabbitListener (queues = "${messaging.routingKey}")
	void receive (String message) {
		try {
			var vehicleLocation = deserializeVehicleLocation (message);
			receiveVehicleLocation (vehicleLocation);
		} catch (JsonProcessingException e) {
			logger.error ("Could not parse " + message, e);
		}
	}
	
	private VehicleLocationPingMessage deserializeVehicleLocation (String message) throws JsonProcessingException {
		return objectMapper.readValue (message, VehicleLocationPingMessage.class);
	}
}
