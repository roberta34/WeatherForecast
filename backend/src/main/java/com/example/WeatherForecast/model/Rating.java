package com.example.WeatherForecast.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class Rating {
    private Integer id;

    private Integer userId;

    private String username;

    private Integer cityId;

    private String cityName;

    private Integer ratingValue;

    private LocalDateTime createdAt;
}
