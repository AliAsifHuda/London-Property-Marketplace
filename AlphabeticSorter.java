import java.util.Comparator;

/**
 * An interface extending Comparator interface used to sort
 * the AirbnbListing alphabetically
 * @author , Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (3)
 */
public interface AlphabeticSorter extends Comparator<AirbnbListing> {
    @Override
    public int compare(AirbnbListing airbnbListing, AirbnbListing t1);
}
