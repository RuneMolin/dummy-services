package dk.cpr.dar.rest;

import dk.cpr.dar.service.StorageService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author Rune Molin, rmo@nine.dk
 */
@Component
@Path("/Records")
@Produces(MediaType.APPLICATION_JSON)
public class RecordsEndpoint {
  private final StorageService service;

  public RecordsEndpoint(StorageService service) {
    this.service = service;
  }

  @GET
  @Path("{entitet}")
  public String get(@PathParam("entitet") String entitet) {
    return service.getRecords(entitet);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("{entitet}")
  public String post(@PathParam("entitet") String entitet, String record) {
    service.setRecords(entitet, record);
    return get(entitet);
  }


}
