import javafx.collections.ObservableList;

import java.util.Collections;

/**
 * Sort the list by the price of properties
 */
public class SortByPrice implements SortBy {
    @Override
    public ObservableList<AirbnbListing> sortList(ObservableList<AirbnbListing> unsortedList) {
        Collections.sort(unsortedList, new PriceSorter());
        return unsortedList;
    }
}
