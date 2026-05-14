package com.example.WeatherForecast.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
public class Comment {

    private Integer id;

    private Integer userId;

    private String username;

    private Integer cityId;

    private String cityName;

    private String commentText;

    private LocalDateTime createdAt;
}
