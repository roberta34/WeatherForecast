package com.example.WeatherForecast.repository;

import com.example.WeatherForecast.exception.DatabaseException;
import com.example.WeatherForecast.model.CityRanking;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RankingRepository {

    private final DataSource dataSource;

    public RankingRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void generateRankings() {

        String sql = "CALL generate_city_rankings()";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.execute();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Failed to generate rankings",
                    e
            );
        }
    }

    public List<CityRanking> getRankings() {

        List<CityRanking> rankings = new ArrayList<>();

        String sql = """
                SELECT
                    cr.id,
                    cr.city_id,
                    c.name AS city_name,
                    cr.ranking_type,
                    cr.ranking_position,
                    cr.score,
                    cr.generated_at
                FROM city_rankings cr
                JOIN cities c
                    ON cr.city_id = c.id
                ORDER BY cr.ranking_position
                """;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                CityRanking ranking = new CityRanking();

                ranking.setId(resultSet.getInt("id"));

                ranking.setCityId(
                        resultSet.getInt("city_id")
                );

                ranking.setCityName(
                        resultSet.getString("city_name")
                );

                ranking.setRankingType(
                        resultSet.getString("ranking_type")
                );

                ranking.setRankingPosition(
                        resultSet.getInt("ranking_position")
                );

                ranking.setScore(
                        resultSet.getDouble("score")
                );

                ranking.setGeneratedAt(
                        resultSet.getTimestamp("generated_at").toLocalDateTime()
                );

                rankings.add(ranking);
            }

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Failed to generate rankings",
                    e
            );
        }

        return rankings;
    }

    public List<CityRanking> getHottestCities() {

        List<CityRanking> rankings =
                new ArrayList<>();

        String sql = """
            SELECT
                cr.id,
                cr.city_id,
                c.name AS city_name,
                cr.ranking_type,
                cr.ranking_position,
                cr.score,
                cr.generated_at
            FROM city_rankings cr
            JOIN cities c
                ON cr.city_id = c.id
            WHERE cr.ranking_type = 'HOTTEST'
            ORDER BY cr.ranking_position
            LIMIT 10
            """;

        try (
                Connection connection =
                        dataSource.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery();

        ) {

            while (resultSet.next()) {

                CityRanking ranking =
                        new CityRanking();

                ranking.setId(
                        resultSet.getInt("id")
                );

                ranking.setCityId(
                        resultSet.getInt("city_id")
                );

                ranking.setCityName(
                        resultSet.getString("city_name")
                );

                ranking.setRankingType(
                        resultSet.getString("ranking_type")
                );

                ranking.setRankingPosition(
                        resultSet.getInt("ranking_position")
                );

                ranking.setScore(
                        resultSet.getDouble("score")
                );

                ranking.setGeneratedAt(
                        resultSet.getTimestamp("generated_at")
                                .toLocalDateTime()
                );

                rankings.add(ranking);
            }

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Failed to fetch hottest cities",
                    e
            );
        }

        return rankings;
    }

    public List<CityRanking> getColdestCities() {

        List<CityRanking> rankings =
                new ArrayList<>();

        String sql = """
            SELECT
                cr.id,
                cr.city_id,
                c.name AS city_name,
                cr.ranking_type,
                cr.ranking_position,
                cr.score,
                cr.generated_at
            FROM city_rankings cr
            JOIN cities c
                ON cr.city_id = c.id
            ORDER BY cr.score ASC
            LIMIT 10
            """;

        try (
                Connection connection =
                        dataSource.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery();

        ) {

            while (resultSet.next()) {

                CityRanking ranking =
                        new CityRanking();

                ranking.setId(
                        resultSet.getInt("id")
                );

                ranking.setCityId(
                        resultSet.getInt("city_id")
                );

                ranking.setCityName(
                        resultSet.getString("city_name")
                );

                ranking.setRankingType(
                        resultSet.getString("ranking_type")
                );

                ranking.setRankingPosition(
                        resultSet.getInt("ranking_position")
                );

                ranking.setScore(
                        resultSet.getDouble("score")
                );

                ranking.setGeneratedAt(
                        resultSet.getTimestamp("generated_at")
                                .toLocalDateTime()
                );

                rankings.add(ranking);
            }

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Failed to fetch coldest cities",
                    e
            );
        }

        return rankings;
    }

    public List<CityRanking> getTopRankings(
            int limit
    ) {

        List<CityRanking> rankings =
                new ArrayList<>();

        String sql = """
            SELECT
                cr.id,
                cr.city_id,
                c.name AS city_name,
                cr.ranking_type,
                cr.ranking_position,
                cr.score,
                cr.generated_at
            FROM city_rankings cr
            JOIN cities c
                ON cr.city_id = c.id
            ORDER BY cr.ranking_position
            LIMIT ?
            """;

        try (
                Connection connection =
                        dataSource.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

        ) {

            statement.setInt(1, limit);

            try (
                    ResultSet resultSet =
                            statement.executeQuery();
            ) {

                while (resultSet.next()) {

                    CityRanking ranking =
                            new CityRanking();

                    ranking.setId(
                            resultSet.getInt("id")
                    );

                    ranking.setCityId(
                            resultSet.getInt("city_id")
                    );

                    ranking.setCityName(
                            resultSet.getString("city_name")
                    );

                    ranking.setRankingType(
                            resultSet.getString("ranking_type")
                    );

                    ranking.setRankingPosition(
                            resultSet.getInt("ranking_position")
                    );

                    ranking.setScore(
                            resultSet.getDouble("score")
                    );

                    ranking.setGeneratedAt(
                            resultSet.getTimestamp("generated_at")
                                    .toLocalDateTime()
                    );

                    rankings.add(ranking);
                }
            }

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Failed to fetch top rankings",
                    e
            );
        }

        return rankings;
    }
}