package scrappers;

import beans.Event;
import beans.PlainTextDate;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestInOrasEventsScrapper {


    public static final String FAKE_URL = "localhost:8888/fake_servlet/events/test1.html";
    public static final String FAKE_URL1 = "localhost:8888/fake_servlet/events/test2.html";
    public static final String INVALID_URL = "localhost:9090/";


    public static final String PALAS = "palas";
    public static final String IULIUS = "iulius";

    public PlainTextDate getDummyPlainTextDate() {
        return new PlainTextDate("1", "1", "mon");
    }

    private List<Event> getExpectedEvents() {
        List<Event> expectedEvents = new ArrayList<>();

        Event event1 = new Event("A", getDummyPlainTextDate(), getDummyPlainTextDate(), PALAS);
        Event event2 = new Event("A", getDummyPlainTextDate(), getDummyPlainTextDate(), PALAS);
        Event event3 = new Event("A", getDummyPlainTextDate(), getDummyPlainTextDate(), PALAS);

        expectedEvents.add(event1);
        expectedEvents.add(event2);
        expectedEvents.add(event3);

        return expectedEvents;
    }


    private List<Event> getExpectedEvents2() {
        List<Event> expectedEvents = new ArrayList<>();

        Event event1 = new Event("B", getDummyPlainTextDate(), getDummyPlainTextDate(), IULIUS);
        Event event2 = new Event("B", getDummyPlainTextDate(), getDummyPlainTextDate(), IULIUS);
        Event event3 = new Event("B", getDummyPlainTextDate(), getDummyPlainTextDate(), IULIUS);

        expectedEvents.add(event1);
        expectedEvents.add(event2);
        expectedEvents.add(event3);

        return expectedEvents;
    }

    @Test
    public void testScrappEventsOnFakePageTest1() throws IOException {
        List<Event> expectedEvents = getExpectedEvents();

        InOrasEventsScrapper a = new InOrasEventsScrapper();
        a.changeUrl(FAKE_URL);

        List<Event> actualEvents = a.scrappEvents();

        int i = 0;
        for (Event event : actualEvents) {
            assertEquals(event, expectedEvents.get(i));
            ++i;
        }
    }

    @Test
    public void testScrappEventsOnFakePageTest2() throws IOException {
        List<Event> expectedEvents = getExpectedEvents2();

        InOrasEventsScrapper a = new InOrasEventsScrapper();
        a.changeUrl(FAKE_URL1);

        List<Event> actualEvents = a.scrappEvents();

        int i = 0;
        for (Event event : actualEvents) {
            assertEquals(event, expectedEvents.get(i));
            ++i;
        }
    }

    @Test
    public void testScrappeEventsOnInvalidUrl() {
        InOrasEventsScrapper inOrasEventsScrapper = new InOrasEventsScrapper();
        inOrasEventsScrapper.changeUrl(INVALID_URL);
        boolean thrown = false;

        try {
            inOrasEventsScrapper.scrappEvents();
        } catch (Exception e) {
            thrown = true;
        }

        assertTrue(thrown);
    }
}
