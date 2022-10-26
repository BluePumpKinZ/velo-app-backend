package be.kdg.sa.velo.configuration;

import be.kdg.sa.velo.messaging.senders.SenderQueueConfig;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties (prefix = "messaging")
@Getter
@Setter
@NoArgsConstructor
public class MessagingProperties implements SenderQueueConfig {
	@Value ("${messaging.invoices-queue}")
	private String invoiceQueueKey;
	
	@Override
	public String getSenderQueueName () {
		return invoiceQueueKey;
	}
}
