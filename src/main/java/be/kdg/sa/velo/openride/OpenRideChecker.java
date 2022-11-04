package be.kdg.sa.velo.openride;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OpenRideChecker {
	
	@Scheduled (cron = "0 */2 * * * *") // every 2 minutes
	public void checkOpenRides () {
		System.out.println ("Checking open rides...");
	}
	
}
