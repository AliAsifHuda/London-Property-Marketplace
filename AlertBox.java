import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A new alert box which displays a message.
 */
public class AlertBox {

    /**
     * Display a new alertbox of a specific title, which displays a message and
     * has a close button
     * @param title The title of this alertbox
     * @param message The message displayed by the alertbox
     */
    public static void display(String title, String message) {
        Stage window = new Stage();
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);
        window.centerOnScreen();

        Label dispMessage = new Label(message);
        dispMessage.setAlignment(Pos.CENTER);
        Button closeButton = new Button("Close");
        closeButton.setAlignment(Pos.BOTTOM_RIGHT);
        closeButton.setOnAction( e -> window.close());
        VBox layout = new VBox(40);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(dispMessage, closeButton);

        window.setScene(new Scene(layout, 600, 100));
        window.show();
    }
}
