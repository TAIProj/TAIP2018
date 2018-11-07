package com.example.demo;

import com.example.demo.entity.model.Location;
import com.example.demo.services.interfaces.LocationsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private LocationsService locationsService;

    List<Location> locations = new ArrayList<>();
    Map<String, Object> result = new HashMap<>();

    @Test
    public void contextLoads() {
    }

    @Test
    public void whenApiIsCalled_ThenHttpStatusReturned_ShouldBe_200() {
        try {

            URL url = new URL("https://geocoder.api.here.com/6.2/geocode.json?app_id=MJodHR7FgrmWJh1YyBZf&app_code=ANkzjCNocTLMyl-PfYElzA&city=iasi&country=ro");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            assertThat(conn.getResponseCode()).isEqualTo(200);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenApiIsCalled_ThenCountryResponse_ShouldBe_ROU() {
        assertThat(callGeolocationAPI("Country").toString()).isEqualTo("ROU");
    }

    @Test
    public void whenApiIsCalled_ThenCityResponse_ShouldBe_Iasi() {
        assertThat(callGeolocationAPI("City").toString()).isEqualTo("Iasi");
    }

    public String callGeolocationAPI(String askedInformation){
        try {

            URL url = new URL("https://geocoder.api.here.com/6.2/geocode.json?app_id=MJodHR7FgrmWJh1YyBZf&app_code=ANkzjCNocTLMyl-PfYElzA&city=iasi&country=ro");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
            }

            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String json = br.readLine().toString();
            ObjectMapper mapper = new ObjectMapper();
            result = mapper.readValue(json, new TypeReference<Map<String, Object>>(){});

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.get(askedInformation).toString();
    }

}
