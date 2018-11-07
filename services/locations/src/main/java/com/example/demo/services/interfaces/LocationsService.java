package com.example.demo.services.interfaces;

import com.example.demo.entity.model.Location;

import java.util.List;

public interface LocationsService {

    List<Location> getLocationsByCategory(String category);

}
