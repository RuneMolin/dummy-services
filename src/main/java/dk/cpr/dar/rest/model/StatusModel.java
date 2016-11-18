package dk.cpr.dar.rest.model;

/**
 * @author Rune Molin, rmo@nine.dk
 */

// [{"entitet": "NavngivenVej","eventid":42},{"entitet":"NavngivenVejKommunedel","eventid":43}]
public class StatusModel {
  private final StatusEntityModel[] entities;

  public StatusModel(StatusEntityModel[] entities) {
    this.entities = entities;
  }
}
