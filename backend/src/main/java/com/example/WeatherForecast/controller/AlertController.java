package com.example.WeatherForecast.controller;


import com.example.WeatherForecast.dto.response.ApiResponse;
import com.example.WeatherForecast.model.Alert;
import com.example.WeatherForecast.service.AlertService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin("*")
@AllArgsConstructor
@Tag(name = "Alerts")
public class AlertController {
    private final AlertService alertService;

    @GetMapping("/{cityId}")
    public ApiResponse<List<Alert>> getAlerts(
            @PathVariable int cityId
    ) {
        return new ApiResponse<>(
                "success",
                alertService.getAlertsByCity(cityId),
                "Alerts fetched successfully"
        );
    }

}
