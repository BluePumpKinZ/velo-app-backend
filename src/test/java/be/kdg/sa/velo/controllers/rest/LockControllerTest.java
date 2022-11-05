package be.kdg.sa.velo.controllers.rest;

import be.kdg.sa.velo.controllers.VeloApplicationMvcTests;
import be.kdg.sa.velo.domain.stations.Lock;
import be.kdg.sa.velo.dto.stations.AddLockDTO;
import be.kdg.sa.velo.repositories.StationRepository;
import be.kdg.sa.velo.repositories.VehicleRepository;
import be.kdg.sa.velo.services.LockService;
import be.kdg.sa.velo.services.StationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LockControllerTest extends VeloApplicationMvcTests {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private StationRepository stationRepository;

	private final Random random = new Random();

	@Test
	void testCrud() throws Exception {
		// Create
		var station = stationRepository.findAll().get(0);
		var lock = new AddLockDTO(random.nextInt(100-1) + 1,random.nextInt(100-1) + 1, station.getId(),1);

		ResultActions response = mvc.perform(post("/api/v1/locks/add")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(lock)))
				.andExpect(status().isCreated());

		response.andDo(print()).
				andExpect(status().isCreated())
				.andExpect(jsonPath("$.stationLockNr").value(lock.getStationLockNr()))
				.andExpect(jsonPath("$.stationId").value(station.getId()));

		// Read
		response = mvc.perform(get("/api/v1/locks/{lockId}", lock.lockId));
		response.andExpect(jsonPath("$.stationLockNr").value(lock.getStationLockNr()))
				.andExpect(jsonPath("$.stationId").value(station.getId()));

		// Update
		lock.setStationLockNr(2);
		response = mvc.perform(put("/api/v1/locks/{lockId}/update", lock.lockId)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(lock)));
		response.andExpect(jsonPath("$.stationLockNr").value(lock.getStationLockNr()));

		// Delete
		response = mvc.perform(delete("/api/v1/locks/{lockId}/delete", lock.lockId));
		response.andExpect(status().isOk());
	}
}