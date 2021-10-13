import javafx.collections.ObservableList;

import java.util.Collections;

/**
 * Sort the list alphabetically
 * @author Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (3)
 */
public class SortAlphabetically implements SortBy {
    @Override
    public ObservableList<AirbnbListing> sortList(ObservableList<AirbnbListing> unsortedList, boolean isAscendingOrder) {
        // if (isAscendingOrder) {
            // Collections.sort(unsortedList, new IncAlphabeticalSorter());
        // } else {
            // Collections.sort(unsortedList, new DecAlphabeticalSorter());
        // }
        if (isAscendingOrder) {
            unsortedList.sort((AirbnbListing airbnbListing, AirbnbListing t1)  -> 
                    airbnbListing.getHost_name().compareTo(t1.getHost_name()));
        }else {
            unsortedList.sort((AirbnbListing airbnbListing, AirbnbListing t1)  -> 
                    t1.getHost_name().compareTo(airbnbListing.getHost_name()));

        }
        return unsortedList;
    }
}
