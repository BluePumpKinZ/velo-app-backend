package be.kdg.sa.velo.messaging.senders;

import be.kdg.sa.velo.configuration.MessagingProperties;
import be.kdg.sa.velo.models.Invoice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * Jonas Leijzen
 * 5/10/2022
 */
@Component
public class RideInvoiceSender {
	
	private final RabbitTemplate rabbitTemplate;
	private final MessagingProperties messagingProperties;
	private final ObjectMapper objectMapper;
	
	public RideInvoiceSender (RabbitTemplate rabbitTemplate, MessagingProperties messagingProperties, ObjectMapper objectMapper) {
		this.rabbitTemplate = rabbitTemplate;
		this.messagingProperties = messagingProperties;
		this.objectMapper = objectMapper;
	}
	
	public void send (Invoice invoice) {
		send (objectMapper.convertValue (invoice, String.class));
	}
	
	private void send (String message) {
		rabbitTemplate.convertAndSend (messagingProperties.getInvoiceQueueKey (), message);
	}
}
