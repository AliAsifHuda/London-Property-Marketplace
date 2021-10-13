import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.text.ParseException;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;

/**
 * Write a description of JavaFX class a here.
 *
 * @author Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (3)
 */
public class Main extends Application 
{
    private Stage window;
    private SecondPane secondPane =  new SecondPane();
    private final ThirdPane thirdPane = new ThirdPane();
    private final FourthPane fourthPane = new FourthPane();
    private final BorderPane borderPane = new BorderPane();
    private StackPane textPane = new StackPane();
    private ComboBox<Integer> fromComboBox = new ComboBox<>();
    private ComboBox<Integer> toComboBox = new ComboBox<>();
    private Button backButton = new Button("Back");
    private Button forwardButton = new Button("Forward");
    //private ToggleButton darkModeButton = new ToggleButton("Dark Mode");
    private Text text1 = new Text("Welcome to London Property Marketplace ");
    private boolean disableFlagA = false;
    private boolean disableFlagB = false;
    // check if the values in "from" and "to" boxes are correct
    private boolean correctBoxValues = true;
    //store the value selected in the fromComboBox
    private static int minRange;
    //store the value selected in the toComboBox
    private static int maxRange;
    //HBox hbox = new HBox(50);
    private int fsceneCounter = 1;
    private int bsceneCounter = 1;
    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param primaryStage the primary stage for this application.
     */
    @Override
    public final void start(Stage primaryStage) throws IOException, ParseException {
        AirbnbDataLoader dataLoader = new AirbnbDataLoader();
        dataLoader.load();

        window = primaryStage;
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
        HBox.setHgrow(fromToButtonPane , Priority.ALWAYS);
        commonLayout.getChildren().add(fromToButtonPane);
        commonLayout.getChildren().addAll(fromLabel, fromComboBox, backLabel, toComboBox);

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
        textPane();
        borderPane.setCenter(textPane);        
        borderPane.setBottom(bottomBox);
        BorderPane.setMargin(bottomBox, new Insets(10));
        borderPane.getStyleClass().add("root");        
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStylesheets().add("myCss.css");
        scrollPane.setContent(borderPane);
        scrollPane.fitToWidthProperty().set(true);
        scrollPane.fitToHeightProperty().set(true);
        scrollPane.pannableProperty().set(true);
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        backButton.setStyle("-fx-padding : 5 175 5 175;");
        forwardButton.setStyle("-fx-padding : 5 175 5 175;");

        // back and forward button are initially disabled
        backButton.setDisable(true);
        forwardButton.setDisable(true);

        forwardButton.setOnAction(this::forwardAction);
        backButton.setOnAction(this::backAction);

        //ActionEvent for the combo boxes
        fromComboBox.setOnAction(this::fromAction);
        toComboBox.setOnAction(this::toAction);

        //lines at top and bottom
        bottomBox.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px; -fx-padding: 5px ;");
        commonLayout.setStyle("-fx-border-color: black; -fx-border-width: 0px 0px 1px 0px; -fx-padding: 5px ;");   

        secondPane.imagePane();
        thirdPane.statisticsPane();
        fourthPane.chartGraph();

        // Show the Stage (window)
        window.setScene(new Scene(scrollPane, 900, 850));
        window.setMinHeight(850);
        window.setMinWidth(900);
        window.show();
    }

    private StackPane textPane()
    {
        //text for the middle
        text1.setFont(Font.font("calibri", FontWeight.BOLD, FontPosture.ITALIC, 25));
        text1.setFill(Color.BLACK);
        text1.setStrokeWidth(0.8);
        text1.setStroke(Color.WHITE);
        textPane.getChildren().addAll(text1);
        return this.textPane;
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
        minRange = fromComboBox.getValue();
        disableFlagA = true; //if both combobox have input back and forward button will be enabled
        enableButton();
    }

    /**
     * Action of the toComboBox.
     * @param event The ActionEvent
     */
    private void toAction(ActionEvent event)
    {
        text1.setText("Welcome to London Property Marketplace \n\n" +
            "Minimum price: " + displayPrice(fromComboBox) + "\n\nMaximum price: " + displayPrice(toComboBox));
        maxRange = toComboBox.getValue();
        disableFlagB = true; //if both combobox have input back and forward button will be enabled
        enableButton();
    }

    /**
     * Enable the buttons after a valid price range is entered and play some animation 
     */
    public void enableButton()
    {
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
        try { 
            if (fsceneCounter == 1){//borderPane.getCenter().equals(textPane)) {
                analyzeBoxValues(fromComboBox, toComboBox);
                if (correctBoxValues) {
                    borderPane.setCenter(secondPane.imagePane());
                    bsceneCounter = 2;
                    fsceneCounter = 2;
                }
            } else if  (fsceneCounter == 2){//(borderPane.getCenter().equals(secondPane.imagePane())) {
                borderPane.setCenter(thirdPane.statisticsPane());
                bsceneCounter = 3;
                fsceneCounter = 3;
            } else if  (fsceneCounter == 3){//(borderPane.getCenter().equals(thirdPane.statisticsPane()))
                borderPane.setCenter(fourthPane.chartGraph());
                bsceneCounter = 4;
                fsceneCounter = 4;
            } else if  (fsceneCounter == 4){//(borderPane.getCenter().equals(fourthPane.chartGraph())) {
                borderPane.setCenter(textPane);
                bsceneCounter = 1;
                fsceneCounter = 1;
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Action of back button
     * @param event The ActionEvent
     */
    private void backAction(ActionEvent event) 
    {
        try{
            if (bsceneCounter == 1){//borderPane.getCenter().equals(textPane)) {
                analyzeBoxValues(fromComboBox, toComboBox);
                if (correctBoxValues) {
                    borderPane.setCenter(fourthPane.chartGraph());
                    bsceneCounter = 4;
                    fsceneCounter = 4;
                }
            }
            else if (bsceneCounter == 2){//borderPane.getCenter().equals(secondPane.imagePane())) {
                borderPane.setCenter(textPane);
                bsceneCounter = 1;
                fsceneCounter = 1;
            } 
            else if (bsceneCounter == 3){ // (borderPane.getCenter().equals(thirdPane.statisticsPane())){
                borderPane.setCenter(secondPane.imagePane());
                bsceneCounter = 2;
                fsceneCounter = 2;
            }else if  (bsceneCounter == 4){//borderPane.getCenter().equals(fourthPane.chartGraph())) {
                borderPane.setCenter(thirdPane.statisticsPane());
                bsceneCounter = 3;
                fsceneCounter = 3;
            }
        }catch (Exception e){

        }
    }

    /**
     * @return The value selected in fromComboBox
     */
    public static int getMinRange() {
        return minRange;
    }

    /**
     * @return The value selected in toComboBox
     */
    public static int getMaxRange() {
        return maxRange;
    }

    public static void main(String[] args) {
        launch(args);
    }
}