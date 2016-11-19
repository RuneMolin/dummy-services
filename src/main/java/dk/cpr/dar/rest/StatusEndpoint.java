package dk.cpr.dar.rest;

import dk.cpr.dar.service.StorageService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * @author Rune Molin, rmo@nine.dk
 */
@Component
@Path("/Status")
@Produces(MediaType.APPLICATION_JSON)
public class StatusEndpoint {
  private final StorageService service;

  public StatusEndpoint(StorageService service) {
    this.service = service;
  }

  @GET
  public Map<String, Integer> get() {
    return service.getStatus();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Map<String, Integer> post(Map<String, Integer> nyStatus) {
    service.updateStatus(nyStatus);
    return get();
  }
}

