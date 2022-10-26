package be.kdg.sa.velo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class RideControllerTests extends VeloApplicationMvcTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void startRide () throws Exception {
		
		String body = """
				{
				  "userId": 1,
				  "stationId": 1,
				}
				""";
		
		mockMvc.perform (
				post ("/api/v1/rides/start/docked")
						.accept (MediaType.APPLICATION_JSON_VALUE)
						.header (HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
						.content(body))
				.andExpect (status ().isOk ());
	}
	
}
