package be.kdg.sa.velo.messaging.senders;

import be.kdg.sa.velo.configuration.MessagingProperties;
import be.kdg.sa.velo.models.Invoice;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Component;

/**
 * Jonas Leijzen
 * 5/10/2022
 */
@Component
public class InvoiceXmlSender extends XmlSender<Invoice, MessagingProperties> {
	
	public InvoiceXmlSender (RabbitTemplate rabbitTemplate, MappingJackson2XmlHttpMessageConverter converter, MessagingProperties senderQueueConfig) {
		super (rabbitTemplate, converter, senderQueueConfig, LoggerFactory.getLogger (InvoiceXmlSender.class));
	}
	
}
