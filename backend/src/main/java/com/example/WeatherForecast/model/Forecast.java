package com.example.WeatherForecast.model;

import lombok.Data;

@Data
public class Forecast {
    private int id;
    private int cityId;

    private double minTemperature;
    private double maxTemperature;

    private int humidity;
    private double windSpeed;

    private int uvIndex;

    private String weatherType;
    private String forecastDate;
}
