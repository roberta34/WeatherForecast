package com.example.WeatherForecast.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
public class CityRanking {
    private Integer id;
    private Integer cityId;
    private String cityName;
    private String rankingType;
    private Integer rankingPosition;
    private Double score;
    private LocalDateTime generatedAt;


}
