package Models.DataAccessLayer;

import Models.User;

/**
 * Created by David on 2/4/17.
 */
public interface Users {

    //Find a user by username
    User findByUsername(String username);

    //Add a new user
    Long insert(User user);

    //Find a user by ID number
    User findUserByIDNumber (long idNumber);
}
