package com.example.WeatherForecast.controller;


import com.example.WeatherForecast.service.AlertService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin("*")
@AllArgsConstructor
public class AlertController {
    private final AlertService alertService;

    @GetMapping("/{cityId}")
    public Object getAlerts(
            @PathVariable int cityId
    ) {
        return alertService.getAlertsByCity(cityId);
    }

}
