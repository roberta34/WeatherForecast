    package com.example.WeatherForecast.service;

    import com.example.WeatherForecast.model.Forecast;
    import com.example.WeatherForecast.repository.ForecastRepository;
    import lombok.AllArgsConstructor;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @AllArgsConstructor
    @Service
    public class ForecastService {
        private final ForecastRepository forecastRepository;

        public List<Forecast> getForecastByCity(int cityId) {
            return forecastRepository.getForecastByCity(cityId);
        }
    }
