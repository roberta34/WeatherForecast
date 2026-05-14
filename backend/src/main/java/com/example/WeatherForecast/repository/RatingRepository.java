package com.example.WeatherForecast.repository;

import com.example.WeatherForecast.exception.DatabaseException;
import com.example.WeatherForecast.model.Rating;
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
public class RatingRepository {
    private final DataSource dataSource;

    public void addRating(
            Integer userId,
            Integer cityId,
            Integer ratingValue
    ) {
        String sql = """
                    INSERT INTO ratings
                    (user_id, city_id, rating_value)
                    VALUES (?, ?, ?)
                """;

        try (
                Connection connection = dataSource.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);

                ){
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, cityId);
            preparedStatement.setInt(3, ratingValue);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            throw new DatabaseException(
                    "Failed to add rating",
                    e
            );
        }
    }

    public List<Rating> getRatings() {
        List<Rating> ratings =
                new ArrayList<>();

        String sql = """
                    SELECT 
                        r.id,
                        r.user_id,
                        u.username,
                        r.city_id,
                        c.name AS city_name,
                        r.rating_value,
                        r.created_at
                    FROM ratings r
                    JOIN users u
                         ON r.user_id = u.id
                    JOIN cities c
                        ON r.city_id = c.id
                    ORDER BY r.created_at DESC
                """;

        try (
                Connection connection = dataSource.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        preparedStatement.executeQuery();
                ){
            while (resultSet.next()) {
                Rating rating = new Rating();

                rating.setId(
                        resultSet.getInt("id")
                );

                rating.setUserId(
                        resultSet.getInt("user_id")
                );

                rating.setUsername(
                        resultSet.getString("username")
                );

                rating.setCityId(
                        resultSet.getInt("city_id")
                );

                rating.setCityName(
                        resultSet.getString("city_name")
                );

                rating.setRatingValue(
                        resultSet.getInt("rating_value")
                );

                rating.setCreatedAt(
                        resultSet
                                .getTimestamp("created_at")
                                .toLocalDateTime()
                );
                ratings.add(rating);
            }
        } catch (SQLException e) {

            throw new DatabaseException(
                    "Failed to add rating",
                    e
            );
        }
        return ratings;
    }
}
