/**
 * Created by David on 2/1/17.
 */
public class Config {
    private String url = "jdbc:mysql://localhost:3306/adlister_db";
    private String user = "adlister_admin";
    private String password = "password";

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
