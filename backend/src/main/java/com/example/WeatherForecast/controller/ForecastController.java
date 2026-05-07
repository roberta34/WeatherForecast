package com.example.WeatherForecast.controller;


import com.example.WeatherForecast.repository.ForecastRepository;
import com.example.WeatherForecast.service.ForecastService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/forecast")
@CrossOrigin("*")
@AllArgsConstructor
public class ForecastController {
    private final ForecastService forecastService;

    @GetMapping("/{cityId}")
    public Object getForecast(
            @PathVariable int cityId
    ) {
        return forecastService.getForecastByCity(cityId);
    }
}
