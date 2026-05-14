package com.example.WeatherForecast.model;

import lombok.Data;

@Data
public class Alert {
    private int id;
    private int cityId;

    private String type;
    private String message;

    private LocalDateTime createdAt;
}
