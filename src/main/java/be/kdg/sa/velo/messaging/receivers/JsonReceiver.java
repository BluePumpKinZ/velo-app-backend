package be.kdg.sa.velo.messaging.receivers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

/**
 * Jonas Leijzen
 * 2/10/2022
 */

public abstract class JsonReceiver<BaseType> extends Receiver<BaseType> {
	
	protected JsonReceiver (ObjectMapper objectMapper, Logger logger) {
		super (objectMapper, logger);
	}
	
}
