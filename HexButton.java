import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.shape.Polygon;

/**
 * Write a description of JavaFX class HexaButton here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HexButton
{
    // We keep track of the count, and label displaying the count:
    private int count = 0;
    private Label myLabel = new Label("0");
    private Button myButton;
    private static String boroughName;

    /**
     * The constructor of our class
     * @param boroughName The name of borough that our button will display
     */
    public HexButton(String boroughName) {
        this.boroughName = boroughName;
        createButton(boroughName);
    }

    /**
     * @return The button object.
     */
    public Button getButton() {
        return myButton;
    }

    /**
     * @return The name of the borough that the button is for.
     */
    public static String getBoroughName() {
        return boroughName;
    }

    /**
     * Create and return a hexagonal button with a given name
     * @param boroughName The name to display on button
     */
    public void createButton(String boroughName) {
        myButton = new Button();
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(
                200.0, 250.0,
                1100.0, 500.0,
                2000.0, 250.0,
                2000.0, -250.0,
                1100.0, -500.0,
                200.0, -250.0
        );
        myButton.setShape(polygon);
        myButton.setMaxSize(50,50);
        myButton.setText(boroughName);
    }

    /**
     * Set the position of the button on the display
     * @param xTran The x coordinates
     * @param yTran The y coordinates
     */
    public void setButtonPosition(double xTran, double yTran) {
        myButton.setTranslateX(xTran);
        myButton.setTranslateY(yTran);
    }

}