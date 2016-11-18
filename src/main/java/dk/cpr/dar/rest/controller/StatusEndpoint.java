package dk.cpr.dar.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.cpr.dar.rest.model.StatusModel;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import java.io.IOException;

/**
 * @author Rune Molin, rmo@nine.dk
 */
@Component
@Path("/Status")
public class StatusEndpoint {
  private final ObjectMapper mapper = new ObjectMapper();

  @GET
  public StatusModel get() {
    try {
      return mapper.readValue("[{'entitet': 'NavngivenVej','eventid':42},{'entitet':'NavngivenVejKommunedel','eventid':43}]", StatusModel.class);
    } catch (IOException e) {
      throw new WebApplicationException(e);
    }
  }


}
