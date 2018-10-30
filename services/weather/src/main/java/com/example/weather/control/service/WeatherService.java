package com.example.weather.control.service;

import com.example.weather.entity.model.Weather;

public interface WeatherService extends CrudService<Weather> {

    Weather getCurrentWeather();
}
