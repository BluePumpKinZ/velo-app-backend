package be.kdg.sa.velo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Service;

@Service
@Qualifier("XML")
public class XmlMessenger<T> implements Messenger<T> {

  private static final Logger logger = LoggerFactory.getLogger(XmlMessenger.class);
  ObjectMapper mapper;

  public XmlMessenger(MappingJackson2XmlHttpMessageConverter converter) {
    mapper=converter.getObjectMapper();
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
