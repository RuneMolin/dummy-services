package dk.cpr.dar.services.dummy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by pjacobs2
 */
@RestController
public class Records extends DummyServiceNC {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private static final String MY_SERVICE = "CPRDeltaService/Records";

  @RequestMapping(method = RequestMethod.GET, path = "/" + MY_SERVICE)
  ResponseEntity myGet(@RequestParam Map<String,String>  webRequest) {
    return doGet(webRequest);
  }

  @RequestMapping(value = "/" + MY_SERVICE+"/{Entitet}", method = RequestMethod.POST)
  String mySet(@PathVariable String Entitet,@RequestBody Map<String, Object > webRequest) {
    return doSet(Entitet,webRequest);
  }
}
