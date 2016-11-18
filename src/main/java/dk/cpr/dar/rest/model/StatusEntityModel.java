package dk.cpr.dar.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Rune Molin, rmo@nine.dk
 */

// [{"entitet": "NavngivenVej","eventid":42},{"entitet":"NavngivenVejKommunedel","eventid":43}]
final public class StatusEntityModel {
  private final String entitet;
  private final int eventid;

  @JsonCreator
  public StatusEntityModel(@JsonProperty("entitet") String entitet, @JsonProperty("eventid") int eventid) {
    this.entitet = entitet;
    this.eventid = eventid;
  }

  public String getEntitet() {
    return entitet;
  }

  public int getEventid() {
    return eventid;
  }
}
