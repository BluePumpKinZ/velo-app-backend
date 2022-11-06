package be.kdg.sa.velo.messaging.receivers;

import be.kdg.sa.velo.SimpleBeans;
import be.kdg.sa.velo.VeloApplicationTests;
import be.kdg.sa.velo.dto.vehicles.messages.VehicleLocationPingMessage;
import be.kdg.sa.velo.models.maintenance.MaintenanceFlag;
import be.kdg.sa.velo.repositories.VehicleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


class VehicleLocationReceiverTest extends VeloApplicationTests {

    @Value("${messaging.vehicle-location-queue}")
    private final String vehicleLocationQueueName = "";

    @Autowired
    private RabbitTemplate template;
    @MockBean
    private VehicleRepository vehicleRepository;
    @Autowired
    private ObjectMapper objectMapper;


    @BeforeAll
    static void setUp() {
        var obj1 = new MaintenanceFlag(1,"123","Step","Platte band");
        given(vehi)
        given(vehicleRepository.findById).willReturn(obj1);
    }
    @Test
    void testMessagingReceiver() throws JsonProcessingException {
        VehicleLocationPingMessage message = new VehicleLocationPingMessage(1,2,3);
        this.template.convertAndSend(vehicleLocationQueueName, objectMapper.writeValueAsString(message));

        assertThat(this.template.receiveAndConvert(vehicleLocationQueueName)).isNotNull();
    }
}
