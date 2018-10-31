package scrappers;

import beans.Event;
import beans.PlainTextDate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InOrasEventsScrapper implements Scrapper{

    public static final String TARGET_URL = "http://iasi.inoras.ro/evenimente/";
    public static final String ROOT_ELEMENT_CLASS = ".rec_description";
    public static final String DAY_NAME = "dayname";
    public static final String DAY = "day";
    public static final String MONTH = "month";
    public static final String EVENT_NAME_CONTAINER_TAG = "a";
    public static final String DATE_CLASS_MARKER = "date";

    public InOrasEventsScrapper() {}

    public List<Event> scrappEvents() throws IOException {
        Document doc = Jsoup.connect(TARGET_URL).get();
        Elements newsHeadlines = doc.select(ROOT_ELEMENT_CLASS);

        List<Event> events = new ArrayList<>();

        for (Element headline : newsHeadlines) {
            Elements es = headline.getElementsByClass(DATE_CLASS_MARKER);

            PlainTextDate beginDate = new PlainTextDate();
            PlainTextDate endDate = new PlainTextDate();
            Event event = new Event();
            event.setPlainTextDateBeg(beginDate);
            event.setPlainTextDateEnd(endDate);

            beginDate.setDay(es.get(0).getElementsByClass(DAY).get(0).text());
            beginDate.setMonth(es.get(0).getElementsByClass(MONTH).get(0).text());
            beginDate.setDayname(es.get(0).getElementsByClass(DAY_NAME).get(0).text());

//            System.out.println();

            if (es.size() > 1) {
                endDate.setDay(es.get(1).getElementsByClass(DAY).get(0).text());
                endDate.setMonth(es.get(1).getElementsByClass(MONTH).get(0).text());
                endDate.setDayname(es.get(1).getElementsByClass(DAY_NAME).get(0).text());
            } else {
                event.setPlainTextDateEnd(null);
            }
            event.setEventName(headline.getElementsByTag(EVENT_NAME_CONTAINER_TAG).text());

            events.add(event);
        }

        return events;
    }
}
