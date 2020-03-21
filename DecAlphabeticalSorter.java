/**
 * (Aux) Sort the list in descending order.
 */
public class DecAlphabeticalSorter implements AlphabeticSorter {

    @Override
    public int compare(AirbnbListing airbnbListing, AirbnbListing t1) {
        return -airbnbListing.getHost_name().compareTo(t1.getHost_name());
    }
}
