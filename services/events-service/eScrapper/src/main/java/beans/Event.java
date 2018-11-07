package beans;

public class Event {
    private String eventName;
    private PlainTextDate plainTextDateBeg;
    private PlainTextDate plainTextDateEnd;

    public Event(String eventName, PlainTextDate plainTextDateBeg, PlainTextDate plainTextDateEnd, String eventLocation) {
        this.eventName = eventName;
        this.plainTextDateBeg = plainTextDateBeg;
        this.plainTextDateEnd = plainTextDateEnd;
        this.eventLocation = eventLocation;
    }

    public Event() {}

    public PlainTextDate getPlainTextDateBeg() {
        return plainTextDateBeg;
    }

    public void setPlainTextDateBeg(PlainTextDate plainTextDateBeg) {
        this.plainTextDateBeg = plainTextDateBeg;
    }

    public PlainTextDate getPlainTextDateEnd() {
        return plainTextDateEnd;
    }

    public void setPlainTextDateEnd(PlainTextDate plainTextDateEnd) {
        this.plainTextDateEnd = plainTextDateEnd;
    }

    private String eventLocation;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
}
