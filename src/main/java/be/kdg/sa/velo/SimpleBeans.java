package be.kdg.sa.velo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * Jonas Leijzen
 * 2/10/2022
 */
@Configuration
public class SimpleBeans {
	
	@Value ("${messaging.routingKey}")
	private final String vehicleLocationqQueueName = "";
	
	@Bean
	public Queue vehicleLocationQueue () {
		return new Queue (vehicleLocationqQueueName, false);
	}
	
	@Bean
	public Random random () {
		return new Random ();
	}
	
	@Bean
	public ObjectMapper objectMapper () {
		return new ObjectMapper ();
	}
	
}
