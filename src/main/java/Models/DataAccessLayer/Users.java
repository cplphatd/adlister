package Models.DataAccessLayer;

import Models.User;

/**
 * Created by David on 2/4/17.
 */
public interface Users {

    User findByUsername(String username);

    Long insert(User user);
}
