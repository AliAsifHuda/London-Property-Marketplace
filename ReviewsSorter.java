import java.util.Comparator;

/**
 * An interface extending Comparator interface used to sort
 * the AirbnbListing objects by their number of reviews
 * @author David J. Barnes, Michael Kölling, Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (3)
 */
public interface ReviewsSorter extends Comparator<AirbnbListing> {
    @Override
    public int compare(AirbnbListing airbnbListing, AirbnbListing t1);
}
