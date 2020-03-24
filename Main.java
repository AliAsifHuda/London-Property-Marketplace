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
import javafx.scene.control.Tooltip;
import javafx.scene.control.Button;
import javafx.geometry.Orientation;
import java.util.HashSet; 
import javafx.scene.paint.Color;
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
    private SplitPane splitPane1 = new SplitPane();
    private ComboBox<Integer> fromComboBox = new ComboBox<>();
    private ComboBox<Integer> toComboBox = new ComboBox<>();
    private Button backButton = new Button("Back");
    private Button forwardButton = new Button("Forward");
    private Text text1 = new Text("Welcome to London Property Marketplace ");
    private int counter;
    private int caseCounter1 = 1;
    private int caseCounter2 = 1;
    private int caseCounter3 = 1;
    private int caseCounter4 = 1;
    private boolean disableFlagA = false;
    private boolean disableFlagB = false;
    private AirbnbDataLoader dataLoader = new AirbnbDataLoader();
    private BoroughProfileLoader boroughProfileLoader = new BoroughProfileLoader();
    private Label stat1 = new Label();
    private Label stat2 = new Label();
    private Label stat3 = new Label();
    private Label stat4 = new Label();
     
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
        text1.setFont(Font.font("calibri", FontWeight.NORMAL, FontPosture.ITALIC, 25));
        text1.setFill(Color.BLACK);
        text1.setStrokeWidth(0.8);
        text1.setStroke(Color.WHITE);
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
        backButton.setOnAction(this::backAction);

        //ActionEvent for the combo boxes
        fromComboBox.setOnAction(this::fromAction);
        toComboBox.setOnAction(this::toAction);

        //lines at top and bottom
        bottomBox.setStyle("-fx-border-color: white; -fx-border-width: 1px 0px 0px 0px; -fx-padding: 5px ;");
        commonLayout.setStyle("-fx-border-color: white; -fx-border-width: 0px 0px 1px 0px; -fx-padding: 5px ;");    

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
            HexButton hexButtonHover = new HexButton(boroughName); //create a new button to display no of properties 
            hexButton.getButton().setTooltip(new Tooltip(Integer.toString(hexButtonHover.numberOfProperties()))); //adds mouse hover popup
            // add the buttons to the pane which has a map
            stackPane.getChildren().addAll(hexButton.getButton());
            hexButton.getButton().setOnAction( ex -> {
                    boroughInfo.displayInfo(hexButton.getBoroughName());
                });
        }
        return stackPane;
    }

    /**
     * A Pane for showing Statistics.
     * @return The StackPane displaying Statistics.
     */
    private HBox statisticsPane(){
        HBox hbox = new HBox(50);
        hbox.setTranslateX(0);
        hbox.setTranslateY(-28);
        hbox.setMinSize(440, 355);
        hbox.setMaxSize(440, 355);

        GridPane gridPane = new GridPane();
        //splitPane1.setOrientation(Orientation.VERTICAL);
        gridPane.setMinSize(440, 355);
        gridPane.setMaxSize(440, 355);
        gridPane.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        gridPane.setVgap(10); //vertical gap in pixels
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        final Button stat1b = new Button("◀");
        final Button stat2b = new Button("◀");
        final Button stat3b = new Button("◀");
        final Button stat4b = new Button("◀");

        final Button stat1f = new Button("▶");
        final Button stat2f = new Button("▶");
        final Button stat3f = new Button("▶");
        final Button stat4f = new Button("▶");

        stat1.setText("\n\n  number of reviews per property.\n\n" +
            "\t\t\t" + dataLoader.getNumberOfReviews()/dataLoader.getListings().size());
        stat2.setText("\n\n Total number of available properties.\n\n" +
                "\t\t\t" + dataLoader.getAvailability());
        stat3.setText("\n\n  Total number of homes available.\n\n" +
                "\t\t\t" +  dataLoader.getHome());
        stat4.setText("\n This is The most expensive borough");

        gridPane.add(stat1, 1, 0, 1, 1);
        gridPane.add(stat1b, 0, 0, 1, 1);
        gridPane.add(stat1f, 2, 0, 1, 1);
        gridPane.add(stat2, 1, 2, 1, 1);
        gridPane.add(stat2b, 0, 2, 1, 1);
        gridPane.add(stat2f, 2, 2, 1, 1);
        gridPane.add(stat3, 1, 4, 1, 1);
        gridPane.add(stat3b, 0, 4, 1, 1);
        gridPane.add(stat3f, 2, 4, 1, 1);
        gridPane.add(stat4, 1, 6, 1, 1);
        gridPane.add(stat4b, 0, 6, 1, 1);
        gridPane.add(stat4f, 2, 6, 1, 1);
        hbox.getChildren().add(gridPane);
        hbox.setMargin(gridPane, new Insets(10, 10, 10, 80));

        stat1b.setOnAction(this::stat1Action);
        stat2b.setOnAction(this::stat2Action);
        stat3b.setOnAction(this::stat3Action);
        stat4b.setOnAction(this::stat4Action);

        stat1f.setOnAction(this::stat1Action);
        stat2f.setOnAction(this::stat2Action);
        stat3f.setOnAction(this::stat3Action);
        stat4f.setOnAction(this::stat4Action);
        return hbox;
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
        } else if  (counter == 1) {
            borderPane.setCenter(statisticsPane());
            counter++;
        } else if  (counter == 2) {
            borderPane.setCenter(textPane);
            counter = 0;
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
                borderPane.setCenter(statisticsPane());
                counter++;
            }
        }
        else if  (counter==1) {
            borderPane.setCenter(imagePane());
            counter++;
        } 
        else if  (counter==2) {
            borderPane.setCenter(textPane);
            counter=0;
        }
    }

    /**
     * Action of back  and forward button of first stat
     * @param event The ActionEvent
     */
    private void stat1Action(ActionEvent event) {
        switch(caseCounter1)
        {
            case (0): 
            stat1.setText("\n  number of reviews per property.\n" +
            "\t\t\t" + dataLoader.getNumberOfReviews()/dataLoader.getListings().size());
            break;
            case (1): 
            stat1.setText("\n borough with highest transport acessibility \n" +
            "\t\t\t" + boroughProfileLoader.getMaxTransportAcessibility());
            caseCounter1 = -1;
            break;
        }
        caseCounter1++;
    }

    /**
     * Action of back  and forward button of second stat
     * @param event The ActionEvent
     */
    private void stat2Action(ActionEvent event) {
        switch(caseCounter2)
        {
            case (0): 
            stat2.setText("\n Total number of available properties.\n" +
                "\t\t\t" + dataLoader.getAvailability());
            break;
            case (1):
            stat2.setText("\n Borough with highest crime rate is\n"
            + "\t\t\t" + boroughProfileLoader.getMaxCrimeRate());
            caseCounter2 = -1;
            break;
        }
        caseCounter2++;
    }

    /**
     * Action of back  and forward button of third stat
     * @param event The ActionEvent
     */
    private void stat3Action(ActionEvent event) {
        switch(caseCounter3)
        {
            case (0): 
            stat3.setText("\n  Total number of homes available.\n" +
                "\t\t\t" +  dataLoader.getHome());
            break;
            case (1): 
            stat3.setText("\n\n Borough with highestcarbon emmision is\n"
            + "\t\t\t" + boroughProfileLoader.getCarbonEmission());
            caseCounter3 = -1;
            break;
        }
        caseCounter3++;
    }

    /**
     * Action of back  and forward button of fourth stat
     * @param event The ActionEvent
     */
    private void stat4Action(ActionEvent event) {
        switch(caseCounter4)
        {
            case (0): 
            stat4.setText("\n This is The most expensive borough\n");
            break;
            case (1): 
            stat4.setText("\n Borough with highestcarbon emmision is\n" +
            "\t\t\t" +  boroughProfileLoader.getGreenSpace());           
            caseCounter4 = -1;
            break;
        }
        caseCounter4++;
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