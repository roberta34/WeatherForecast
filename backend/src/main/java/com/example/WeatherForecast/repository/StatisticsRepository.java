package com.example.WeatherForecast.repository;

import com.example.WeatherForecast.model.CityStatistics;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@AllArgsConstructor
public class StatisticsRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<CityStatistics> getAllStatistics() {

        String sql = """
                SELECT *
                FROM city_statistics
                """;

        return jdbcTemplate.query(
                sql,

                (rs, rowNum) -> {

                    CityStatistics s =
                            new CityStatistics();

                    s.setId(
                            rs.getInt("id")
                    );

                    s.setCityId(
                            rs.getInt("city_id")
                    );

                    s.setAverageTemperature(
                            rs.getDouble("average_temperature")
                    );

                    s.setMinTemperature(
                            rs.getDouble("min_temperature")
                    );

                    s.setMaxTemperature(
                            rs.getDouble("max_temperature")
                    );

                    s.setAverageHumidity(
                            rs.getDouble("average_humidity")
                    );

                    s.setAverageWindSpeed(
                            rs.getDouble("average_wind_speed")
                    );

                    return s;
                }
        );
    }

    public CityStatistics getStatisticsByCityId(
            Integer cityId
    ) {

        String sql = """
                SELECT *
                FROM city_statistics
                WHERE city_id = ?
                """;

        List<CityStatistics> result =
                jdbcTemplate.query(

                        connection -> {

                            PreparedStatement ps =
                                    connection.prepareStatement(sql);

                            ps.setInt(1, cityId);

                            return ps;
                        },

                        (rs, rowNum) -> {

                            CityStatistics s =
                                    new CityStatistics();

                            s.setId(
                                    rs.getInt("id")
                            );

                            s.setCityId(
                                    rs.getInt("city_id")
                            );

                            s.setAverageTemperature(
                                    rs.getDouble(
                                            "average_temperature"
                                    )
                            );

                            s.setMinTemperature(
                                    rs.getDouble(
                                            "min_temperature"
                                    )
                            );

                            s.setMaxTemperature(
                                    rs.getDouble(
                                            "max_temperature"
                                    )
                            );

                            s.setAverageHumidity(
                                    rs.getDouble(
                                            "average_humidity"
                                    )
                            );

                            s.setAverageWindSpeed(
                                    rs.getDouble(
                                            "average_wind_speed"
                                    )
                            );
                            s.setGeneratedAt(
                                    rs.getTimestamp("generated_at")
                                            .toLocalDateTime()
                            );

                            return s;
                        }
                );

        if(result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }
}