package scrappers;

import beans.Event;

import java.util.ArrayList;
import java.util.List;

public class ScrappersObservers {
    private List<Scrapper> scrappers = new ArrayList<>();

    public ScrappersObservers() {}

    public void registerScrapper(Scrapper scrapper) {
        scrappers.add(scrapper);
    }

    public List<Event> combineScrappersOutput() { return null; }

}
