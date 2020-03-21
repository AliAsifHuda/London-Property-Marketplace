import java.util.Comparator;

/**
 * A class implementing Comparator interface used to sort
 * the AirbnbListing objects by their number of reviews
 */
public class ReviewsSorter implements Comparator<AirbnbListing> {
    @Override
    public int compare(AirbnbListing airbnbListing, AirbnbListing t1) {
        if (airbnbListing.getNumberOfReviews() == t1.getNumberOfReviews()) {
            return 0;
        } else if (airbnbListing.getNumberOfReviews() > t1.getNumberOfReviews()) {
            return 1;
        } else {
            return -1;
        }
    }
}
