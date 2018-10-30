package managers;

import beans.Event;
import scrappers.InOrasEventsScrapper;
import scrappers.ScrappersObservers;

import java.io.IOException;
import java.util.List;

public class EventsManager {
    private static List<Event> events = null;
    private static ScrappersObservers scrappersObservers;

    private EventsManager() {}

    public synchronized static List<Event> getAvailableEvents() throws IOException {
        if (events == null) {
            events = scrappersObservers.combineScrappersOutput();
        }

        return events;
    }


    public synchronized void combineRegisteredScrappersOutput() {}
}
