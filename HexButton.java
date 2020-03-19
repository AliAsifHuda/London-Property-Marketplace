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

import java.util.HashSet;

/**
 * Write a description of JavaFX class HexButton here.
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
    private String boroughName;

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
    public String getBoroughName() {
        return boroughName;
    }

    /**
     * Create and return a hexagonal button with a given name
     * @param boroughName The name to display on button
     */
    public void createButton(String boroughName) {
        myButton = new Button();
        
        //these if statements set the colour of the buttons
        if (buttonColourSetter().equals("red")) {
            myButton.setStyle("-fx-background-color: #ff0000");
        }
        if (buttonColourSetter().equals("orange")) {
            myButton.setStyle("-fx-background-color: #ffa500");
        }
        if (buttonColourSetter().equals("yellow")) {
            myButton.setStyle("-fx-background-color: #ffff00");
        }
        if (buttonColourSetter().equals("green")) {
            myButton.setStyle("-fx-background-color: #008000");
        }

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
    
    /**
     * @return The colour of button based on the number of properties in the borough.
     */
    private String buttonColourSetter() {
        if (numberOfProperties() >= 0 && numberOfProperties() <= 250) {
            return "red";
        }
        else if (numberOfProperties() >= 260 && numberOfProperties() <= 500) {
            return "orange";
        }
        else if (numberOfProperties() >= 510 && numberOfProperties() <= 750) {
            return "yellow";
        }
        else if (numberOfProperties() >= 760) {
            return "green";
        }
        return "red";
    }
    
    /**
     * @return The number of properties in the borough of this button
     */
    public int numberOfProperties() {
        int count = 0;
        for (AirbnbListing listing : AirbnbDataLoader.getListings()) {
            if (listing.getNeighbourhood().equals(this.boroughName)) {
                count++;
            }
        }
        return count;
    }
}