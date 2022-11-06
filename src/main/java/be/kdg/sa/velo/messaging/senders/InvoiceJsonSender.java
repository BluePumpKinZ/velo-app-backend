package be.kdg.sa.velo.messaging.senders;

import be.kdg.sa.velo.configuration.MessagingProperties;
import be.kdg.sa.velo.models.invoices.Invoice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class InvoiceJsonSender extends JsonSender<Invoice, MessagingProperties> {

    public InvoiceJsonSender(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper, MessagingProperties senderQueueConfig) {
        super(rabbitTemplate, objectMapper, senderQueueConfig, LoggerFactory.getLogger(InvoiceJsonSender.class));
    }
}
