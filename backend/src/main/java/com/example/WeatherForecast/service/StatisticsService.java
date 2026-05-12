package com.example.WeatherForecast.service;

import com.example.WeatherForecast.model.CityStatistics;
import com.example.WeatherForecast.repository.StatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;

    public List<CityStatistics> getAllStatistics() {
        return statisticsRepository.getAllStatistics();
    }

    public CityStatistics getStatisticsByCityId(
            Integer cityId
    ) {
        return statisticsRepository.getStatisticsByCityId(cityId);
    }
}
