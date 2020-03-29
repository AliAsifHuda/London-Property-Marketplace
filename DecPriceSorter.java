
/**
 * (Aux) Sort the list in descending order.
 * @author Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (3)
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
