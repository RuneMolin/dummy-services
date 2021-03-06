package dk.cpr.dar.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rune Molin, rmo@nine.dk
 */
@Service
public class StorageService {
  private final Map<String, Integer> status = new HashMap<>();
  private Map<String, String> records = new HashMap<>();

  @PostConstruct
  void init() {
    status.put("NavngivenVej", 42);
    status.put("NavngivenVejKommunedel", 43);

    records.put("NavngivenVej",
      "{#records#:[{#vejadresseringsnavn#:#Luskestræde#,#vejnavn#:#Luskestræde#,#eventopret#:null,#eventopdater#:null,#rowkey#:11,#id#:#fb51b8f4-9b59-11e6-9f33-a24fc0d9649c#,#registreringfra#:#2016-10-24T10:47:00Z#,#registreringtil#:null,#virkningfra#:#2016-10-25T00:00:00Z#,#virkningtil#:null,#status#:#3#,#eventid#:42}]}#".replace("#", "\"")
    );

    records.put("NavngivenvejKommunedel",
      "{#records#: [{#kommune#:#0370#,#navngivenvej_id#:#fb51b8f4-9b59-11e6-9f33-a24fc0d9649c#,#vejkode#:#3099#,#eventopret#:null,#eventopdater#:null,#rowkey#:6,#id#:#68660fe6-2d29-4057-9866-2fee101ec1f4#,#registreringfra#:#2016-10-10T11:55:54Z#,#registreringtil#:null,#virkningfra#:#2016-10-26T00:00:00Z#,#virkningtil#:null,#status#:#3#,#eventid#:43}]}".replace("#", "\"")
    );

  }

  public Map<String, Integer> getStatus() {
    return status;
  }

  public void updateStatus(Map<String, Integer> nyStatus) {
    status.clear();
    status.putAll(nyStatus);
  }

  public Map<String, String> getRecords() {
    return records;
  }


  public String getRecords(String entitet) {
    return records.get(entitet);
  }

  public void setRecords(String entitet, String records) {
    this.records.put(entitet, records);
  }

}
