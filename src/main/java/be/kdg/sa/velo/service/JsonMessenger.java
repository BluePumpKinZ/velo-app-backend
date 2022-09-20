package be.kdg.sa.velo.service;

import be.kdg.sa.velo.domain.VehicleLocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("JSON")
public class JsonMessenger<T> implements Messenger<T> {
  private static final Logger logger = LoggerFactory.getLogger(JsonMessenger.class);
  ObjectMapper mapper;

  public JsonMessenger(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public void sendMessage(T location) {
    try {
      logger.warn(mapper.writeValueAsString(location));
    } catch (JsonProcessingException e) {
      logger.error("Error marshalling " + location,e);
    }
  }
}
