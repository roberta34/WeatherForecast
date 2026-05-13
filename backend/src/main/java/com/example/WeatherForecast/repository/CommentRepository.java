package com.example.WeatherForecast.repository;

import com.example.WeatherForecast.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springdoc.ui.AbstractSwaggerWelcome;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.sql.XAConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class CommentRepository {
    private final DataSource dataSource;
    private final AbstractSwaggerWelcome abstractSwaggerWelcome;

    public void addComment(
            Integer userId,
            Integer cityId,
            String commentText
    ) {
        String sql = """
                INSERT INTO comments 
                (user_id, city_id, comment_text)
                VALUES (?, ?, ?)
                """;

        try (
                Connection connection = dataSource.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);
                ){

            preparedStatement.setInt(1, userId);

            preparedStatement.setInt(2, cityId);

            preparedStatement.setString(3, commentText);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Comment> getComments() {
        List<Comment> comments = new ArrayList<>();

        String sql = """
                SELECT
                    cm.id,
                    cm.user_id,
                    u.username,
                    cm.city_id,
                    c.name AS city_name,
                    cm.comment_text,
                    cm.created_at
                FROM comments cm
                JOIN users u
                    ON cm.user_id = u.id
                JOIN cities c
                    ON cm.city_id = c.id
                ORDER BY cm.created_at DESC
                """;

        try (
                Connection connection =  dataSource.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        preparedStatement.executeQuery();
                ){
            while(resultSet.next()) {
                Comment comment = new Comment();

                comment.setId(
                        resultSet.getInt("id")
                );

                comment.setUserId(
                        resultSet.getInt("user_id")
                );

                comment.setUsername(
                        resultSet.getString("username")
                );

                comment.setCityId(
                        resultSet.getInt("city_id")
                );

                comment.setCityName(
                        resultSet.getString("city_name")
                );

                comment.setCommentText(
                        resultSet.getString("comment_text")
                );

                comment.setCreatedAt(
                        resultSet.
                                getTimestamp("created_at")
                                .toLocalDateTime()
                );

                comments.add(comment);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }
}
