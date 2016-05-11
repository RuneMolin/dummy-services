package dk.cpr.dar.domain.dar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by vagrant on 27-04-16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventStatus {

    private String entitet;
    private Long eventid;

    public EventStatus() {}

    public String getEntitet() {
        return entitet;
    }

    public void setEntitet(String entitet) {
        this.entitet = entitet;
    }

    public Long getEventid() {
        return eventid;
    }

    public void setEventid(Long eventid) {
        this.eventid = eventid;
    }

    @Override
    public String toString() {
        return "EventStatus{" +
                "entitet='" + entitet + '\'' +
                ", eventid=" + eventid +
                '}';
    }
}
