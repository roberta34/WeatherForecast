package com.example.WeatherForecast.service;

import com.example.WeatherForecast.model.WeatherAnomaly;
import com.example.WeatherForecast.repository.AnomalyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnomalyService {
    private final AnomalyRepository anomalyRepository;

    public void detectAnomalies() {
        anomalyRepository.detectAnomalies();
    }

    public List<WeatherAnomaly> getAnomalies() {
        return anomalyRepository.getAnomalies();
    }
}
