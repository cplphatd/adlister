import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 2/2/17.
 */
public class MySQLAdsDao implements Ads{
    private Statement statement;

    public MySQLAdsDao () {
        try {
            DriverManager.registerDriver(new Driver());

            Connection connection = DriverManager.getConnection(
                    Config.url,
                    Config.username,
                    Config.password
            );

            this.statement = connection.createStatement();

        } catch (SQLException e) {
            throw new RuntimeException("Error @ MySQLAdsDao", e);
        }
    }

    @Override
    public List<Ad> all() {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ads");
            return  createAdsList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error @ MySQLAdsDao.all()", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        return null;
    }

    private Ad extractAds (ResultSet resultSet) throws SQLException {
        return new Ad(
                resultSet.getLong("id"),
                resultSet.getLong("user_id"),
                resultSet.getString("title"),
                resultSet.getString("description")
        );
    }

    private List<Ad> createAdsList (ResultSet resultSet) throws SQLException {
        List<Ad> ads = new ArrayList<>();

        while (resultSet.next()) {
            ads.add(extractAds(resultSet));
        }

        return ads;
    }
}
