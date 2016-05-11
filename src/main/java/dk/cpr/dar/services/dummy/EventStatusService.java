package dk.cpr.dar.services.dummy;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.cpr.dar.domain.dar.EventStatus;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by vagrant on 10-05-16.
 */
@RestController
public class EventStatusService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private ObjectMapper mapper = new ObjectMapper();
  private String jsonTosend = null;

  @Autowired
  private ResourceLoader resourceLoader;

  @Value("${server.port}")
  private String serverPort;

  @RequestMapping(method = RequestMethod.GET, path = "/ADRaAReplikeringEventStatusHent")
  String eventStatusGet() {
    logger.debug("GET - ADRaAReplikeringEventStatusHent called");
    if (null == jsonTosend) {

      try {
        Resource resource = resourceLoader.getResource("classpath:JSON/eventStatus.json");
        jsonTosend = IOUtils.toString(resource.getInputStream(), "UTF8");
      } catch (IOException e) {
        e.printStackTrace();
        logger.error("GET - ADRaAReplikeringEventStatusHent exception", e);
      }

    }
    logger.debug("GET - ADRaAReplikeringEventStatusHent returning:"+jsonTosend);
    return jsonTosend;
  }

  @RequestMapping(method = RequestMethod.POST, path = "/ADRaAReplikeringEventStatusHent")
  ResponseEntity<List<EventStatus>> eventStatusSet(@RequestBody List<EventStatus> eventStatusList) {
    logger.debug("POST - ADRaAReplikeringEventStatusHent called");
    try {
      jsonTosend = mapper.writeValueAsString(eventStatusList);
      logger.debug("POST - ADRaAReplikeringEventStatusHent setting json to:"+jsonTosend);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      logger.error("POST - ADRaAReplikeringEventStatusHent exception", e);
    }

    return new ResponseEntity<List<EventStatus>>(eventStatusList, HttpStatus.OK);
  }

}
