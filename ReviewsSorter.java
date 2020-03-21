import java.util.Comparator;

/**
 * An interface extending Comparator interface used to sort
 * the AirbnbListing objects by their number of reviews
 */
public interface ReviewsSorter extends Comparator<AirbnbListing> {
    @Override
    public int compare(AirbnbListing airbnbListing, AirbnbListing t1);
}
