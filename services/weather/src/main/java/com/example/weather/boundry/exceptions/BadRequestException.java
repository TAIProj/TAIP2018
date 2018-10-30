package com.example.weather.boundry.exceptions;

public class BadRequestException extends WeatherGenericException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
