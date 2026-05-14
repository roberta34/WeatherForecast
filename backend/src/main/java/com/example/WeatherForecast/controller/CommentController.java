package com.example.WeatherForecast.controller;


import com.example.WeatherForecast.dto.request.CommentRequest;
import com.example.WeatherForecast.dto.response.ApiResponse;
import com.example.WeatherForecast.model.Comment;
import com.example.WeatherForecast.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin("*")
@Tag(name = "Comments")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;


    @PostMapping
    public ApiResponse<String> addComment(
            @RequestBody CommentRequest request
            ) {
        commentService.addComment(request);

        return new ApiResponse<>(
                "success",
                null,
                "Comment added successfully"
        );
    }

    @GetMapping
    public ApiResponse<List<Comment>> getComments() {
        List<Comment> comments =
                commentService.getComments();

        return new ApiResponse<>(
                "success",
                comments,
                "Comments fetched successfully"
        );
    }
}
