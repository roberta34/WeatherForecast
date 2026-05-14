package com.example.WeatherForecast.service;

import com.example.WeatherForecast.dto.request.RatingRequest;
import com.example.WeatherForecast.exception.InvalidWeatherDataException;
import com.example.WeatherForecast.exception.ResourceNotFoundException;
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

        if(request.getUserId() == null
                || request.getUserId() <= 0) {

            throw new InvalidWeatherDataException(
                    "Invalid user id"
            );
        }

        if(request.getCityId() == null
                || request.getCityId() <= 0) {

            throw new InvalidWeatherDataException(
                    "Invalid city id"
            );
        }

        if(request.getRatingValue() == null
                || request.getRatingValue() < 1
                || request.getRatingValue() > 5) {

            throw new InvalidWeatherDataException(
                    "Rating must be between 1 and 5"
            );
        }

        ratingRepository.addRating(
                request.getUserId(),
                request.getCityId(),
                request.getRatingValue()
        );
    }

    public List<Rating> getRatings() {

        List<Rating> ratings =
                ratingRepository.getRatings();

        if(ratings.isEmpty()) {

            throw new ResourceNotFoundException(
                    "No ratings found"
            );
        }

        return ratings;
    }
}
