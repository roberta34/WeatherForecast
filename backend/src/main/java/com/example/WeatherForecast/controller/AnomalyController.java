package com.example.WeatherForecast.controller;

import com.example.WeatherForecast.dto.response.ApiResponse;
import com.example.WeatherForecast.model.WeatherAnomaly;
import com.example.WeatherForecast.service.AnomalyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomalies")
@CrossOrigin("*")
@AllArgsConstructor
@Tag(name = "Weather Anomalies")
public class AnomalyController {
    private final AnomalyService anomalyService;

    @PostMapping("/detect")
    public ApiResponse<String> detectAnomalies() {
        anomalyService.detectAnomalies();

        return new ApiResponse<>(
            "success",
            null,
            "Anomalies detected successfully"
        );
    }

    @GetMapping
    public ApiResponse<List<WeatherAnomaly>> getAnomalies() {
        List<WeatherAnomaly> anomalies =
                anomalyService.getAnomalies();

        return new ApiResponse<>(
                "success",
                anomalies,
                "Anomalies fetched successfully"
        );
    }
}
