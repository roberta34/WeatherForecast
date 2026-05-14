package com.example.WeatherForecast.service;

import com.example.WeatherForecast.dto.request.RatingRequest;
import com.example.WeatherForecast.model.Rating;
import com.example.WeatherForecast.repository.RatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;

    public void addRating(RatingRequest request) {
        ratingRepository.addRating(
                request.getUserId(),
                request.getCityId(),
                request.getRatingValue()
        );
    }

    public List<Rating> getRatings() {
        return ratingRepository.getRatings();
    }
}
