package com.example.WeatherForecast.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

@NoArgsConstructor
@Getter
@Setter
public class RatingRequest {
    private Integer userId;

    private Integer cityId;

    private Integer ratingValue;
}

