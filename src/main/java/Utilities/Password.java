package Utilities;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by David on 2/6/17.
 */
public class Password {
    private static int numberOfRounds = 12;

    public static String hashPassword (String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(numberOfRounds));
    }

    public static boolean checkPassword (String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
    }
}
