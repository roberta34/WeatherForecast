package com.example.WeatherForecast.service;


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
        return alertRepository.getAlertsByCity(cityId);
    }
}
