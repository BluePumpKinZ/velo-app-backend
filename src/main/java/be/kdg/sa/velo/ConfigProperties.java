package be.kdg.sa.velo;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jonas Leijzen
 * 2/10/2022
 */
@Configuration
public class ConfigProperties {
	
	@Value ("${messaging.routingKey}")
	private final String queueName = "";
	
	@Bean
	public Queue vehicleLocationQueue () {
		return new Queue (queueName, false);
	}
	
}
