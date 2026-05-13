package com.example.WeatherForecast.controller;


import com.example.WeatherForecast.dto.response.ApiResponse;
import com.example.WeatherForecast.model.CityRanking;
import com.example.WeatherForecast.service.RankingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ranking")
@CrossOrigin("*")
@AllArgsConstructor
public class RankingController {
    private final RankingService rankingService;

    @PostMapping("/generate")
    public ApiResponse<String> generateRankings() {
        rankingService.generateRankings();

        return new ApiResponse<>(
                "success",
                "Rankings generated successfully",
                null
        );
    }

    @GetMapping
    public ApiResponse<List<CityRanking>> getRankings() {
        List<CityRanking> rankings = rankingService.getRankings();

        return new ApiResponse<>(
                "success",
                rankings,
                "Rankings fetched successfully"
        );
    }
}
