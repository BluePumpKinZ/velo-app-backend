package be.kdg.sa.velo.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Jonas Leijzen
 * 5/10/2022
 */
@Configuration
@ConfigurationProperties (prefix = "messaging")
@Getter
@Setter
@NoArgsConstructor
public class MessagingProperties {
	private String invoiceQueueKey;
}
