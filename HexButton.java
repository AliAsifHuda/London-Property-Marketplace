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
import java.util.HashMap;

import java.util.HashMap;

/**
 * Write a description of JavaFX class HexButton here.
 *
 * @author David J. Barnes, Michael Kölling, Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (3)
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

        myButton.setStyle(buttonColourSetter()); //sets the colour of every new button made

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
        myButton.setMaxSize(75, 75);
        myButton.setText(boroughName.substring(0, 4).toUpperCase());
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
     * @return The colour in hexcode of button based on the number of properties in the borough.
     */
    private String buttonColourSetter() {
        if (numberOfProperties() >= 0 && numberOfProperties() <= 100) {
            return "-fx-background-color: #ff0000"; //red
        } else if (numberOfProperties() >= 101 && numberOfProperties() <= 200) {
            return "-fx-background-color: #f42900"; //red
        } else if (numberOfProperties() >= 201 && numberOfProperties() <= 300) {
            return "-fx-background-color: #ea4e00"; //red
        } else if (numberOfProperties() >= 301 && numberOfProperties() <= 400) {
            return "-fx-background-color: #df7000"; //red
        } else if (numberOfProperties() >= 401 && numberOfProperties() <= 500) {
            return "-fx-background-color: #d58e00"; //red
        } else if (numberOfProperties() >= 501 && numberOfProperties() <= 750) {
            return "-fx-background-color: #caa800"; //orande
        } else if (numberOfProperties() >= 751 && numberOfProperties() <= 1000) {
            return "-fx-background-color: #c0c000"; //orande
        } else if (numberOfProperties() >= 1001 && numberOfProperties() <= 1250) {
            return "-fx-background-color: #97b500"; //orande
        } else if (numberOfProperties() >= 1251 && numberOfProperties() <= 2000) {
            return "-fx-background-color: #72aa00"; //orande
        } else if (numberOfProperties() >= 2500 && numberOfProperties() <= 3000) {
            return "-fx-background-color: #50a000"; //yellow
        } else if (numberOfProperties() >= 3001 && numberOfProperties() <= 3500) {
            return "-fx-background-color: #329500"; //yellow
        } else if (numberOfProperties() >= 3501 && numberOfProperties() <= 4000) {
            return "-fx-background-color: #178b00"; //yellow
        } else if (numberOfProperties() >= 4500) {
            return "-fx-background-color: #008000"; //green
        }
        return "";
    }

    // private String buttonColourSetter() {
        // int lowerBound = 0;
        // int upperBound = 100;
        // int counter = 0;
        // HashMap<Integer, String> colourHashMap = new HashMap<Integer, String>() 
            // {{
                    // put(0, "#ff0000"); //red
                    // put(1, "#ffa500"); //orange
                    // put(2, "#ffff00"); //yellow
                    // put(3, "#008000"); //green
                    // put(4, "#008000");
                    // put(5, "#ff0000"); //red
                    // put(6, "#ffa500"); //orange
                    // put(7, "#ffff00"); //yellow
                    // put(8, "#008000"); //green
                    // put(9, "#008000");
                    // put(10, "#ff0000"); //red
                    // put(11, "#ffa500"); //orange
                    // put(12, "#ffff00"); //yellow
                    // put(13, "#008000"); //green
                // }};

        // while (!(numberOfProperties() >= lowerBound) && !(numberOfProperties() < upperBound)) {
            // if (upperBound < 500) {
                // lowerBound += 100;
                // upperBound += 100;
                // counter++;
            // }
            // if (upperBound >= 500 && upperBound < 1000) {
                // lowerBound += 200;
                // upperBound += 200;
                // counter++;
            // }
            // if (upperBound >= 1000 && upperBound < 1500) {
                // lowerBound += 250;
                // upperBound += 250;
                // counter++;
            // }
            // if (upperBound >= 1500) {
                // lowerBound += 500;
                // upperBound += 500;
                // counter++;
            // }
            // counter++;
            // return "-fx-background-color: "+colourHashMap.get(counter);
        // }
        // return "-fx-background-color: "+colourHashMap.get(counter);
    // }

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