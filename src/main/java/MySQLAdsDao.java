import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 2/1/17.
 */
public class MySQLAdsDao implements Ads {
    private Statement statement;

    public MySQLAdsDao () {
        try {
            DriverManager.registerDriver(new Driver());
            Connection connection = DriverManager.getConnection(
                    Config.url,
                    Config.user,
                    Config.password
            );

            this.statement = connection.createStatement();

        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Ad> all() {

        try {
            ResultSet resultSet = statement.executeQuery("select * from ads");
            return createAdsFromResults(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            statement.executeUpdate(createInsertString(ad), Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    @Override
    public List<Ad> searchAdsByID(Long idNumber) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ads WHERE id = " + idNumber);
            return createAdsFromResults(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error @ searchAdsByID", e);
        }
    }

    @Override
    public List<Ad> searchAdsByTitle(String title) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ads WHERE title LIKE '%" + title + "%'");
            return createAdsFromResults(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error @ searchAdsByTitle", e);
        }
    }

    private Ad extractAd(ResultSet resultSet) throws SQLException {
        return new Ad(
                resultSet.getLong("id"),
                resultSet.getLong("user_id"),
                resultSet.getString("title"),
                resultSet.getString("description")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet resultSet) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (resultSet.next()) {
            ads.add(extractAd(resultSet));
        }
        return ads;
    }

    private String createInsertString(Ad ad) {
        return "INSERT INTO ads(user_id, title, description) VALUES "
                + "(" + ad.getUserId() + ", "
                + "'" + ad.getTitle() +"', "
                + "'" + ad.getDescription() + "')";
    }
}
