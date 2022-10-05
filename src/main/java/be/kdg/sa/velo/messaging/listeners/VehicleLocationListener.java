package be.kdg.sa.velo.messaging.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Jonas Leijzen
 * 2/10/2022
 */
@Component
public class VehicleLocationListener {
	@RabbitListener (queues = "${messaging.routingKey}")
	void receive (String message) {
		System.out.println ("Received: " + message);
	}
}
