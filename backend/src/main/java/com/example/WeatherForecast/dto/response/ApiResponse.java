package com.example.WeatherForecast.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class ApiResponse <T>{
    private String status;
    private T data;
    private String message;
}
