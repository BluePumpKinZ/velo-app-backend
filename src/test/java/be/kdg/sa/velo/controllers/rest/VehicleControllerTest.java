package be.kdg.sa.velo.controllers.rest;

import be.kdg.sa.velo.controllers.VeloApplicationMvcTests;
import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.domain.vehicles.VehicleLot;
import be.kdg.sa.velo.dto.stations.AddVehicleDTO;
import be.kdg.sa.velo.services.VehicleService;
import be.kdg.sa.velo.utils.LocalDateTimeUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VehicleControllerTest extends VeloApplicationMvcTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ObjectMapper objectMapper;

    private final List<VehicleLot> lots = new ArrayList<>();

    private final Random random = new Random();

    private <T>T getRandomItemFromList (List<T> list) {
        return list.get (random.nextInt (list.size ()));
    }

    @BeforeEach
    void setUp () {
        lots.addAll (vehicleService.getAllVehicleLots ());
    }

    @Test
    void testCrud() throws Exception {
        //Add
        var vehicle = new AddVehicleDTO();
        vehicle.serialNumber = "123456789";
        vehicle.latitude = 51.4;
        vehicle.longitude = 4.0;
        vehicle.lastMaintenanceOn = LocalDateTimeUtils.toUTCMillis(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        vehicle.vehicleLotId = getRandomItemFromList (lots).getId ();

        ResultActions response = mvc.perform(MockMvcRequestBuilders.post("/api/v1/vehicles/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicle)))
                .andExpect(status().isCreated());

        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.serialNumber").value(vehicle.serialNumber))
                .andExpect(jsonPath("$.vehicleLotId").value(vehicle.vehicleLotId))
                .andExpect(jsonPath("$.lastMaintenanceOn").value(vehicle.lastMaintenanceOn));

        //Get
        int id = Integer.parseInt(response.andReturn().getResponse().getContentAsString().split(":")[1].split(",")[0]);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/vehicles/{vehicleId}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.serialNumber").value(vehicle.serialNumber))
                .andExpect(jsonPath("$.vehicleLotId").value(vehicle.vehicleLotId))
                .andExpect(jsonPath("$.lastMaintenanceOn").value(vehicle.lastMaintenanceOn));

        //get all
//        mvc.perform(MockMvcRequestBuilders.get("/api/v1/vehicles/all")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$", hasSize(1)));


        //Update
        vehicle.serialNumber = "987654321";
        vehicle.latitude = 51.5;
        vehicle.longitude = 4.1;
        mvc.perform(MockMvcRequestBuilders.put("/api/v1/vehicles/{vehicleId}/update", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicle)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.serialNumber").value(vehicle.serialNumber))
                .andExpect(jsonPath("$.vehicleLotId").value(vehicle.vehicleLotId))
                .andExpect(jsonPath("$.lastMaintenanceOn").value(vehicle.lastMaintenanceOn));

        //Delete
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/vehicles/{vehicleId}/delete", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}