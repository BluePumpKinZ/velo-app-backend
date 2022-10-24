package be.kdg.sa.velo.messaging.senders;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Jonas Leijzen
 * 5/10/2022
 */
public abstract class Sender<BaseType, QueueConfig extends SenderQueueConfig> {
	
	private final RabbitTemplate rabbitTemplate;
	private final ObjectMapper objectMapper;
	private final QueueConfig senderQueueConfig;
	private final Logger logger;
	
	public Sender (RabbitTemplate rabbitTemplate, ObjectMapper objectMapper, QueueConfig senderQueueConfig, Logger logger) {
		this.rabbitTemplate = rabbitTemplate;
		this.objectMapper = objectMapper;
		this.senderQueueConfig = senderQueueConfig;
		this.logger = logger;
	}
	
	public void send (BaseType messageObject) {
		try {
			var messageStr = serialize (messageObject);
			send(messageStr);
		} catch (JacksonException e) {
			logger.error ("Error while serializing", e);
		}
	}
	
	private void send (String message) {
		rabbitTemplate.convertAndSend (senderQueueConfig.getSenderQueueName (), message);
	}
	
	private String serialize (BaseType messageObject) throws JacksonException {
		return objectMapper.writeValueAsString (messageObject);
	}
	
}
