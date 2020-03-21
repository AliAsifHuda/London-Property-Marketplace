import javafx.collections.ObservableList;

import java.util.Collections;

/**
 * Sort the list by the price of properties
 */
public class SortByPrice implements SortBy {
    @Override
    public ObservableList<AirbnbListing> sortList(ObservableList<AirbnbListing> unsortedList, boolean isAscendingOrder) {
        if (isAscendingOrder) {
            Collections.sort(unsortedList, new IncPriceSorter());
        } else {
            Collections.sort(unsortedList, new DecPriceSorter());
        }
        return unsortedList;
    }
}
