package com.example.WeatherForecast.service;

import com.example.WeatherForecast.exception.InvalidWeatherDataException;
import com.example.WeatherForecast.exception.ResourceNotFoundException;
import com.example.WeatherForecast.model.CityStatistics;
import com.example.WeatherForecast.repository.StatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;

    public List<CityStatistics>
    getAllStatistics() {

        List<CityStatistics> statistics =
                statisticsRepository
                        .getAllStatistics();

        if(statistics.isEmpty()) {

            throw new ResourceNotFoundException(
                    "No statistics found"
            );
        }

        return statistics;
    }

    public CityStatistics getStatisticsByCityId(
            Integer cityId
    ) {

        if(cityId == null || cityId <= 0) {

            throw new InvalidWeatherDataException(
                    "Invalid city id"
            );
        }

        CityStatistics statistics =
                statisticsRepository
                        .getStatisticsByCityId(cityId);

        if(statistics == null) {

            throw new ResourceNotFoundException(
                    "Statistics not found for city"
            );
        }

        return statistics;
    }
}
