package com.example.WeatherForecast.service;

import com.example.WeatherForecast.dto.request.CommentRequest;
import com.example.WeatherForecast.exception.InvalidWeatherDataException;
import com.example.WeatherForecast.exception.ResourceNotFoundException;
import com.example.WeatherForecast.model.Comment;
import com.example.WeatherForecast.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void addComment(CommentRequest request) {

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

        if(request.getCommentText() == null
                || request.getCommentText().isBlank()) {

            throw new InvalidWeatherDataException(
                    "Comment cannot be empty"
            );
        }

        commentRepository.addComment(
                request.getUserId(),
                request.getCityId(),
                request.getCommentText()
        );
    }

    public List<Comment> getComments() {

        List<Comment> comments =
                commentRepository.getComments();

        if(comments.isEmpty()) {

            throw new ResourceNotFoundException(
                    "No comments found"
            );
        }

        return comments;
    }
}
