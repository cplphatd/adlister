package Models.DataAccessLayer;

/**
 * <p>The <code>DaoFactory</code> class is used to statically call methods on classes that implement the
 * <code>Ads</code> interface. The <code>DaoFactory</code> is generally used within servlets to call methods from
 * classes that implement the <code>Ads</code> interface. The use of the <code>DaoFactory</code> class allows for a
 * single point of change if the class used to generate data changes rather than changing each servlet individually.</p>
 *
 * @author David Ryan Alviola
 * @author Zach Gulde
 * @since 3 February 2017
 */
public class DaoFactory {
    private static Ads adsDao;
    private static Users usersDao;

    /**
     * <p>This method can be called statically and since the object created implements the <code>Ads</code>
     * interface, those methods are available as well. If the class that generated data were change, the new class
     * would be the object that the <code>DaoFactory</code> returns. This class is generally used within servlets.</p>
     *
     * @return an object that implements the <code>Ads</code> interface
     */
    public static Ads getAdsDao() {
        if (adsDao == null) {
            adsDao = new MySQLAdsDao();
        }
        return adsDao;
    }

    public static Users getUsersDao () {
        if (usersDao == null) {
            usersDao = new MySQLUsersDao();
        }
        return usersDao;
    }
}
