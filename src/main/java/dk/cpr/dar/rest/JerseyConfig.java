package dk.cpr.dar.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * @author Rune Molin, rmo@nine.dk
 */
@Component
public class JerseyConfig extends ResourceConfig {
  public JerseyConfig() {
    register(RecordsEndpoint.class);
    register(StatusEndpoint.class);
  }
}
