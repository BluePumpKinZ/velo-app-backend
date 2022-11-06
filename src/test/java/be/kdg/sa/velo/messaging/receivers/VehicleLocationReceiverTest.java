package be.kdg.sa.velo.messaging.receivers;

import be.kdg.sa.velo.VeloApplicationTests;
import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import be.kdg.sa.velo.dto.vehicles.messages.VehicleLocationPingMessage;
import be.kdg.sa.velo.repositories.VehicleLocationRepository;
import be.kdg.sa.velo.repositories.VehicleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class VehicleLocationReceiverTest extends VeloApplicationTests {

    @Autowired
    private Receiver<VehicleLocationPingMessage> receiver;
    @MockBean
    private VehicleRepository vehicleRepository;
    @MockBean
    private VehicleLocationRepository vehicleLocationRepository;
    @Autowired
    private ObjectMapper objectMapper;


    @BeforeAll
    void setUp() {
        var vehicle = new Vehicle ();
        vehicle.setId(1);
        given(vehicleRepository.findById(1)).willReturn(Optional.of (vehicle));
    }
    
    @Test
    void testMessagingReceiver() throws JsonProcessingException {
        VehicleLocationPingMessage message = new VehicleLocationPingMessage(1,2,3);
        receiver.receive (objectMapper.writeValueAsString(message));
        
        ArgumentCaptor<Vehicle> vehicleArgumentCaptor = ArgumentCaptor.forClass(Vehicle.class);
        verify(vehicleRepository, times(1)).save(vehicleArgumentCaptor.capture());
    
        ArgumentCaptor<VehicleLocation> vehicleLocationArgumentCaptor = ArgumentCaptor.forClass(VehicleLocation.class);
        verify (vehicleLocationRepository, times(1)).save(vehicleLocationArgumentCaptor.capture());
        
        VehicleLocation vehicleLocation = vehicleLocationArgumentCaptor.getValue();
        assertThat(vehicleLocation.getVehicle ().getId ()).isEqualTo(1);
        assertThat(vehicleLocation.getLocation ().getX ()).isEqualTo(2);
        assertThat(vehicleLocation.getLocation ().getY ()).isEqualTo(3);
    }
}
