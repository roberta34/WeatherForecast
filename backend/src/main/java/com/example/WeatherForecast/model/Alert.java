package com.example.WeatherForecast.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Alert {
    private int id;
    private int cityId;

    private String type;
    private String message;

    private LocalDateTime createdAt;
}
