package dk.cpr.dar.services.dummy;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by vagrant on 10-05-16.
 */
//@RestController
public class EventStatusService extends DummyService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private static final String MY_SERVICE = "ADRaAReplikeringEventStatusHent";
  private static final String MY_JSON = "eventStatus.json";

  @RequestMapping(method = RequestMethod.GET, path = "/"+MY_SERVICE)
  ResponseEntity myGet(@RequestParam Map<String,String> webRequest) {
    return doGet(MY_SERVICE, MY_JSON, webRequest);
  }

  @RequestMapping(method = RequestMethod.POST,  path = "/"+MY_SERVICE)
  String mySet(@RequestBody List<Map<String, Object>> eventStatusList) {
    return doSet(MY_SERVICE, eventStatusList);
  }
}
