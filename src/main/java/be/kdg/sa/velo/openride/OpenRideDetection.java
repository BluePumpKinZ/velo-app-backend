package be.kdg.sa.velo.openride;

import be.kdg.sa.velo.openride.detectors.OpenRideDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class OpenRideDetection {
	
	private final List<OpenRideDetector> openRideDetectors;
	private final Logger logger = LoggerFactory.getLogger (OpenRideDetection.class);
	
	public OpenRideDetection (List<OpenRideDetector> openRideDetectors) {
		this.openRideDetectors = openRideDetectors;
	}
	
	@Scheduled (cron = "0 */2 * * * *") // every 2 minutes
	public void checkOpenRides () {
		AtomicInteger totalClosedRides = new AtomicInteger ();
		openRideDetectors.forEach (openRideDetector -> {
			totalClosedRides.set (totalClosedRides.get () + openRideDetector.checkOpenRides ());
		});
		if (totalClosedRides.get () != 0) {
			logger.info ("Closed " + totalClosedRides.get () + " open rides.");
		} else {
			logger.info ("Ran open ride detection. No open rides to close.");
		}
	}
	
}
