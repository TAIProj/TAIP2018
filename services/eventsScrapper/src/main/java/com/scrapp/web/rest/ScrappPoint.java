
package com.scrapp.web.rest;

import com.scrapp.beans.Event;
import com.scrapp.scrappers.InOrasEventsScrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns="/testEndpoint")

@RestController
@RequestMapping("/scrapp")
public class ScrappPoint {
    private static final long serialVersionUID = 1L;

    @GetMapping("/do")
    protected List<Event> doScrapp()
        throws ServletException, IOException {

        List<Event> events = (new InOrasEventsScrapper()).scrappEvents();

        return events;
    }

}
