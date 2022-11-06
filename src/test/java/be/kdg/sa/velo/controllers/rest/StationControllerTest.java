package be.kdg.sa.velo.controllers.rest;

import be.kdg.sa.velo.controllers.VeloApplicationMvcTests;
import be.kdg.sa.velo.domain.vehicles.VehicleLot;
import be.kdg.sa.velo.dto.stations.AddLockDTO;
import be.kdg.sa.velo.dto.stations.AddStationDTO;
import be.kdg.sa.velo.repositories.StationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StationControllerTest extends VeloApplicationMvcTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	private final Random random = new Random();


	@Test
	void testCrud() throws Exception {
		// Create
		var station = new AddStationDTO("18001","1501","DUBBELZIJDIG","teststraat","10","2000","Antwerpen",51.4,4.0);

		ResultActions response = mvc.perform(post("/api/v1/stations/add")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(station)))
				.andExpect(status().isCreated());

		response.andDo(print()).
				andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.street").value("teststraat"));

		// Read
		var id = response.andReturn().getResponse().getContentAsString().split(":")[1].split(",")[0];
		System.out.println(id);

		mvc.perform(get("/api/v1/stations/{stationId}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.street").value("teststraat"));

		// Update
		station.setStreet("teststraat2");
		mvc.perform(put("/api/v1/stations/{stationId}/update", id)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(station)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.street").value("teststraat2"));

		// Delete
		mvc.perform(delete("/api/v1/stations/{stationId}/delete", id))
				.andExpect(status().isOk());

	}
}