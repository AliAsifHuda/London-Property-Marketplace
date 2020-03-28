import javafx.collections.ObservableList;

/**
 * This is the interface used to specify different
 * sorting algorithms for the "Sort By" combo box in
 * the BoroughInfo's properties detail output
 * @author David J. Barnes, Michael Kölling, Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (3)
 */

//note to self: take list from boroughinfo as param and return the
// sorted list from that (take getListingsList() as param)

public interface SortBy {

    /**
     * Takes in an ObservableList and returns a sorted list of it
     * @param unsortedList The list which is to be sorted
     * @param isAscendingOrder True if the list is to be ordered in ascending order. False otherwise
     * @return The sorted list (by specific sorting types)
     */
    public ObservableList<AirbnbListing> sortList(ObservableList<AirbnbListing> unsortedList, boolean isAscendingOrder);

}
