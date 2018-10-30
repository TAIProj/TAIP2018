package com.example.weather.boundry.controller;


import com.example.weather.control.service.WeatherService;
import com.example.weather.entity.model.Weather;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController(value = "/weather")
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public Weather getCurrentWeather() {
        return weatherService.getCurrentWeather();
    }

}
