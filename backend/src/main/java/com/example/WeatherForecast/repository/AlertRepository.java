package com.example.WeatherForecast.repository;

import com.example.WeatherForecast.exception.DatabaseException;
import com.example.WeatherForecast.model.Alert;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class AlertRepository {
    private DataSource dataSource;

    public List<Alert> getAlertsByCity(int cityId) {
        List<Alert> alerts =
                new ArrayList<>();

        String sql = """
                    SELECT *
                    FROM weather_alerts
                    WHERE city_id = ?
                    ORDER BY created_at DESC
                """;

        try(
                Connection connection =
                    dataSource.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);
                ) {
            preparedStatement.setInt(1, cityId);

            try (
                    ResultSet resultSet =
                            preparedStatement.executeQuery();
                    ){
                while (resultSet.next()) {
                    Alert alert = new Alert();
                    alert.setId(
                            resultSet.getInt("id")
                    );

                    alert.setCityId(
                            resultSet.getInt("city_id")
                    );

                    alert.setType(
                            resultSet.getString("alert_type")
                    );

                    alert.setMessage(
                            resultSet.getString("description")
                    );

                    alert.setCreatedAt(
                            resultSet.getTimestamp("created_at")
                                    .toLocalDateTime()
                    );

                    alerts.add(alert);
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException(
                    "Failed to fetch alerts",
                    e
            );
        }
        return alerts;

    }
}
