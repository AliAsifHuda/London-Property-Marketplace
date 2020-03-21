import java.util.Comparator;

/**
 * An interface extending Comparator interface used to sort
 * the AirbnbListing alphabetically
 */
public interface AlphabeticSorter extends Comparator<AirbnbListing> {
    @Override
    public int compare(AirbnbListing airbnbListing, AirbnbListing t1);
}
