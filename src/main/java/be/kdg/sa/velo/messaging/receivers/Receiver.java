package be.kdg.sa.velo.messaging.receivers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;


public abstract class Receiver<BaseType> {
	
	private final ObjectMapper objectMapper;
	private final Logger logger;
	protected Receiver (ObjectMapper objectMapper, Logger logger) {
		this.objectMapper = objectMapper;
		this.logger = logger;
	}
	
	public void receive (String message) {
		try {
			BaseType object = deserialize (message);
			process (object);
		} catch (JacksonException e) {
			logger.error ("Could not parse " + message, e);
		}
	}
	
	private BaseType deserialize (String message) throws JacksonException {
		return objectMapper.readValue (message, new TypeReference<> () {});
	}
	
	abstract void process (BaseType messageObject);
}
