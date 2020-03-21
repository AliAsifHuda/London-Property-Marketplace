
/**
 * (Aux) Sort the list in descending order.
 */
public class DecPriceSorter implements PriceSorter {

    @Override
    public int compare(AirbnbListing airbnbListing, AirbnbListing t1) {
        if (airbnbListing.getPrice() == t1.getPrice()) {
            return 0;
        } else if (airbnbListing.getPrice() > t1.getPrice()) {
            return -1;
        } else {
            return 1;
        }
    }
}
