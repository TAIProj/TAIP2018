package com.example.demo.services;

import com.example.demo.entity.model.Location;
import com.example.demo.services.interfaces.LocationsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocationsServiceImpl implements LocationsService {

    @Override
    public List<Location> getLocationsByCategory(String category) {

        List<Location> locations = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();

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

        //result.get(askedInformation).toString();
        return locations;
    }
}
