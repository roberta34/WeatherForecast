package com.example.WeatherForecast.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

@NoArgsConstructor
@Setter
@Getter
public class CommentRequest {

    private Integer userId;

    private Integer cityId;

    private String commentText;
}
