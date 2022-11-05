package be.kdg.sa.velo.controllers.rest;

import be.kdg.sa.velo.controllers.VeloApplicationMvcTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LockControllerTest extends VeloApplicationMvcTests {
	@Autowired
	private MockMvc mvc;

	private final Random random = new Random();

	@Test
	void testCrud() throws Exception {
//		this.mvc.perform(post("/api/v1/stations/add").contentType("application/json").content("{\n" +
//						"	\"ObjectId\": \"1700\",\n" +
//						"	\"name\": \"Station 1700\",\n" +
//						"    \"stationId\": \"310\",\n" +
//						"    \"type\": \"DUBBELZIJDIG\",\n" +
//						"    \"street\": \"Teststraat\",\n" +
//						"    \"number\": \"69\",\n" +
//						"    \"zipcode\": \"9000\",\n" +
//						"    \"district\": \"Gent\",\n" +
//						"    \"latitude\": 50.0,\n" +
//						"    \"additionalInfo\": \"Nope\",\n" +
//						"    \"longitude\": 50.0\n" +
//						"}"))
//				.andExpect(status().isOk());

		var lock = this.mvc.perform(post("/api/v1/locks/add")
						.contentType("application/json")
						.content("{\n" +
								"  \"stationLockNr\": 123,\n" +
								"  \"stationId\": 295,\n" +
								"  \"vehicleId\": 1\n" +
								"}"))
				.andExpect(status().isOk());

		this.mvc.perform(get("/api/v1/locks/123"))
				.andExpect(status().isOk()).andExpect(
						result -> {
							var content = result.getResponse().getContentAsString();
							assertTrue(content.contains("123"));
							assertTrue(content.contains("295"));
							assertTrue(content.contains("1"));
						}
				);

		this.mvc.perform(post("/api/v1/locks/123/update").content("{\n" +
				"  \"stationLockNr\": 123,\n" +
				"  \"stationId\": 295,\n" +
				"  \"vehicleId\": 2\n" +
				"}"))
				.andExpect(status().isOk());

		this.mvc.perform(delete("/api/v1/locks/123/delete"))
				.andExpect(status().isOk());

	}
}