package com.example.WeatherForecast.controller;

import com.example.WeatherForecast.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cities")
@CrossOrigin("*")
@AllArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping
    public Object getCities() {
        return cityService.getAllCities();
    }
}
