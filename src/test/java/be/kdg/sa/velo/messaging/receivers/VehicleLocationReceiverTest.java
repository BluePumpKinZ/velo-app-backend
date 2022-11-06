package be.kdg.sa.velo.messaging.receivers;

import be.kdg.sa.velo.SimpleBeans;
import be.kdg.sa.velo.VeloApplicationTests;
import be.kdg.sa.velo.dto.vehicles.messages.VehicleLocationPingMessage;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


class VehicleLocationReceiverTest extends VeloApplicationTests {

    @Autowired
    private SimpleBeans simpleBeans;


    @Test
    void testMessagingReceiver() {
        Queue queue = simpleBeans.vehicleLocationQueue();
        var object = new VehicleLocationPingMessage(1, 1, 1);
        queue.
        assertNotNull(queue);
    }
}
