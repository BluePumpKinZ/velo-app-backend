package be.kdg.sa.velo.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@ConfigurationProperties (prefix = "openride")
@Getter
@Setter
@NoArgsConstructor
public class OpenRideDetectionProperties {
	@Value ("${openride.max-ride-duration}")
	private Duration maxRideDuration;
	@Value ("${openride.max-not-moving-duration}")
	private Duration maxNotMovingDuration;
	@Value ("${openride.max-not-moving-distance}")
	private double maxNotMovingDistance;
}
