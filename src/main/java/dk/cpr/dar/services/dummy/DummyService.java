package dk.cpr.dar.services.dummy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by vagrant on 13-05-16.
 */
public abstract class DummyService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private ResourceLoader resourceLoader;

  protected String jsonTosend = null;

  protected ObjectMapper mapper = new ObjectMapper();

  protected ResponseEntity doGet(String serviceMethod, String jsonFile, Map<String, String> webRequest) {
    logger.debug(String.format("GET - %s called, params: %s ", serviceMethod, webRequest));
    if (null == jsonTosend) {
      jsonTosend = getJsonFromResource(jsonFile);
    }

    ResponseEntity responseEntity = ResponseEntity.ok(jsonTosend);
    if (jsonTosend.contains("httpStatus")) {
      try {
        ObjectMapper mapper = new ObjectMapper();
        List<Map> myObjects = mapper.readValue(jsonTosend, new TypeReference<List<Map>>() {
        });
        String myHttp = (String) myObjects.get(0).get("httpStatus");

        switch (myHttp) {
          case "400":
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            break;
          case "404":
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            break;
          case "500":
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            break;
          case "503":
            responseEntity = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
            break;
        }

      } catch (Exception e) {
        logger.error("Error during translate of httpStatus codes:", e);
      }

    }
    logger.debug(String.format("GET - %s returning: %s", serviceMethod, jsonTosend));
    return responseEntity;
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
