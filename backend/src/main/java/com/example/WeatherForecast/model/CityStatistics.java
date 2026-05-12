package com.example.WeatherForecast.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

import java.time.LocalDateTime;
import java.util.Locale;

@NoArgsConstructor
@Setter
@Getter
public class CityStatistics {
    private Integer id;

    private Integer cityId;

    private Double averageTemperature;

    private Double minTemperature;

    private Double maxTemperature;

    private Double averageHumidity;
    private Double averageWindSpeed;

    private LocalDateTime generatedAt;
}
