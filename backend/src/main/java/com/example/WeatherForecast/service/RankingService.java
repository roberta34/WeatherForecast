package com.example.WeatherForecast.service;

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
        return rankingRepository.getRankings();
    }
}
