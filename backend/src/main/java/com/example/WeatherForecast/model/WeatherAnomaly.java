package com.example.WeatherForecast.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
public class WeatherAnomaly {

    private Integer id;

    private Integer cityId;

    private String cityName;

    private Integer forecastId;

    private String anomalyType;

    private String description;

    private LocalDateTime detectedAt;

}
