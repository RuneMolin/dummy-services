package dk.cpr.dar.services.dummy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by vagrant on 13-05-16.
 */
@RestController
public class SogneinddelingService extends DummyService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private static final String MY_SERVICE = "ADRaAReplikeringSogneInddelingHent";
  private static final String MY_JSON = "sogneinddeling.json";

  @RequestMapping(method = RequestMethod.GET, path = "/" + MY_SERVICE)
  String myGet() {
    return doGet(MY_SERVICE, MY_JSON);
  }

  @RequestMapping(method = RequestMethod.POST, path = "/" + MY_SERVICE)
  String mySet(@RequestBody Map<String, Object> sogneinddeling) {
    return doSet(MY_SERVICE, sogneinddeling);
  }
}
