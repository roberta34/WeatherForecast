package com.example.WeatherForecast.service;

import com.example.WeatherForecast.model.City;
import com.example.WeatherForecast.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.getAllCities();
    }
}
