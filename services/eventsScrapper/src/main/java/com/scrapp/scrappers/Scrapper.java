package com.scrapp.scrappers;


import com.scrapp.beans.Event;

import java.io.IOException;
import java.util.List;

public interface Scrapper {
    List<Event> scrappEvents() throws IOException;
}
