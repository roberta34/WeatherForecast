package com.example.WeatherForecast.service;


import com.example.WeatherForecast.exception.InvalidWeatherDataException;
import com.example.WeatherForecast.model.Alert;
import com.example.WeatherForecast.repository.AlertRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlertService {
    private final AlertRepository alertRepository;

    public List<Alert> getAlertsByCity(int cityId) {
        if(cityId <= 0) {
            throw new InvalidWeatherDataException(
                    "City id must be positive"
            );
        }

        return alertRepository.getAlertsByCity(cityId);
    }
}
