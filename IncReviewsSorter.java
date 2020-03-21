/**
 * (Aux) The class to sort list in ascending order
 */
public class IncReviewsSorter implements ReviewsSorter {

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
