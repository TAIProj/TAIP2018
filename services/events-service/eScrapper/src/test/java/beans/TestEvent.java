package beans;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestEvent {

    @Test
    public void testGetters() {
        PlainTextDate plainTextDate = new PlainTextDate("1", "1", "marti");
        Event event = new Event("a", plainTextDate, plainTextDate, "palas");

        assertEquals(event.getEventLocation(), "palas");
        assertEquals(event.getEventName(), "a");
        assertEquals(event.getPlainTextDateBeg(), plainTextDate);
        assertEquals(event.getPlainTextDateEnd(), plainTextDate);
    }

    @Test
    public void testSetters() {
        PlainTextDate plainTextDate = new PlainTextDate("1", "1", "marti");
        Event event = new Event();
        event.setEventName("a");
        event.setPlainTextDateEnd(plainTextDate);
        event.setPlainTextDateBeg(plainTextDate);
        event.setEventLocation("palas");

        assertEquals(event.getEventLocation(), "palas");
        assertEquals(event.getEventName(), "a");
        assertEquals(event.getPlainTextDateBeg(), plainTextDate);
        assertEquals(event.getPlainTextDateEnd(), plainTextDate);
    }

}
