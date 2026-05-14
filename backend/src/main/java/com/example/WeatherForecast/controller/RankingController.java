package com.example.WeatherForecast.controller;


import com.example.WeatherForecast.dto.response.ApiResponse;
import com.example.WeatherForecast.model.CityRanking;
import com.example.WeatherForecast.service.RankingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ranking")
@CrossOrigin("*")
@AllArgsConstructor
@Tag(name = "Rankings")
public class RankingController {
    private final RankingService rankingService;

    @PostMapping("/generate")
    public ApiResponse<String> generateRankings() {
        rankingService.generateRankings();

        return new ApiResponse<>(
                "success",
                null,
                "Rankings generated successfully"
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

    @GetMapping("/hottest")
    public ApiResponse<List<CityRanking>>
    getHottestCities() {

        return new ApiResponse<>(
                "success",
                rankingService.getHottestCities(),
                "Hottest cities fetched successfully"
        );
    }

    @GetMapping("/coldest")
    public ApiResponse<List<CityRanking>>
    getColdestCities() {

        return new ApiResponse<>(
                "success",
                rankingService.getColdestCities(),
                "Coldest cities fetched successfully"
        );
    }

    @GetMapping("/top/{limit}")
    public ApiResponse<List<CityRanking>>
    getTopRankings(
            @PathVariable int limit
    ) {

        return new ApiResponse<>(
                "success",
                rankingService.getTopRankings(limit),
                "Top rankings fetched successfully"
        );
    }
}
