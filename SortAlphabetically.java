import javafx.collections.ObservableList;

import java.util.Collections;

/**
 * Sort the list alphabetically
 */
public class SortAlphabetically implements SortBy {
    @Override
    public ObservableList<AirbnbListing> sortList(ObservableList<AirbnbListing> unsortedList) {
        Collections.sort(unsortedList, new AlphabeticSorter());
        return unsortedList;
    }
}
