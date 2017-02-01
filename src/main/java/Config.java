/**
 * Created by David on 2/1/17.
 */
public class Config {
    private static String url = "jdbc:mysql://localhost:3306/adlister_db";
    private static String username = "adlister_admin";
    private static String password = "password";

    public static String getUrl() {
        return url;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
}
