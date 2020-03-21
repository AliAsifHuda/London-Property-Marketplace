import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//import java.awt.event.ActionEvent;
import javafx.event.ActionEvent;
import java.util.HashSet;


/**
 * This class is for creation of a new window
 * which will display the information about properties
 * in a specific borough when the user presses the button of that
 * respective borough.
 */

public class BoroughInfo {
    private AirbnbDataLoader loader = new AirbnbDataLoader();
    private String boroughName;
    private TableView<AirbnbListing> table;
    ComboBox<String> sortByList;
    ComboBox<String> sortingOrderList;

    /**
     * Displays a new window showing the properties for listing in a borough
     * @param borough The name of the borough
     */
    public void displayInfo(String borough) {
        boroughName = borough;
        Stage window = new Stage();
        window.setTitle(boroughName);
        table = new TableView<>();

        TableColumn<AirbnbListing, String> hostNameCol = new TableColumn<>("Host Name");
        hostNameCol.setMinWidth(200);
        hostNameCol.setCellValueFactory(new PropertyValueFactory<>("host_name"));

        TableColumn<AirbnbListing, String> priceCol = new TableColumn<>("Price");
        priceCol.setMinWidth(100);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<AirbnbListing, String> numReviewsCol = new TableColumn<>("Reviews (number)");
        numReviewsCol.setMinWidth(150);
        numReviewsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfReviews"));

        TableColumn<AirbnbListing, String> minNightsCol = new TableColumn<>("Minimum Nights");
        minNightsCol.setMinWidth(150);
        minNightsCol.setCellValueFactory(new PropertyValueFactory<>("minimumNights"));

        table.setItems(getListingsList());

        table.getColumns().addAll(hostNameCol, priceCol, numReviewsCol, minNightsCol);

        initialiseSortingOrderList();
        initialiseSortByList();
        sortByList.setOnAction(this::sortingListsAction);
        sortingOrderList.setOnAction(this::sortingListsAction);

        BorderPane borderPane = new BorderPane();
        AnchorPane sortButtonPane = new AnchorPane();
        sortButtonPane.getChildren().addAll(sortByList, sortingOrderList);
        AnchorPane.setLeftAnchor(sortByList, 3.0);
        AnchorPane.setRightAnchor(sortingOrderList, 3.0);
        borderPane.setCenter(table);
        sortButtonPane.setPadding(new Insets(0, 0, 10, 0));
        borderPane.setTop(sortButtonPane);

        window.setScene(new Scene(borderPane, 600, 600));
        window.show();
    }

    /**
     * Adds only the listings with a specified borough to the observable list.
     * @return The Observable list containing all objects in that borough.
     */
    private ObservableList<AirbnbListing> getListingsList() {
        ObservableList<AirbnbListing> properties = FXCollections.observableArrayList();
        for (AirbnbListing listing : AirbnbDataLoader.getListings()) {
            if (listing.getNeighbourhood().equals(boroughName)) {
                properties.add(listing);
            }
        }
        return properties;
    }
    
    /**
     * @return The number of properties in the borough
     */
    public String numberOfProperties() {
        int count = 0;
        for (AirbnbListing listing : AirbnbDataLoader.getListings()) {
            if (listing.getNeighbourhood().equals(this.boroughName)) {
                count++;
            }
        }
        return Integer.toString(count);
    }

    /**
     * The Action listener for the two sort by lists in our panel
     */
    private void sortingListsAction(ActionEvent e) {
        SortBy sort = null;
        if (sortByList.getValue().equals("Number of Reviews")) {
            sort = new SortByReviews();
        } else if (sortByList.getValue().equals("Price")) {
            sort = new SortByPrice();
        } else if (sortByList.getValue().equals("Alphabetical Order")) {
            sort = new SortAlphabetically();
        }

        if (sortingOrderList.getValue().equals("Ascending")) {
            table.setItems(sort.sortList(getListingsList(), true));
        } else {
            table.setItems(sort.sortList(getListingsList(), false));
        }
    }

    /**
     * Initialise and set values of our sortByList
     */
    private void initialiseSortByList() {
        sortByList = new ComboBox<>();
        sortByList.setPromptText("Sort by: ");
        sortByList.getItems().add("Number of Reviews");
        sortByList.getItems().add("Price");
        sortByList.getItems().add("Alphabetical Order");
    }

    /**
     * Initialise and set values of our sortingOrderList
     */
    private void initialiseSortingOrderList() {
        sortingOrderList = new ComboBox<>();
        sortingOrderList.getItems().addAll("Ascending", "Descending");
        // first option (ascending) selected by default
        sortingOrderList.getSelectionModel().selectFirst();
    }
}
