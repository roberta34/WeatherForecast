package com.example.WeatherForecast.repository;

import com.example.WeatherForecast.exception.DatabaseException;
import com.example.WeatherForecast.model.City;
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
public class CityRepository {
    private final DataSource dataSource;

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();

        String sql = """
                 SELECT c.id,
                        c.name,
                        co.name AS country,
                        c.population
                        FROM cities c
                        JOIN countries co
                        ON c.country_id = co.id
                        ORDER BY c.name
                """;

        try (
                Connection connection = dataSource.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery();
                ){
            while (resultSet.next()) {
                City city = new City();

                city.setId(
                        resultSet.getInt("id")
                );
                city.setName(
                        resultSet.getString("name")
                );

                city.setCountry(
                        resultSet.getString("country")
                );

                city.setPopulation(
                        resultSet.getInt("population")
                );

                cities.add(city);
            }

        } catch (SQLException e) {
            throw new DatabaseException(
                    "Failed to fetch cities",
                    e
            );
        }
        return cities;
    }
}
