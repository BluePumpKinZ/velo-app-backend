package be.kdg.sa.velo.messaging.senders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


public abstract class JsonSender<BaseType, QueueConfig extends SenderQueueConfig> extends Sender<BaseType, QueueConfig> {
	
	public JsonSender (RabbitTemplate rabbitTemplate, ObjectMapper objectMapper, QueueConfig senderQueueConfig, Logger logger) {
		super (rabbitTemplate, objectMapper, senderQueueConfig, logger);
	}
	
}
