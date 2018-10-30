/*******************************************************************************
 * (c) Copyright IBM Corporation 2017.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package eventsScrapper;

import beans.Event;
import scrappers.InOrasEventsScrapper;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/testEndpoint")
public class TestingEndpoint extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        List<Event> events = (new InOrasEventsScrapper()).scrappEvents();

        for (Event event : events) {
            response.getWriter().append(event.getEventName() + "\n");

            response.getWriter().append("\t " + event.getPlainTextDateBeg().getDay());
            response.getWriter().append(" " + event.getPlainTextDateBeg().getMonth());
            response.getWriter().append(" " + event.getPlainTextDateBeg().getDayname());

            if (event.getPlainTextDateEnd() != null) {
                response.getWriter().append(" pana pe ");
                response.getWriter().append("\t " + event.getPlainTextDateEnd().getDay());
                response.getWriter().append(" " + event.getPlainTextDateEnd().getMonth());
                response.getWriter().append(" " + event.getPlainTextDateEnd().getDayname());
                response.getWriter().append("\n");
            } else {
                response.getWriter().append("\n");
            }

        }

        response.getWriter().append("Hello! How are you today?");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // logic not yet implemented
        doGet(request, response);
    }
}
