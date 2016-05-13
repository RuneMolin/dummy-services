package dk.cpr.dar.services.dummy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.List;

/**
 * Created by vagrant on 13-05-16.
 */
public abstract class DummyService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private ResourceLoader resourceLoader;

  protected String jsonTosend = null;

  protected ObjectMapper mapper = new ObjectMapper();

  protected String doGet(String serviceMethod, String jsonFile) {

    logger.debug(String.format("GET - %s called ", serviceMethod));
    if (null == jsonTosend) {
      jsonTosend = getJsonFromResource(jsonFile);
    }
    logger.debug(String.format("GET - %s returning: %s", serviceMethod, jsonTosend));
    return jsonTosend;
  }

  protected String doSet(String serviceMethod, List<?> dataList) {
    logger.debug(String.format("POST - %s called", serviceMethod));

    return getJsonSet(serviceMethod, dataList);
  }

  protected String doSet(String serviceMethod, Object data) {
    logger.debug(String.format("POST - %s called", serviceMethod));

    return getJsonSet(serviceMethod, data);
  }

  private String getJsonSet(String serviceMethod, Object data) {
    try {
      jsonTosend = mapper.writeValueAsString(data);
      logger.debug(String.format("POST - %s setting json to: %s", serviceMethod, jsonTosend));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      logger.error(String.format("POST - %s exception", e));
    }

    return jsonTosend;
  }


  protected String getJsonFromResource(String jsonFile) {
    String json = null;
    try {
      Resource resource = resourceLoader.getResource(String.format("classpath:JSON/%s", jsonFile));
      json = IOUtils.toString(resource.getInputStream(), "UTF8");
    } catch (IOException e) {
      e.printStackTrace();
      logger.error(jsonFile + " - exception", e);
    }
    return json;
  }
}
