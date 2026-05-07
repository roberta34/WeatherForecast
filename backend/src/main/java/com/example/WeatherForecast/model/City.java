package com.example.WeatherForecast.model;

import lombok.*;

@Data
public class City {
    private int id;
    private String name;
    private String country;
    private int population;
}
