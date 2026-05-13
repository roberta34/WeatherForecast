package com.example.WeatherForecast.controller;

import com.example.WeatherForecast.dto.response.ApiResponse;
import com.example.WeatherForecast.model.City;
import com.example.WeatherForecast.service.CityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cities")
@CrossOrigin("*")
@AllArgsConstructor
@Tag(name = "Cities")
public class CityController {
    private final CityService cityService;

    @GetMapping
    public ApiResponse<List<City>> getCities() {
        List<City> cities = cityService.getAllCities();

        return new ApiResponse<>(
                "success",
                cities,
                "Cities fetched successfully"
        );
    }
}
