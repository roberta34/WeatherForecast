package com.example.WeatherForecast.service;

import com.example.WeatherForecast.exception.ResourceNotFoundException;
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
        List<City> cities =
                cityRepository.getAllCities();

        if(cities.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No cities found"
            );
        }

        return cities;
    }
}
