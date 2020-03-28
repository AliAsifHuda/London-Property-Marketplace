import javafx.collections.ObservableList;

import java.util.Collections;

/**
 * Sort the list alphabetically
 * @author David J. Barnes, Michael Kölling, Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (3)
 */
public class SortAlphabetically implements SortBy {
    @Override
    public ObservableList<AirbnbListing> sortList(ObservableList<AirbnbListing> unsortedList, boolean isAscendingOrder) {
        if (isAscendingOrder) {
            Collections.sort(unsortedList, new IncAlphabeticalSorter());
        } else {
            Collections.sort(unsortedList, new DecAlphabeticalSorter());
        }
        return unsortedList;
    }
}
