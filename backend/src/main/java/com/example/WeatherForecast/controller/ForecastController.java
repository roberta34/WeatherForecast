package com.example.WeatherForecast.controller;


import com.example.WeatherForecast.dto.response.ApiResponse;
import com.example.WeatherForecast.model.Forecast;
import com.example.WeatherForecast.repository.ForecastRepository;
import com.example.WeatherForecast.service.ForecastService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forecast")
@CrossOrigin("*")
@AllArgsConstructor
@Tag(name = "Forecasts")
public class ForecastController {
    private final ForecastService forecastService;

    @GetMapping("/{cityId}")
    public ApiResponse<List<Forecast>>
    getForecast(@PathVariable int cityId) {
        return new ApiResponse<>(
          "success",
          forecastService.getForecastByCity(cityId),
          "Forecast fetched successfully"
        );
    }
}
