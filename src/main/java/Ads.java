import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();

    // insert a new ad and return the new ad's id
    Long insert(Ad ad);

    //Find single ad by ID
    List<Ad> searchAdsByID (Long idNumber);

    //Find ads by title
    List<Ad> searchAdsByTitle (String title);
}
