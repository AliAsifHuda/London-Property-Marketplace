import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;
import java.util.Comparator;

/**
 * Sort the list by number of reviews
 * @author  Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (3)
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
