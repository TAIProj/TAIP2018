package com.example.weather.control.client;

import com.example.weather.control.client.pojo.OpenWeatherResponse;

public interface OpenWeatherClient {
    String API_KEY = "1776c2eecb92d3e9c1e1120c8830099d";
    String CITY_ID = "675810";
    String BASE_API_URL = "http://api.openweathermap.org/data/2.5/weather";


    OpenWeatherResponse getCurrentWeather();
    void getForecast();
}
