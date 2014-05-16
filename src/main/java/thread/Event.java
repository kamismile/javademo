package thread;

import java.util.Date;

/**
 * Created by lidong on 14-5-16.
 */
public class Event {
    private Date date;
    private String event;

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }
}
