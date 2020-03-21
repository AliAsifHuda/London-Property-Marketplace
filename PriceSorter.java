import java.util.Comparator;

/**
 * An interface extending Comparator interface used to sort
 * the AirbnbListing objects by their price
 */
public interface PriceSorter extends Comparator<AirbnbListing> {
    @Override
    public int compare(AirbnbListing airbnbListing, AirbnbListing t1);
}
