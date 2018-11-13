package com.waze;


import com.fasterxml.jackson.databind.JsonNode;
import com.waze.domain.WazeRouteResponse;
import com.waze.domain.WazeRouteWithDirectionsResponse;
import com.waze.domain.WazeStreetPickerResult;
import com.waze.domain.WazeTrafficNotificationsResponse;
import com.waze.exceptions.WazeException;
import com.waze.service.WazeNotificationService;
import com.waze.service.WazeRouteService;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;


public class WazeRouteServiceTest {

    WazeRouteService wazeRouteService = new WazeRouteService();
    WazeNotificationService wazeNotificationService = new WazeNotificationService();

    @Test
    @Ignore
    public void getNotificationTest(){
        WazeTrafficNotificationsResponse res = wazeNotificationService.getNotifications("123", "123", "123", "123");
        assert(res.getAlerts().size() >= 1);
        assert(res.getJams().size() >= 1);
    }


    @Test
    public void testWazeGetAddressApi(){
        JsonNode addressResult = wazeRouteService.getAddress("Strada Palat", true);
        String addressResultStr = addressResult.get("name").asText();
        assert(addressResultStr.contains("Iași"));
    }

    @Test
    public void testWazeFreeTextToAddress(){
        WazeStreetPickerResult wazeStreetPickerResult = wazeRouteService.getAddressOptionsFromFreeText("Strada Palat, IS");
        assert(wazeStreetPickerResult.getAddressList().size() >= 1);
        assert(wazeStreetPickerResult.getAddressList().get(0).contains("Iași"));
    }


}
