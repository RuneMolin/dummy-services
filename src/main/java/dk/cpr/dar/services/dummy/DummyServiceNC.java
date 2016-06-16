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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vagrant on 13-05-16.
 */
public abstract class DummyServiceNC {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private ResourceLoader resourceLoader;

  protected Map<String, String> jsonTosendMap = new HashMap<String, String>();

  protected ObjectMapper mapper = new ObjectMapper();

  protected ResponseEntity doGet(Map<String, String> webRequest) {
    String entitet = webRequest.get("Entitet");
    String jsonTosend = jsonTosendMap.get(entitet);

    logger.debug(String.format("GET called, params: %s ",  webRequest));
    if (null == jsonTosend) {
      jsonTosend = getJsonFromResource(webRequest);
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
    logger.debug(String.format("GET returning: %s", jsonTosend));
    return responseEntity;
  }


  protected String doSet(String entitet, Map<String,Object>  data) {
    logger.debug(String.format("POST - %s called", entitet));

    return getJsonSet(entitet, data);
  }

  private String getJsonSet(String entitet, Map<String,Object>  data) {
    String jsonTosend = null;
    try {
      jsonTosend = mapper.writeValueAsString(data);
      jsonTosendMap.put(entitet, jsonTosend);
      logger.debug(String.format("POST - %s setting json to: %s", entitet, jsonTosend));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      logger.error(String.format("POST - %s exception", e));
    }

    return jsonTosend;
  }


  protected String getJsonFromResource(Map<String, String> webRequest) {
    String json = null;
    String jsonFile = webRequest.get("Entitet")+".json";
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
