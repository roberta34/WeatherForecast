package com.example.WeatherForecast.service;

import com.example.WeatherForecast.dto.request.CommentRequest;
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
        commentRepository.addComment(
                request.getUserId(),
                request.getCityId(),
                request.getCommentText()
        );
    }

    public List<Comment> getComments() {
        return commentRepository.getComments();
    }
}
