import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * This class is for creation of a new window
 * which will display the information about properties
 * in a specific borough when the user presses the button of that
 * respective borough.
 */

public class BoroughInfo {
    private AirbnbDataLoader loader = new AirbnbDataLoader();
    private String boroughName;

    /**
     * Displays a new window showing the properties for listing in a borough
     * @param borough The name of the borough
     */
    public void displayInfo(String borough) {
        boroughName = borough;
        Stage window = new Stage();
        window.setTitle(boroughName);
        TableView<AirbnbListing> table = new TableView<>();

        TableColumn<AirbnbListing, String> hostNameCol = new TableColumn<>("Host Name");
        hostNameCol.setMinWidth(200);
        hostNameCol.setCellValueFactory(new PropertyValueFactory<>("host_name"));

        TableColumn<AirbnbListing, String> priceCol = new TableColumn<>("Price");
        priceCol.setMinWidth(100);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<AirbnbListing, String> numReviewsCol = new TableColumn<>("Reviews (number)");
        numReviewsCol.setMinWidth(100);
        numReviewsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfReviews"));

        TableColumn<AirbnbListing, String> minNightsCol = new TableColumn<>("Minimum Nights");
        minNightsCol.setMinWidth(100);
        minNightsCol.setCellValueFactory(new PropertyValueFactory<>("minimumNights"));

        table.setItems(getListingsList());

        table.getColumns().addAll(hostNameCol, priceCol, numReviewsCol, minNightsCol);

        ComboBox<String> sortByList = new ComboBox<>();
        sortByList.setPromptText("Sort by: ");
        sortByList.getItems().add("Number of Reviews");
        sortByList.getItems().add("Price");
        sortByList.getItems().add("Alphabetical Order");

        BorderPane borderPane = new BorderPane();
        HBox hBox = new HBox();
        hBox.getChildren().add(sortByList);
        hBox.setAlignment(Pos.CENTER);

        borderPane.setCenter(table);
        hBox.setPadding(new Insets(0, 0, 10, 0));
        borderPane.setTop(hBox);

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
}
