import java.sql.*;
import java.util.List;

/**
 * Created by David on 2/1/17.
 */
public class MySQLAdsDao implements Ads {
    Connection connection = DriverManager.getConnection(
            Config.getUrl(),
            Config.getUsername(),
            Config.getPassword()
    );

    Statement statement = connection.createStatement();
    ResultSet resultSet;

    private List<Ad> ads;
    private Ad ad;

    public MySQLAdsDao() throws SQLException {
    }

    @Override
    public List<Ad> all() throws SQLException {
        resultSet = statement.executeQuery("select * from adlister_db.ads");

        int i = 0;
        while (resultSet.next()) {
            ad = new Ad(resultSet.getInt("id"),
                    resultSet.getInt("user_id"),
                    resultSet.getString("title"),
                    resultSet.getString("description")
            );

            ads.add(i, ad);
            i += 1;
        }

        return ads;
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
}
