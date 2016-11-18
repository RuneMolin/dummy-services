package dk.cpr.dar.rest.controller;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Rune Molin, rmo@nine.dk
 */
@Component
@Path("/Records")
public class RecordsEndpoint {

  @GET
  public String get() {
    return "{records:[]}";
  }

  /*
  curl -i \
-H "Content-Type:application/json" \
-X POST --data '{"records":[{"vejadresseringsnavn":"Luskestræde","vejnavn":"Luskestræde","eventopret":null,"eventopdater":null,"rowkey":11,"id":"fb51b8f4-9b59-11e6-9f33-a24fc0d9649c","registreringfra":"2016-10-24T10:47:00Z","registreringtil":null,"virkningfra":"2016-10-25T00:00:00Z","virkningtil":null,"status":"3","eventid":42}]}"' "$RECORDS_URL/NavngivenVej"

# POST NavngivenvejKommunedel
curl -i \
-H "Content-Type:application/json" \
-X POST --data '{"records": [{"kommune":"0370","navngivenvej_id":"fb51b8f4-9b59-11e6-9f33-a24fc0d9649c","vejkode":"3099","eventopret":null,"eventopdater":null,"rowkey":6,"id":"68660fe6-2d29-4057-9866-2fee101ec1f4","registreringfra":"2016-10-10T11:55:54Z","registreringtil":null,"virkningfra":"2016-10-26T00:00:00Z","virkningtil":null,"status":"3","eventid":43}]}' "$RECORDS_URL/NavngivenVejKommunedel"

   */
}
