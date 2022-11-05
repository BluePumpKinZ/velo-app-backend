package be.kdg.sa.velo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootApplication
@EnableScheduling
@EntityScan
@DirtiesContext (classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class VeloApplication {
	
	public static void main (String[] args) {
		SpringApplication.run (VeloApplication.class, args);
	}
	
}
