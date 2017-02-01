import com.mysql.cj.api.mysqla.result.Resultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 2/1/17.
 */
public class MySQLAdsDao implements Ads {
    private Connection connection = DriverManager.getConnection(
            Config.getUrl(),
            Config.getUsername(),
            Config.getPassword()
    );

    private Statement statement = connection.createStatement();
    private ResultSet resultSet;

    public MySQLAdsDao() throws SQLException {
    }

    @Override
    public List<Ad> all() throws SQLException {
        resultSet = statement.executeQuery("select * from ads");

        return createAdsFromResults(resultSet);
    }

    @Override
    public Long insert(Ad ad) throws SQLException {
        String insertCommand = "insert into ads(user_id, title, description)" + " values ("
                + ad.getUserId() + ", "
                + ad.getTitle() + ", "
                + ad.getDescription() + ")";

        statement.executeUpdate(insertCommand, statement.RETURN_GENERATED_KEYS);

        resultSet = statement.getGeneratedKeys();

        Long newAdId = null;
        if(resultSet.next()) {
            newAdId = resultSet.getLong(1);
        }
        return newAdId;
    }

    private Ad extractsAds (ResultSet resultSet) throws SQLException {
        Long newID = (long) resultSet.getInt("id");
        Long newUserID = (long) resultSet.getInt("user_id");
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
