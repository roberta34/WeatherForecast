package com.example.WeatherForecast.repository;


import com.example.WeatherForecast.model.Forecast;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class ForecastRepository {
    private final DataSource dataSource;

    public List<Forecast> getForecastByCity(int cityId) {
        List<Forecast> forecasts = new ArrayList<>();

        String sql = """
                SELECT *
                FROM weather_forecasts
                WHERE city_id = ?
                ORDER BY forecast_date
                """;

        try (
                Connection connection =
                        dataSource.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);
                ){
            preparedStatement.setInt(1, cityId);

            try (
                    ResultSet resultSet =
                            preparedStatement.executeQuery();
                    ){
                    while (resultSet.next()) {
                        Forecast forecast =
                                new Forecast();

                        forecast.setId(
                                resultSet.getInt("id")
                        );

                        forecast.setCityId(
                                resultSet.getInt("city_id")
                        );

                        forecast.setMinTemperature(
                                resultSet.getDouble(
                                        "temperature_min"
                                )
                        );

                        forecast.setMaxTemperature(
                                resultSet.getDouble("temperature_max")
                        );

                        forecast.setHumidity(
                                resultSet.getInt("humidity")
                        );

                        forecast.setWindSpeed(
                                resultSet.getInt("wind_speed")
                        );

                        forecast.setUvIndex(
                                resultSet.getInt(
                                        "uv_index"
                                )
                        );

                        forecast.setWeatherType(
                                resultSet.getString(
                                        "weather_type"
                                )
                        );

                        forecast.setForecastDate(
                                resultSet.getString(
                                        "forecast_date"
                                )
                        );

                        forecasts.add(forecast);
                    }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return forecasts;
    }

}
