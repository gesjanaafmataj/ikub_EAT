package repository;

import model.AuthenticatedUser;
import util.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static util.Queries.LOGIN;

public class UserRepository {

    public AuthenticatedUser login(String email, String password) {
        AuthenticatedUser auth = null;
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(LOGIN)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                auth = new AuthenticatedUser();
                auth.setName(result.getString("name"));
                auth.setEmail(result.getString("email"));
                auth.setRole(result.getString("role"));
                auth.setId(result.getInt("id"));
                auth.setRestaurantId(result.getInt("restaurant_id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return auth;
    }
}
