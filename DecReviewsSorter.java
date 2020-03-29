/**
 * (Aux) The class to sort list in descending order
 * @author , Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (3)
 */
public class DecReviewsSorter implements ReviewsSorter {
    @Override
    public int compare(AirbnbListing airbnbListing, AirbnbListing t1) {
        if (airbnbListing.getNumberOfReviews() == t1.getNumberOfReviews()) {
            return 0;
        } else if (airbnbListing.getNumberOfReviews() > t1.getNumberOfReviews()) {
            return -1;
        } else {
            return 1;
        }
    }
}
