import java.util.Comparator;

/**
 * A class implementing Comparator interface used to sort
 * the AirbnbListing objects by their price
 */
public class PriceSorter implements Comparator<AirbnbListing> {
    @Override
    public int compare(AirbnbListing airbnbListing, AirbnbListing t1) {
        if (airbnbListing.getPrice() == t1.getPrice()) {
            return 0;
        } else if (airbnbListing.getPrice() > t1.getPrice()) {
            return 1;
        } else {
            return 0;
        }
    }
}
