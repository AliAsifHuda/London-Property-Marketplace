import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;
import java.util.Comparator;

/**
 * Sort the list by number of reviews
 */
public class SortByReviews implements SortBy {

    @Override
    public ObservableList<AirbnbListing> sortList(ObservableList<AirbnbListing> unsortedList, boolean isAscendingOrder) {
        if (isAscendingOrder) {
            Collections.sort(unsortedList, new IncReviewsSorter());
        } else {
            Collections.sort(unsortedList, new DecReviewsSorter());
        }
        return unsortedList;
    }

}