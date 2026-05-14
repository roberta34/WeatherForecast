package com.example.WeatherForecast.exception;

import com.example.WeatherForecast.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        new ApiResponse<>(
                                "error",
                                null,
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ApiResponse<Void>>
    handleDatabase(DatabaseException e) {
        return ResponseEntity.status(
                HttpStatus.INTERNAL_SERVER_ERROR
        )
                .body(
                        new ApiResponse<>(
                                "error",
                                null,
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(InvalidWeatherDataException.class)
    public ResponseEntity<ApiResponse<Void>>
    handleInvalidWeather(
            InvalidWeatherDataException e
    ) {
        return ResponseEntity.badRequest()
                .body(
                        new ApiResponse<>(
                                "error",
                                null,
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>>
    handleGeneric(Exception e) {
        return ResponseEntity.internalServerError()
                .body(
                        new ApiResponse<>(
                                "error",
                                null,
                                "Unexpected server error"
                        )
                );
    }
}
