package com.example.weather.boundry.exceptions;

public class WeatherGenericException extends Exception {

    public WeatherGenericException(String message) {
        super(message);
    }

    public WeatherGenericException(String message, Throwable throwable) {
        super(message, throwable);
    }
}