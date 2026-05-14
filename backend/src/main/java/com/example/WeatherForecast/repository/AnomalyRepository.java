package com.example.WeatherForecast.repository;

import com.example.WeatherForecast.exception.DatabaseException;
import com.example.WeatherForecast.model.WeatherAnomaly;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class AnomalyRepository {
    private final DataSource dataSource;

    public void detectAnomalies() {
        String sql = "CALL detect_weather_anomalies()";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);
                        ){
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new DatabaseException(
                    "Failed to detect anomalies",
                    e
            );
        }
    }

    public List<WeatherAnomaly> getAnomalies() {
        List<WeatherAnomaly> anomalies =
                new ArrayList<>();

        String sql = """
                SELECT 
                    wa.id,
                    wa.city_id,
                    c.name AS city_name,
                    wa.forecast_id,
                    wa.anomaly_type,
                    wa.description, 
                    wa.detected_at
                FROM weather_anomalies wa 
                JOIN cities c
                    ON wa.city_id = c.id
                ORDER BY wa.detected_at DESC
                """;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                ){
            while (resultSet.next()) {
                WeatherAnomaly anomaly =
                        new WeatherAnomaly();

                anomaly.setId(
                        resultSet.getInt("id")
                );

                anomaly.setCityId(
                        resultSet.getInt("city_id")
                );

                anomaly.setCityName(
                        resultSet.getString("city_name")
                );

                anomaly.setForecastId(
                        resultSet.getInt("forecast_id")
                );

                anomaly.setAnomalyType(
                        resultSet.getString("anomaly_type")
                );

                anomaly.setDescription(
                        resultSet.getString("description")
                );

                anomaly.setDetectedAt(
                        resultSet.getTimestamp("detected_at")
                                .toLocalDateTime()
                );

                anomalies.add(anomaly);
            }
        } catch (SQLException e) {
            throw new DatabaseException(
                    "Failed to detect anomalies",
                    e
            );
        }
        return anomalies;
    }
}
