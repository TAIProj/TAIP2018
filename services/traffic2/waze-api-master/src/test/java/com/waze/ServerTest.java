package com.waze;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import com.waze.domain.WazeTrafficNotificationsResponse;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

public class ServerTest {


    @ClassRule
    public static final DropwizardAppRule<WazeConfig> RULE =
            new DropwizardAppRule<>(WazeApp.class, ResourceHelpers.resourceFilePath("conf.yml"));

    ObjectMapper mapper = new ObjectMapper();


    @Test
    public void testNotifications() {
        String latBottom = "123";
        String latTop = "123";
        String lonLeft = "123";
        String lonRight = "123";

        WazeTrafficNotificationsResponse wazeTrafficNotificationsResponse = null;
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        try {
            Response response = asyncHttpClient.prepareGet("http://localhost:"
                    + RULE.getLocalPort() +
                    "/waze/traffic-notifications?latBottom=" + latBottom + "&latTop=" + latTop + "&lonLeft=" + lonLeft + "&lonRight=" + lonRight)
                    .addHeader("Accept", "application/json").execute().get();

            String responseStr = response.getResponseBody();
            wazeTrafficNotificationsResponse = mapper.readValue(responseStr, WazeTrafficNotificationsResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            asyncHttpClient.close();
        }

        assert (wazeTrafficNotificationsResponse != null);
    }

}
