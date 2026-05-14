package com.example.WeatherForecast.repository;

import com.example.WeatherForecast.exception.DatabaseException;
import com.example.WeatherForecast.model.CityStatistics;
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
public class StatisticsRepository {

    private final DataSource dataSource;

    public List<CityStatistics> getAllStatistics() {

        List<CityStatistics> statistics =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM city_statistics
                ORDER BY average_temperature DESC
                """;

        try (

                Connection connection =
                        dataSource.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        preparedStatement.executeQuery();

        ) {

            while (resultSet.next()) {

                CityStatistics s =
                        new CityStatistics();

                s.setId(
                        resultSet.getInt("id")
                );

                s.setCityId(
                        resultSet.getInt("city_id")
                );

                s.setAverageTemperature(
                        resultSet.getDouble(
                                "average_temperature"
                        )
                );

                s.setMinTemperature(
                        resultSet.getDouble(
                                "min_temperature"
                        )
                );

                s.setMaxTemperature(
                        resultSet.getDouble(
                                "max_temperature"
                        )
                );

                s.setAverageHumidity(
                        resultSet.getDouble(
                                "average_humidity"
                        )
                );

                s.setAverageWindSpeed(
                        resultSet.getDouble(
                                "average_wind_speed"
                        )
                );

                s.setGeneratedAt(
                        resultSet
                                .getTimestamp("generated_at")
                                .toLocalDateTime()
                );

                statistics.add(s);
            }

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Failed to fetch statistics",
                    e
            );
        }

        return statistics;
    }

    public CityStatistics getStatisticsByCityId(
            Integer cityId
    ) {

        String sql = """
                SELECT *
                FROM city_statistics
                WHERE city_id = ?
                """;

        try (

                Connection connection =
                        dataSource.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);

        ) {

            preparedStatement.setInt(1, cityId);

            try (

                    ResultSet resultSet =
                            preparedStatement.executeQuery();

            ) {

                if(resultSet.next()) {

                    CityStatistics s =
                            new CityStatistics();

                    s.setId(
                            resultSet.getInt("id")
                    );

                    s.setCityId(
                            resultSet.getInt("city_id")
                    );

                    s.setAverageTemperature(
                            resultSet.getDouble(
                                    "average_temperature"
                            )
                    );

                    s.setMinTemperature(
                            resultSet.getDouble(
                                    "min_temperature"
                            )
                    );

                    s.setMaxTemperature(
                            resultSet.getDouble(
                                    "max_temperature"
                            )
                    );

                    s.setAverageHumidity(
                            resultSet.getDouble(
                                    "average_humidity"
                            )
                    );

                    s.setAverageWindSpeed(
                            resultSet.getDouble(
                                    "average_wind_speed"
                            )
                    );

                    s.setGeneratedAt(
                            resultSet
                                    .getTimestamp("generated_at")
                                    .toLocalDateTime()
                    );

                    return s;
                }

            }

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Failed to fetch city statistics",
                    e
            );
        }

        return null;
    }


}