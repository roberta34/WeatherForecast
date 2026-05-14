package com.example.WeatherForecast.service;

import com.example.WeatherForecast.exception.InvalidWeatherDataException;
import com.example.WeatherForecast.exception.ResourceNotFoundException;
import com.example.WeatherForecast.model.CityRanking;
import com.example.WeatherForecast.repository.RankingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RankingService {

    private final RankingRepository rankingRepository;

    public void generateRankings() {
        rankingRepository.generateRankings();
    }

    public List<CityRanking> getRankings() {

        List<CityRanking> rankings =
                rankingRepository.getRankings();

        if(rankings.isEmpty()) {

            throw new ResourceNotFoundException(
                    "No rankings found"
            );
        }

        return rankings;
    }

    public List<CityRanking> getHottestCities() {
        return rankingRepository.getHottestCities();
    }

    public List<CityRanking> getColdestCities() {
        return rankingRepository.getColdestCities();
    }

    public List<CityRanking> getTopRankings(int limit) {

        if(limit <= 0) {

            throw new InvalidWeatherDataException(
                    "Limit must be positive"
            );
        }

        return rankingRepository.getTopRankings(limit);
    }
}
