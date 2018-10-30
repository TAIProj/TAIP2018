package scrappers;

import beans.Event;

import java.io.IOException;
import java.util.List;

public interface Scrapper {
    List<Event> scrappEvents() throws IOException;
}
