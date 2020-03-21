import javafx.collections.ObservableList;

import java.util.Collections;

/**
 * Sort the list alphabetically
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
