package Models.DataAccessLayer;

import Models.Ad;

import java.util.List;

/**
 * <p>The <code>Ads</code> interface provides common methods found in adlister applications. Each method can be
 * defined uniquely by the classes that implement the <code>Ads</code> interface.</p>
 *
 * @author David Ryan Alviola
 * @author Zach Gulde
 * @since  3 Febraury 2017
 */
public interface Ads {
    /**
     * @return a List of <code>Ad</code> objects
     */
    List<Ad> all();

    /**
     * @param ad an <code>Ad</code> object
     * @return a long value (generally representing the generated key after inserting the
     *         <code>Ad</code> object into a database.
     */
    Long insert(Ad ad);

    /**
     * @param idNumber a long value representing the ID of the ad being searched for
     * @return a List of <code>Ad</code> objects representing the search results. Theoretically, only one
     * <code>Ad</code> object should be returned with this method, but a List is returned so the same .jsp file can
     * display the results of all the search methods
     */
    List<Ad> searchAdsByID (Long idNumber);

    /**
     * @param title a string value representing the title of the ad being searched for
     * @return a List of <code>Ad</code> objects with similar titles
     */
    List<Ad> searchAdsByTitle (String title);

    List<Ad> searchAdsByUserID (Long userID);
}
