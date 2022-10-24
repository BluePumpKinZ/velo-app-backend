package be.kdg.sa.velo.messaging.senders;

import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

/**
 * Jonas Leijzen
 * 5/10/2022
 */
public abstract class XmlSender<BaseType, QueueConfig extends SenderQueueConfig> extends Sender<BaseType, QueueConfig> {
	
	public XmlSender (RabbitTemplate rabbitTemplate, MappingJackson2XmlHttpMessageConverter converter, QueueConfig senderQueueConfig, Logger logger) {
		super (rabbitTemplate, converter.getObjectMapper (), senderQueueConfig, logger);
	}
}
