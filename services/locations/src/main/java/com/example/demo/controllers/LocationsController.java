package com.example.demo.controllers;

import com.example.demo.entity.model.Location;
import com.example.demo.services.interfaces.LocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "locations")
public class LocationsController {

    @Autowired
    LocationsService locationsService;

    @GetMapping
    public @ResponseBody ResponseEntity<List<Location>> getLocations(@RequestParam("category") String category) {

        List<Location> locations = locationsService.getLocationsByCategory(category);

        return new ResponseEntity<>(locations, org.springframework.http.HttpStatus.OK);
    }

}
