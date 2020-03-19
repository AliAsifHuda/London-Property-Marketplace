import javafx.application.Application;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.text.*;

import java.util.HashSet;

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
    private BorderPane borderPane = new BorderPane();
    private StackPane textPane = new StackPane();
    private StackPane stackPane = new StackPane();  
    private ComboBox<Integer> fromComboBox = new ComboBox<>();
    private ComboBox<Integer> toComboBox = new ComboBox<>();
    private Button backButton = new Button("Back");
    private Button forwardButton = new Button("Forward");
    private Text text1 = new Text("Welcome to London Property Marketplace ");
    private int counter;
    private boolean disableFlagA = false;
    private boolean disableFlagB = false;
    private AirbnbDataLoader dataLoader = new AirbnbDataLoader();
    // check if the values in "from" and "to" boxes are correct
    private boolean correctBoxValues = true;
    // A set containing all the buttons in the borough
    private HashSet<HexButton> boroughButtons = new HashSet<>();

    private BoroughInfo boroughInfo = new BoroughInfo();

    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param primaryStage the primary stage for this application.
     */
    @Override
    public final void start(Stage primaryStage) {
        dataLoader.load();
        window = primaryStage;
        // create the buttons needed for boroughs
        window.setTitle("Property");

        //controls and lines for the top bit
        HBox commonLayout = new HBox(15);
        Label fromLabel = new Label("From");
        Label backLabel = new Label("To");
        fromComboBox.setPromptText("min"); 
        addComboBoxRanges(fromComboBox, dataLoader.getMinPriceListing().getPrice(), dataLoader.getMaxPriceListing().getPrice());
        toComboBox.setPromptText("max"); 
        addComboBoxRanges(toComboBox, dataLoader.getMinPriceListing().getPrice(), dataLoader.getMaxPriceListing().getPrice());
        AnchorPane fromToButtonPane = new AnchorPane();//AnchorPane to align the buttons
        commonLayout.setHgrow(fromToButtonPane , Priority.ALWAYS); 
        commonLayout.getChildren().add(fromToButtonPane);
        commonLayout.getChildren().addAll(fromLabel, fromComboBox, backLabel, toComboBox);

        //text for the middle
        textPane.getChildren().addAll(text1);

        //controls and lines for the bottom bit
        HBox bottomBox = new HBox(15);
        AnchorPane backButtonPane = new AnchorPane();//AnchorPane to align the buttons
        AnchorPane forwardButtonPane = new AnchorPane();//AnchorPane to align the buttons
        HBox.setHgrow(backButtonPane , Priority.ALWAYS);//Make pane grow horizontally
        bottomBox.getChildren().add(backButtonPane );
        bottomBox.getChildren().add(forwardButtonPane );
        backButtonPane.getChildren().add(backButton);
        forwardButtonPane.getChildren().add(forwardButton);

        //make a borderPane layout and add layouts to top and bottom
        borderPane.setTop(commonLayout);        
        BorderPane.setMargin(commonLayout, new Insets(20));
        borderPane.setCenter(textPane);        
        borderPane.setBottom(bottomBox);
        BorderPane.setMargin(bottomBox, new Insets(10));
        borderPane.getStylesheets().add("abc.css");

        // back and forward button are initially disabled
        backButton.setDisable(true);
        forwardButton.setDisable(true);

        forwardButton.setOnAction(this::forwardAction);
        backButton.setOnAction(this::backAction);;

        //ActionEvent for the combo boxes
        fromComboBox.setOnAction(this::fromAction);
        toComboBox.setOnAction(this::toAction);

        //lines at top and bottom
        bottomBox.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px; -fx-padding: 5px ;");
        commonLayout.setStyle("-fx-border-color: black; -fx-border-width: 0px 0px 1px 0px; -fx-padding: 5px ;");    

        // Show the Stage (window)
        window.setScene(new Scene(borderPane,500, 500));
        window.show();
    }

    /**
     * Create and display the map and buttons on the second screen.
     * @return The StackPane displaying the map and buttons.
     */
    private StackPane imagePane()
    {
        //keeps track of the index of a button (which button it is)
        int index = 0;
        //initial starting position values of buttons
        int a = -63 , b = -140 , c = -166 , d = -140 , e = -115 , f = -89;
        for (String boroughName : dataLoader.getBoroughs()) {
            HexButton hexButton = new HexButton(boroughName);
            boroughButtons.add(hexButton);
            if (index == 0) {
                //gap between each button
                hexButton.setButtonPosition(15, -129);
            } else if (index <= 3) {
                hexButton.setButtonPosition(a, -86);
                a = a + 52;  
            } else if (index >= 4 && index <= 10) {
                hexButton.setButtonPosition(b, -43);
                b = b + 52;
            } else if (index >= 10 && index <= 17) {
                hexButton.setButtonPosition(c, 0);
                c = c + 52;
            } else if (index >= 17 && index <= 23) {
                hexButton.setButtonPosition(d, 43);
                d = d + 52;
            } else if (index >= 24 && index <= 28) {
                hexButton.setButtonPosition(e, 86);
                e = e + 52;
            } else if (index >= 28 && index <= 34) {
                hexButton.setButtonPosition(f, 129);
                f = f + 52;
            }
            index++;
            // add the buttons to the pane which has a map
            stackPane.getChildren().addAll(hexButton.getButton());
            hexButton.getButton().setOnAction( ex -> {
                boroughInfo.displayInfo(hexButton.getBoroughName());
            });
        }

        //test code for popup
        // StackPane popupPane = new StackPane();
        // popupPane.setPrefSize(200, 20);
        // popupPane.setStyle("-fx-background-color: pink;");

        // Label popupLabel = new Label("This is a label");
        // popupPane.getChildren().add(popupLabel);

        // Popup popup = new Popup();
        // popup.getContent().add(popupPane);

        // stackPane.hoverProperty().addListener((obs, oldVal, newValue) -> {
        // if (newValue) {
        // Bounds bnds = stackPane.localToScreen(stackPane.getLayoutBounds());
        // double x = bnds.getMinX() - (popupPane.getWidth() / 2) + (stackPane.getWidth() / 2);
        // double y = bnds.getMinY() - popupPane.getHeight();
        // popup.show(stackPane, x, y);
        // } else {
        // popup.hide();
        // }
        // });
        //end of test code

        return stackPane;
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
    // private Button createBoroughButtons() {
    // for (String boroughName : dataLoader.getBoroughs()) {
    // HexButton hexButton = new HexButton(boroughName);
    // boroughButtons.add(hexButton);
    // System.out.println(boroughName);
    // System.out.println(dataLoader.getBoroughs());
    // return hexButton.getButton();
    // }
    // return null;
    // }

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

    /**
     * Action of the fromComboBox.
     * @param event The ActionEvent
     */
    private void fromAction(ActionEvent event)
    {
        text1.setText("Welcome to London Property Marketplace \n\n"  +
            "Minimum price: " + displayPrice(fromComboBox) + "\n\nMaximum price: " + displayPrice(toComboBox));
        disableFlagA = true; //if both combobox have input back and forward button will be enabled
        if (disableFlagA && disableFlagB) {
            backButton.setDisable(false);
            forwardButton.setDisable(false);
        }
    }

    /**
     * Action of the toComboBox.
     * @param event The ActionEvent
     */
    private void toAction(ActionEvent event)
    {
        text1.setText("Welcome to London Property Marketplace \n\n" +
            "Minimum price: " + displayPrice(fromComboBox) + "\n\nMaximum price: " + displayPrice(toComboBox));
        disableFlagB = true; //if both combobox have input back and forward button will be enabled
        if (disableFlagA && disableFlagB) {
            backButton.setDisable(false);
            forwardButton.setDisable(false);
        }
    }

    /**
     * Action of the forward button.
     * @param event The ActionEvent
     */
    private void forwardAction(ActionEvent event)
    {
        if (counter==0) {
            analyzeBoxValues(fromComboBox, toComboBox);
            if (correctBoxValues) {
                borderPane.setCenter(imagePane());
                counter++;
            }
        } else if  (counter==1) {
            borderPane.setCenter(textPane);
            counter=0;
        }
    }

    /**
     * Action of back button
     * @param event The ActionEvent
     */
    private void backAction(ActionEvent event) {
        if (counter==0) {
            analyzeBoxValues(fromComboBox, toComboBox);
            if (correctBoxValues) {
                borderPane.setCenter(imagePane());
                counter++;
            }
        } else if  (counter==1) {
            borderPane.setCenter(textPane);
            counter=0;
        }
    }

    /**
     * Display the information about a borough when a borough button
     * is pressed on the image panel (panel 2).
     */
    private void boroughButtonAction() {
        for (HexButton hexButton : boroughButtons) {
            hexButton.getButton().setOnAction( e -> {
                boroughInfo.displayInfo(hexButton.getBoroughName());
            });
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}