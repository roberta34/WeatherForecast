package com.example.WeatherForecast.controller;


import com.example.WeatherForecast.dto.response.ApiResponse;
import com.example.WeatherForecast.model.CityStatistics;
import com.example.WeatherForecast.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
@AllArgsConstructor

@Tag(name = "Statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping
    @Operation(
            summary = "Returns all statistics"
    )
    public ApiResponse<List<CityStatistics>>
    getAllStatistics() {

        return new ApiResponse<>(
                "success",
                statisticsService.getAllStatistics(),
                "Statistics fetched successfully"
        );
    }


    @GetMapping("/{cityId}")
    @Operation(
            summary = "Returns statistics for a city"
    )
    public ApiResponse<CityStatistics>
    getStatisticsByCityId(
            @PathVariable Integer cityId
    ) {

        return new ApiResponse<>(
                "success",
                statisticsService
                        .getStatisticsByCityId(cityId),
                "City statistics fetched successfully"
        );
    }
}
