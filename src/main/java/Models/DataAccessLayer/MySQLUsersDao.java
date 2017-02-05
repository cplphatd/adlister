package Models.DataAccessLayer;

import Models.Config;
import Models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

/**
 * Created by David on 2/4/17.
 */
public class MySQLUsersDao implements Users {
    private Connection connection;

    public MySQLUsersDao () {
        try {
            DriverManager.registerDriver(new Driver());
            this.connection = DriverManager.getConnection(
                    Config.url,
                    Config.user,
                    Config.password
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error @ MySQLUsersDao", e);
        }
    }

    @Override
    public User findByUsername(String username) {
        User existingUser = null;
        String sql = "SELECT * FROM users WHERE username = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                existingUser = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
            }

            return existingUser;
        } catch (SQLException e) {
            throw new RuntimeException("Error @ findByUsername", e);
        }
    }

    @Override
    public Long insert(User user) {
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new user.", e);
        }
    }

    @Override
    public User findUserByIDNumber(long idNumber) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idNumber);

            ResultSet resultSet = statement.executeQuery();

            return new User (
                    resultSet.getLong("id"),
                    resultSet.getString("username"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error @ findUserByIDNumber", e);
        }
    }
}
