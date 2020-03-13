import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Separator;
import javafx.geometry.Orientation;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;

import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.stage.Popup;
import javafx.scene.input.MouseEvent;
import javafx.scene.effect.DropShadow;

/**
 * Write a description of JavaFX class a here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main extends Application
{
    private Stage window;
    private Scene scene1;
    private int counter;
    private boolean disableFlagA = false;
    private boolean disableFlagB = false;
    private AirbnbDataLoader dataLoader = new AirbnbDataLoader();
    // check if the values in "from" and "to" boxes are correct
    private boolean correctBoxValues = true;
    // A set containing all the buttons in the borough
    private HashSet<HexButton> boroughButtons;

    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param window the primary stage for this application.
     */
    @Override
    public final void start(Stage window) {
        // create the buttons needed for boroughs
        createBoroughButtons();
        // window = window;
        window.setTitle("Property");

        //controls and lines for the top bit
        HBox commonLayout = new HBox(15);
        commonLayout.setStyle("-fx-border-color: black; -fx-border-width: 0px 0px 1px 0px; -fx-padding: 5px;");
        Label fromLabel = new Label("From");
        Label backLabel = new Label("To");
        ComboBox<Integer> fromComboBox1 = new ComboBox<>();
        fromComboBox1.setPromptText("min"); 
        addComboBoxRanges(fromComboBox1, dataLoader.getMinPriceListing().getPrice(), dataLoader.getMaxPriceListing().getPrice());
        ComboBox<Integer> toComboBox1 = new ComboBox<>();
        toComboBox1.setPromptText("max"); 
        addComboBoxRanges(toComboBox1, dataLoader.getMinPriceListing().getPrice(), dataLoader.getMaxPriceListing().getPrice());
        AnchorPane fromToButtonPane = new AnchorPane();
        commonLayout.setHgrow(fromToButtonPane , Priority.ALWAYS); 
        commonLayout.getChildren().add(fromToButtonPane);
        //add all control items to layout style
        commonLayout.getChildren().addAll(fromLabel, fromComboBox1, backLabel, toComboBox1);

        //text for the middle
        Text text1 = new Text("Welcome to London Property Marketplace ");
        StackPane textPane = new StackPane();
        textPane.getChildren().addAll(text1);

        //controls and lines for the bottom bit
        HBox bottomBox = new HBox(15);
        bottomBox.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px; -fx-padding: 5px ;");
        Button backButton = new Button("Back");
        Button buttonForward = new Button("Forward");
        AnchorPane backButtonPane = new AnchorPane();
        AnchorPane forwardButtonPane = new AnchorPane();
        HBox.setHgrow(backButtonPane , Priority.ALWAYS);//Make pane grow horizontally
        bottomBox.getChildren().add(backButtonPane );
        bottomBox.getChildren().add(forwardButtonPane );
        backButtonPane.getChildren().add(backButton);
        forwardButtonPane.getChildren().add(buttonForward);

        //make a borderPane layout and add layouts to top and bottom
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(commonLayout);        
        BorderPane.setMargin(commonLayout, new Insets(20));
        borderPane.setCenter(textPane);        
        borderPane.setBottom(bottomBox);
        BorderPane.setMargin(bottomBox, new Insets(10));
        borderPane.getStylesheets().add("abc.css");

        // image panel
        Image image = new Image("boroughs.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(700);
        imageView.setFitWidth(780);

        //create new hex shaped buttons
        GridPane gridPane = new GridPane();
        /*HexaButton hexButton1 = new HexaButton();
        HexaButton hexButton2 = new HexaButton();
        HexaButton hexButton3 = new HexaButton();
        HexaButton hexButton4 = new HexaButton();
        HexaButton hexButton5 = new HexaButton();*/

        //add buttons to stackpane 
        StackPane stackPane = new StackPane();
        /*stackPane.getChildren().addAll(imageView,
            hexButton1.getButton("Tower Hill", 72,0), 
            hexButton2.getButton("Lambeth", -40,190),
            hexButton3.getButton("Westminster", -40, 0),
            hexButton4.getButton("Hackney", 140,-100),
            hexButton5.getButton("Richmond", -300,190));*/

        // back and forward button are initially disabled
        backButton.setDisable(true);
        buttonForward.setDisable(true);

        buttonForward.setOnAction( e -> {
                if (counter==0) {
                    analyzeBoxValues(fromComboBox1, toComboBox1);
                    if (correctBoxValues) {
                        borderPane.setCenter(stackPane);
                        counter++;
                    }
                } else if  (counter==1) {
                    borderPane.setCenter(textPane);
                    //for testing
                    //borderPane.setLeft(hex1.getButton());
                    counter=0;
                }
            });

        backButton.setOnAction( e -> {
                if (counter==0) {
                    analyzeBoxValues(fromComboBox1, toComboBox1);
                    if (correctBoxValues) {
                        borderPane.setCenter(imageView);
                        counter++;
                    }
                } else if  (counter==1) {
                    borderPane.setCenter(textPane);
                    counter=0;
                }
            });

        fromComboBox1.setOnAction( e -> {
                text1.setText("Welcome to London Property Marketplace \n\n"  +
                    "Minimum price: " + displayPrice(fromComboBox1) + "\n\n Maximum price: " + displayPrice(toComboBox1));
                disableFlagA = true; //if both combobox have input back and forward button will be enabled
                if (disableFlagA && disableFlagB) {
                    backButton.setDisable(false);
                    buttonForward.setDisable(false);
                }
            });

        toComboBox1.setOnAction( e-> {

                text1.setText("Welcome to London Property Marketplace \n\n" +
                    "Minimum price: " + displayPrice(fromComboBox1) + "\n\n Maximum price: " + displayPrice(toComboBox1));
                disableFlagB = true; //if both combobox have input back and forward button will be enabled
                if (disableFlagA && disableFlagB) {
                    backButton.setDisable(false);
                    buttonForward.setDisable(false);
                }
            });

        //test code for popup    
        StackPane popupPane = new StackPane();
        popupPane.setPrefSize(200, 20);
        popupPane.setStyle("-fx-background-color: pink;");

        Label popupLabel = new Label("This is a label");

        popupPane.getChildren().add(popupLabel);

        Popup popup = new Popup();
        popup.getContent().add(popupPane);

        stackPane.hoverProperty().addListener((obs, oldVal, newValue) -> {
        if (newValue) {
        Bounds bnds = stackPane.localToScreen(stackPane.getLayoutBounds());
        double x = bnds.getMinX() - (popupPane.getWidth() / 2) + (stackPane.getWidth() / 2);
        double y = bnds.getMinY() - popupPane.getHeight();
        popup.show(stackPane, x, y);
        } else {
        popup.hide();
        }
        });
        
        //end of test code    

        // Show the Stage (window)
        window.setScene(new Scene(borderPane,500, 500));
        window.show();
    }

    /**
     * Analyze the values selected by the user in the ComboBoxes
     * @param box1 The first combo box
     * @param box2 The second combo box
     */
    private void analyzeBoxValues(ComboBox<Integer> box1, ComboBox<Integer> box2) {
        if (box1.getValue().equals(box2.getValue())) {
            correctBoxValues = false;
            AlertBox.display("Error", "Please select different values for the \"From\" " +
                "and \"To\" fields");
        } else if (box1.getValue() > box2.getValue()) {
            correctBoxValues = false;
            AlertBox.display("Error", "Please select a smaller value in the " +
                "\"From\" field than that in the \"To\" field");
        } else {
            correctBoxValues = true;
        }
    }

    /**
     * Add price ranges to the Combo Boxes (in increments of 500)
     * @param box The combobox to add values in
     * @param min The minimum amount for the values
     * @param max The maximum value
     */
    private void addComboBoxRanges(ComboBox<Integer> box, int min, int max) {
        int i = min;
        while (i <= max) {
            box.getItems().add(i);
            if ((i + 500) % 500 == 0) {
                i += 500;
            }
            else {
                i += (500 - i);
            }
        }
    }

    /**
     * Create hexagonal buttons for each borough to be
     * added to the second panel which are then added to the boroughButtons array
     */
    private void createBoroughButtons() {
        for (String boroughName : dataLoader.getBoroughs()) {
            boroughButtons.add(new HexButton(boroughName));
        }
    }

    /**
     * Display the price user has selected on first screen.
     * @param box The combo box to get the value from.
     * @return The String displaying message.
     */
    private String displayPrice(ComboBox<Integer> box) {
        if (box.getValue() != null) {
            return "" + box.getValue();
        }
        else {
            return "<Please select a value>";
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}