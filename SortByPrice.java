import javafx.collections.ObservableList;

import java.util.Collections;

/**
 * Sort the list by the price of properties
 * @author Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (3)
 */
public class SortByPrice implements SortBy {
    @Override
    public ObservableList<AirbnbListing> sortList(ObservableList<AirbnbListing> unsortedList, boolean isAscendingOrder) {
        if (isAscendingOrder) {
            unsortedList.sort((AirbnbListing airbnbListing, AirbnbListing t1)  -> 
                    airbnbListing.getPrice() - t1.getPrice());
        }else {
            unsortedList.sort((AirbnbListing airbnbListing, AirbnbListing t1)  -> 
                    t1.getPrice() - airbnbListing.getPrice());

        }
        return unsortedList;
    }
}
