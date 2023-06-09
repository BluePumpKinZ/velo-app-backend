package be.kdg.sa.velo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;


@Configuration
public class SimpleBeans {
	
	@Value ("${messaging.vehicle-location-queue}")
	private final String vehicleLocationQueueName = "";
	
	@Bean
	public Queue vehicleLocationQueue () {
		return new Queue (vehicleLocationQueueName, false);
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
