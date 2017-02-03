package Models;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>The <code>MySQLAdsDao</code> is used to connect to a MySQL database and run queries that correspond to the
 * methods defined by the web application.</p>
 *
 * @author David Ryan Alviola
 * @since  3 February 2017
 */
public class MySQLAdsDao implements Ads {
    private Statement statement;

    /**
     * <p>Constructor for the <code>MySQLAdsDao</code> class. The <code>Statement</code> object is assigned in the
     * constructor so that the same object can be used to run queries throughout the class without having to create
     * new <code>Statement</code> objects within each method.</p>
     *
     * @throws SQLException
     */
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
            throw new RuntimeException("Error @ MySQLAdsDao constructor.", e);
        }
    }

    @Override
    public List<Ad> all() {

        try {
            ResultSet resultSet = statement.executeQuery("select * from ads");
            return createAdsFromResults(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error @ MySQLAdsDao.all().", e);
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
            throw new RuntimeException("Error @ MySQLAdsDao.insert.", e);
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
