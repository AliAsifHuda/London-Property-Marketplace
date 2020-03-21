import java.util.Comparator;

/**
 * A class implementing Comparator interface used to sort
 * the AirbnbListing objects in their alphabetical order
 */
public class AlphabeticSorter implements Comparator<AirbnbListing> {
    @Override
    public int compare(AirbnbListing airbnbListing, AirbnbListing t1) {
        return airbnbListing.getHost_name().compareTo(t1.getHost_name());
    }
}
