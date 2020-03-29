import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * @author  Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (3)
 */
public class PropertyInfo {

    private static AirbnbListing listing;

    /**
     * Display the information about the current property
     * selected in the property displaying table from
     * borough info class in a new window.
     * @param givenListing The current AirbnbListing object whose
     *                 details are to be viewed.
     */
    public static void displayPropertyInfo(AirbnbListing givenListing) {
        listing = givenListing;
        Stage newWindow = new Stage();
        newWindow.setTitle(givenListing.getHost_name() + "'s listing - Property #" + givenListing.getId());
        newWindow.setMinWidth(400);
        newWindow.setMinHeight(400);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(getTopLayout());
        borderPane.setCenter(getCenterLayout());
        newWindow.setScene(new Scene(borderPane, 400, 400));
        newWindow.show();
        // close this window when it is no longer the focused window.
        // (init modality not added intentionally to enhance the user experience (to not annoy the user))
        newWindow.focusedProperty().addListener((e, onHidden, onShown) -> {
            if (!newWindow.isFocused()) {
                Platform.runLater(newWindow::close);
            }
        });
    }

    /**
     * @return The layout AnchorPane to be set on top of window
     */
    private static AnchorPane getTopLayout() {
        AnchorPane topLayout = new AnchorPane();
        Label hostNameLabel = new Label("Host's name: ");
        Label hostName = new Label(listing.getHost_name());
        hostName.setStyle("-fx-font-size: 30;"
                + "-fx-font-weight: bold;"
                + "-fx-font-family: Comic Sans MS;");
        Label priceLabel = new Label("Cost: ");
        Label price = new Label("" + listing.getPrice());
        price.setStyle("-fx-font-size: 30;"
                + "-fx-font-family: Comic Sans MS;"
                + "-fx-font-weight: bold;");
        price.setPadding(new Insets(10, 0,0,0));
        hostName.setPadding(new Insets(10,  0, 0, 0));
        AnchorPane.setLeftAnchor(priceLabel, 10.0);
        AnchorPane.setLeftAnchor(price, 10.0);
        AnchorPane.setRightAnchor(hostNameLabel, 10.0);
        AnchorPane.setRightAnchor(hostName, 10.0);
        topLayout.getChildren().addAll(hostName, hostNameLabel, priceLabel, price);
        topLayout.setPadding(new Insets(0, 0, 30, 0));
        return topLayout;
    }

    /**
     * @return The layout VBox to be set on center of window
     */
    private static VBox getCenterLayout() {
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        Label descriptionLabel = new Label("Description: ");
        descriptionLabel.setFont(new Font("Arial", 18));
        descriptionLabel.setAlignment(Pos.TOP_LEFT);
        Label propertyDescription = new Label(listing.getName());
        propertyDescription.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(descriptionLabel, propertyDescription);
        return vBox;
    }
}
