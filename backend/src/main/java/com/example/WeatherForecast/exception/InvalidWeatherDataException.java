package com.example.WeatherForecast.exception;

public class InvalidWeatherDataException extends RuntimeException {
    public InvalidWeatherDataException(String message) {
        super(message);
    }
}
