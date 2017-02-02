import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 2/1/17.
 */
public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
        try {
            connection = DriverManager.getConnection(
                   config.getUrl(),
                   config.getUsername(),
                   config.getPassword()
           );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Ad> all() {
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from ads");
            return createAdsFromResults(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
//        Statement statement = null;
//        try {
//            statement = connection.createStatement();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        String insertCommand = "insert into ads(user_id, title, description)" + " values ("
//                + ad.getUserId() + ", "
//                + ad.getTitle() + ", "
//                + ad.getDescription() + ")";
//
//        statement.executeUpdate(insertCommand, statement.RETURN_GENERATED_KEYS);
//
//        ResultSet resultSet = statement.getGeneratedKeys();
//
//        Long newAdId = null;
//        if(resultSet.next()) {
//            newAdId = resultSet.getLong(1);
//        }
//        return newAdId;
        return  null;
    }

    private Ad extractsAds (ResultSet resultSet) throws SQLException {
        Long newID = resultSet.getLong("id");
        Long newUserID = resultSet.getLong("user_id");
        String newTitle = resultSet.getString("title");
        String newDescription = resultSet.getString("description");

        return new Ad (newID, newUserID, newTitle, newDescription);
    }

    private List<Ad> createAdsFromResults (ResultSet resultSet) throws SQLException {
        List<Ad> ads = new ArrayList<>();

        while (resultSet.next()) {
            ads.add(extractsAds(resultSet));
        }

        return ads;
    }
}
