    package com.example.WeatherForecast.service;

    import com.example.WeatherForecast.exception.InvalidWeatherDataException;
    import com.example.WeatherForecast.exception.ResourceNotFoundException;
    import com.example.WeatherForecast.model.Forecast;
    import com.example.WeatherForecast.repository.ForecastRepository;
    import lombok.AllArgsConstructor;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @AllArgsConstructor
    @Service
    public class ForecastService {
        private final ForecastRepository forecastRepository;

        public List<Forecast> getForecastByCity(
                int cityId
        ) {

            if(cityId <= 0) {

                throw new InvalidWeatherDataException(
                        "City id must be positive"
                );
            }

            List<Forecast> forecasts =
                    forecastRepository
                            .getForecastByCity(cityId);

            if(forecasts.isEmpty()) {

                throw new ResourceNotFoundException(
                        "No forecasts found for city"
                );
            }

            return forecasts;
        }
    }
