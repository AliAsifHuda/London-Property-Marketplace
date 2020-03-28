/**
 * (Aux) Sort the list in descending order.
 * @author David J. Barnes, Michael Kölling, Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (3)
 */
public class DecAlphabeticalSorter implements AlphabeticSorter {

    @Override
    public int compare(AirbnbListing airbnbListing, AirbnbListing t1) {
        return -airbnbListing.getHost_name().compareTo(t1.getHost_name());
    }
}
