package com.example.WeatherForecast.controller;


import com.example.WeatherForecast.dto.request.RatingRequest;
import com.example.WeatherForecast.dto.response.ApiResponse;
import com.example.WeatherForecast.model.Rating;
import com.example.WeatherForecast.service.RatingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@CrossOrigin("*")
@Tag(name = "Ratings")
@AllArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping
    public ApiResponse<String> addRating(
            @RequestBody RatingRequest request
            ) {
        ratingService.addRating(request);

        return new ApiResponse<>(
                "success",
                null,
                "Rating added successfully"
        );
    }

    @GetMapping
    public ApiResponse<List<Rating>> getRatings() {
        List<Rating> ratings =
                ratingService.getRatings();

        return new ApiResponse<>(
                "success",
                ratings,
                "Ratings fetched succesfully"
        );
    }
}
