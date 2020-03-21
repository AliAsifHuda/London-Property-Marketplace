import javafx.collections.ObservableList;

/**
 * This is the interface used to specify different
 * sorting algorithms for the "Sort By" combo box in
 * the BoroughInfo's properties detail output
 */

//note to self: take list from boroughinfo as param and return the
// sorted list from that (take getListingsList() as param)

public interface SortBy {

    /**
     * Takes in an ObservableList and returns a sorted list of it
     * @param unsortedList The list which is to be sorted
     * @return The sorted list (by specific sorting types)
     */
    public ObservableList<AirbnbListing> sortList(ObservableList<AirbnbListing> unsortedList);

}
