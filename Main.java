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
import javafx.scene.control.ToggleButton;
import javafx.geometry.Orientation;

import java.io.IOException;
import java.util.HashSet;
import javafx.scene.paint.Color;
import java.util.Arrays;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.animation.RotateTransition;  
import javafx.util.Duration; 
/**
 * Write a description of JavaFX class a here.
 *
 * @author David J. Barnes, Michael Kölling, Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (3)
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
    private ToggleButton darkModeButton = new ToggleButton("Dark Mode");
    private Text text1 = new Text("Welcome to London Property Marketplace ");
    private int caseCounter1 = 1;
    private int caseCounter2 = 1;
    private int caseCounter3 = 1;
    private int caseCounter4 = 1;
    private int caseCounter5 = 0;
    private boolean disableFlagA = false;
    private boolean disableFlagB = false;
    private AirbnbDataLoader dataLoader = new AirbnbDataLoader();
    private BoroughProfileLoader boroughProfileLoader = new BoroughProfileLoader();
    private Label stat1 = new Label();
    private Label stat2 = new Label();
    private Label stat3 = new Label();
    private Label stat4 = new Label();
    private WebCrawler webCrawler = new WebCrawler();
    // check if the values in "from" and "to" boxes are correct
    private boolean correctBoxValues = true;
    // A set containing all the buttons in the borough
    private HashSet<HexButton> boroughButtons = new HashSet<>();
    private BoroughInfo boroughInfo = new BoroughInfo();
    //store the value selected in the fromComboBox
    private static int minRange;
    //store the value selected in the toComboBox
    private static int maxRange;
    private CategoryAxis xAxis = new CategoryAxis();  
    private NumberAxis yAxis = new NumberAxis();
    private BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
    HBox hbox = new HBox(50);

    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param primaryStage the primary stage for this application.
     */
    @Override
    public final void start(Stage primaryStage) throws IOException {
        dataLoader.load();
        webCrawler.fetchData();
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
        text1.setFont(Font.font("calibri", FontWeight.BOLD, FontPosture.ITALIC, 25));
        text1.setFill(Color.BLACK);
        text1.setStrokeWidth(0.8);
        text1.setStroke(Color.WHITE);
        textPane.getChildren().addAll(text1);

        //controls and lines for the bottom bit
        HBox bottomBox = new HBox(15);
        AnchorPane backButtonPane = new AnchorPane();//AnchorPane to align the buttons
        AnchorPane forwardButtonPane = new AnchorPane();//AnchorPane to align the buttons
        AnchorPane darkModeButtonPane = new AnchorPane();//AnchorPane to align the buttons
        HBox.setHgrow(backButtonPane , Priority.ALWAYS);//Make pane grow horizontally
        bottomBox.getChildren().add(backButtonPane );
        bottomBox.getChildren().add(forwardButtonPane );
        bottomBox.getChildren().add(darkModeButtonPane);
        backButtonPane.getChildren().add(backButton);
        forwardButtonPane.getChildren().add(forwardButton);
        darkModeButtonPane.getChildren().add(darkModeButton);

        //make a borderPane layout and add layouts to top and bottom
        borderPane.setTop(commonLayout);        
        BorderPane.setMargin(commonLayout, new Insets(20));
        borderPane.setCenter(textPane);        
        borderPane.setBottom(bottomBox);
        BorderPane.setMargin(bottomBox, new Insets(10));
        //borderPane.getStylesheets().add("abc.css");

       backButton.setStyle("-fx-text-fill : #000000;");
       forwardButton.setStyle("-fx-text-fill : #000000;");
       darkModeButton.setStyle("-fx-text-fill : #000000;");
        // back and forward button are initially disabled
        backButton.setDisable(true);
        forwardButton.setDisable(true);

        forwardButton.setOnAction(this::forwardAction);
        backButton.setOnAction(this::backAction);
        darkModeButton.setOnAction(this::darkModeAction);

        //ActionEvent for the combo boxes
        fromComboBox.setOnAction(this::fromAction);
        toComboBox.setOnAction(this::toAction);

        //lines at top and bottom
        bottomBox.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px; -fx-padding: 5px ;");
        commonLayout.setStyle("-fx-border-color: black; -fx-border-width: 0px 0px 1px 0px; -fx-padding: 5px ;");   

        imagePane();
        statisticsPane();
        chartGraph();

        // Show the Stage (window)
        window.setScene(new Scene(borderPane, 1080, 600));
        window.show();
    }

    /**
     * Create and display the map and buttons on the second screen.
     * @return The StackPane displaying the map and buttons
     */
    private StackPane imagePane()
    {
        //keeps track of the index of a button (which button it is)
        int index = 0;
        //initial starting position values of buttons
        int a = -55 , b = -175 , c = -215 , d = -175 , e = -135 , f = -96;
        for (String boroughName : dataLoader.getBoroughs()) {
            HexButton hexButton = new HexButton(boroughName);
            boroughButtons.add(hexButton);
            if (index == 0) {
                //gap between each button
                hexButton.setButtonPosition(65, -180);
            } else if (index <= 3) {
                hexButton.setButtonPosition(a, -120);
                a = a + 80;  
            } else if (index >= 4 && index <= 10) {
                hexButton.setButtonPosition(b, -60);
                b = b + 80;
            } else if (index >= 10 && index <= 17) {
                hexButton.setButtonPosition(c, 0);
                c = c + 80;
            } else if (index >= 17 && index <= 23) {
                hexButton.setButtonPosition(d, 60);
                d = d + 80;
            } else if (index >= 24 && index <= 28) {
                hexButton.setButtonPosition(e, 120);
                e = e + 80;
            } else if (index >= 28 && index <= 34) {
                hexButton.setButtonPosition(f, 180);
                f = f + 80;
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
    private HBox statisticsPane() {
        hbox.setTranslateX(0);
        hbox.setTranslateY(-28);
        hbox.setMinSize(440, 355);
        hbox.setMaxSize(440, 355);

        GridPane gridPane = new GridPane();
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

        stat1.setText("   Average number of reviews per property: \n" +
            " \t\t\t\t  " + dataLoader.getNumberOfReviews()/dataLoader.getListings().size());
        stat2.setText("     Total number of available properties:  \n" +
            "\t\t\t\t" + dataLoader.getAvailability());
        stat3.setText("        Total number of homes available:    \n" +
            "\t\t\t\t" +  dataLoader.getHome());
        stat4.setText("      This is The most expensive borough:   \n" + "\t\t\t" +
            dataLoader.getMostExpensiveBorough());
        stat4.setStyle("-fx-text-fill: red;");

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
        hbox.setMargin(gridPane, new Insets(40, 10, 10, 20));

        stat1b.setOnAction(this::stat1Action);
        stat2b.setOnAction(this::stat2Action);
        stat3b.setOnAction(this::stat3Action);
        stat4b.setOnAction(this::stat4Action);

        stat1f.setOnAction(this::stat1Action);
        stat2f.setOnAction(this::stat2Action);
        stat3f.setOnAction(this::stat3Action);
        stat4f.setOnAction(this::stat4Action);

        hbox.requestLayout();
        return hbox;
    }

    /**
     * A Pane for showing a graph that co-relates to covid 19 cases in each borough.
     * @return The barchary displaying cases in each borough.
     */
    private BarChart chartGraph()
    {
        //Creating the Bar chart 
        barChart.setTitle("Covid-19 deaths in each borough");
        yAxis.setLabel("Deaths");
        xAxis.setLabel("Borough");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("High risk zone");
        xAxis.setCategories(FXCollections.<String>
            observableArrayList(Arrays.asList(
                    webCrawler.getBoroughCasesList().get(0).getBoroughName(),
                    webCrawler.getBoroughCasesList().get(1).getBoroughName(), 
                    webCrawler.getBoroughCasesList().get(2).getBoroughName(),
                    webCrawler.getBoroughCasesList().get(3).getBoroughName(),
                    webCrawler.getBoroughCasesList().get(4).getBoroughName(), 
                    webCrawler.getBoroughCasesList().get(5).getBoroughName(),
                    webCrawler.getBoroughCasesList().get(6).getBoroughName(),
                    webCrawler.getBoroughCasesList().get(7).getBoroughName(), 
                    webCrawler.getBoroughCasesList().get(8).getBoroughName(),
                    webCrawler.getBoroughCasesList().get(9).getBoroughName(),
                    webCrawler.getBoroughCasesList().get(10).getBoroughName(), 
                    webCrawler.getBoroughCasesList().get(11).getBoroughName(),
                    webCrawler.getBoroughCasesList().get(12).getBoroughName(),
                    webCrawler.getBoroughCasesList().get(13).getBoroughName(), 
                    webCrawler.getBoroughCasesList().get(14).getBoroughName(),
                    webCrawler.getBoroughCasesList().get(15).getBoroughName(),
                    webCrawler.getBoroughCasesList().get(16).getBoroughName(), 
                    webCrawler.getBoroughCasesList().get(17).getBoroughName(),
                    webCrawler.getBoroughCasesList().get(18).getBoroughName(),
                    webCrawler.getBoroughCasesList().get(19).getBoroughName(), 
                    webCrawler.getBoroughCasesList().get(20).getBoroughName(),
                    webCrawler.getBoroughCasesList().get(21).getBoroughName(),
                    webCrawler.getBoroughCasesList().get(22).getBoroughName(), 
                    webCrawler.getBoroughCasesList().get(23).getBoroughName(), 
                    webCrawler.getBoroughCasesList().get(24).getBoroughName(),
                    webCrawler.getBoroughCasesList().get(25).getBoroughName(), 
                    webCrawler.getBoroughCasesList().get(26).getBoroughName(), 
                    webCrawler.getBoroughCasesList().get(27).getBoroughName(),
                    webCrawler.getBoroughCasesList().get(28).getBoroughName()
                )));

        //Prepare XYChart.Series objects by setting data       
        series1.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(0).getBoroughName(),
                webCrawler.getBoroughCasesList().get(0).getCases()));
        series1.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(1).getBoroughName(),
                webCrawler.getBoroughCasesList().get(1).getCases()));
        series1.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(2).getBoroughName(),
                webCrawler.getBoroughCasesList().get(2).getCases()));
        series1.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(3).getBoroughName(),
                webCrawler.getBoroughCasesList().get(3).getCases()));
        series1.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(4).getBoroughName(),
                webCrawler.getBoroughCasesList().get(4).getCases()));
        series1.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(5).getBoroughName(),
                webCrawler.getBoroughCasesList().get(5).getCases()));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Medium risk zone");
        series2.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(6).getBoroughName(),
                webCrawler.getBoroughCasesList().get(6).getCases()));
        series2.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(7).getBoroughName(),
                webCrawler.getBoroughCasesList().get(7).getCases()));
        series2.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(8).getBoroughName(),
                webCrawler.getBoroughCasesList().get(8).getCases()));
        series2.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(9).getBoroughName(),
                webCrawler.getBoroughCasesList().get(9).getCases()));
        series2.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(10).getBoroughName(),
                webCrawler.getBoroughCasesList().get(10).getCases()));
        series2.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(11).getBoroughName(),
                webCrawler.getBoroughCasesList().get(11).getCases()));
        series2.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(12).getBoroughName(),
                webCrawler.getBoroughCasesList().get(12).getCases()));
        series2.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(13).getBoroughName(),
                webCrawler.getBoroughCasesList().get(13).getCases()));
        series2.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(14).getBoroughName(),
                webCrawler.getBoroughCasesList().get(14).getCases()));
        series2.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(15).getBoroughName(),
                webCrawler.getBoroughCasesList().get(15).getCases()));
        series2.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(16).getBoroughName(),
                webCrawler.getBoroughCasesList().get(16).getCases()));
        series2.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(17).getBoroughName(),
                webCrawler.getBoroughCasesList().get(17).getCases()));
        series2.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(18).getBoroughName(),
                webCrawler.getBoroughCasesList().get(18).getCases()));
        series2.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(19).getBoroughName(),
                webCrawler.getBoroughCasesList().get(19).getCases()));
        series2.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(20).getBoroughName(),
                webCrawler.getBoroughCasesList().get(20).getCases()));

        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Low risk zone");
        series3.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(21).getBoroughName(),
                webCrawler.getBoroughCasesList().get(21).getCases()));
        series3.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(22).getBoroughName(),
                webCrawler.getBoroughCasesList().get(22).getCases()));
        series3.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(23).getBoroughName(),
                webCrawler.getBoroughCasesList().get(23).getCases()));
        series3.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(24).getBoroughName(),
                webCrawler.getBoroughCasesList().get(24).getCases()));
        series3.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(25).getBoroughName(),
                webCrawler.getBoroughCasesList().get(25).getCases()));
        series3.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(26).getBoroughName(),
                webCrawler.getBoroughCasesList().get(26).getCases()));
        series3.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(27).getBoroughName(),
                webCrawler.getBoroughCasesList().get(27).getCases()));
        series3.getData().add(new XYChart.Data<>(webCrawler. getBoroughCasesList().get(28).getBoroughName(),
                webCrawler.getBoroughCasesList().get(28).getCases()));

        //Setting the data to bar chart  
        barChart.getData().addAll(series1, series2, series3);
        return barChart;
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
           
            RotateTransition rotate = new RotateTransition();
            rotate.setByAngle(360);  
            rotate.setCycleCount(2);
            rotate.setDuration(Duration.millis(50));
            rotate.setAutoReverse(true);
            rotate.setToAngle(360);   
            rotate.setNode(forwardButton);  
            
            RotateTransition rotate1 = new RotateTransition();
            rotate1.setByAngle(360);
            rotate1.setCycleCount(2);
            rotate1.setDuration(Duration.millis(50));                         
            rotate1.setAutoReverse(true);            
            rotate1.setToAngle(360);
            rotate1.setNode(backButton);
            
            rotate.play(); 
            rotate1.play();
        }
    }

    /**
     * Action of the forward button.
     * @param event The ActionEvent
     */
    private void forwardAction(ActionEvent event)
    {
        if (borderPane.getCenter().equals(textPane)) {
            analyzeBoxValues(fromComboBox, toComboBox);
            if (correctBoxValues) {
                borderPane.setCenter(imagePane());
            }
        } else if  (borderPane.getCenter().equals(stackPane)) {
            borderPane.setCenter(hbox);
        } else if  (borderPane.getCenter().equals(hbox)) {
            borderPane.setCenter(barChart);
        } else if  (borderPane.getCenter().equals(barChart)) {
            borderPane.setCenter(textPane);
        }
    }

    /**
     * Action of back button
     * @param event The ActionEvent
     */
    private void backAction(ActionEvent event) {
        if (borderPane.getCenter().equals(textPane)) {
            analyzeBoxValues(fromComboBox, toComboBox);
            if (correctBoxValues) {
                borderPane.setCenter(barChart);
            }
        }
        else if (borderPane.getCenter().equals(stackPane)) {
            borderPane.setCenter(textPane);
        } 
        else if (borderPane.getCenter().equals(hbox)) {
            borderPane.setCenter(stackPane);
        } else if  (borderPane.getCenter().equals(barChart)) {
            borderPane.setCenter(hbox);
        }
    }
    
    private void darkModeAction(ActionEvent event) {
        if (caseCounter5 == 0) {
            borderPane.getStylesheets().remove("abc.css");
            borderPane.getStylesheets().add("DarkMode.css");
            caseCounter5++;
        }
        
        else if (caseCounter5 == 1) {
            borderPane.getStylesheets().remove("DarkMode.css");
            borderPane.getStylesheets().add("abc.css");
            caseCounter5 = 0;
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
            stat1.setText("   Average number of reviews per property: \n" +
                " \t\t\t\t " + dataLoader.getNumberOfReviews()/dataLoader.getListings().size());
            stat1.setStyle("-fx-text-fill: black;");

            break;
            case (1): 
            stat1.setText("Borough with highest transport acessibility:\n" +
                "\t\t\t" + boroughProfileLoader.getMaxTransportAcessibility());
            stat1.setStyle("-fx-text-fill: green;");
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
            stat2.setText("     Total number of available properties:  \n" +
                "\t\t\t\t  " + dataLoader.getAvailability());
            stat2.setStyle("-fx-text-fill: black;");
            break;
            case (1):
            stat2.setText("\tBorough with highest crime rate is:\n"
                + "\t\t\t" + boroughProfileLoader.getMaxCrimeRate());
            stat2.setStyle(" -fx-text-fill: red;");
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
            stat3.setText("        Total number of homes available:    \n" +
                "\t\t\t\t" +  dataLoader.getHome());
            stat3.setStyle("-fx-text-fill: black;");
            break;
            case (1): 
            stat3.setText(" Borough with highest carbon emmision is:\n"
                + "\t\t\t" + boroughProfileLoader.getCarbonEmission());
            stat3.setStyle("-fx-text-fill: green;");
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
        switch (caseCounter4) {
            case (0):
            stat4.setText("     This is The most expensive borough:   \n"
                + "\t\t\t" + dataLoader.getMostExpensiveBorough());
            stat4.setStyle("-fx-text-fill: red;");
            break;
            case (1):
            stat4.setText("    Borough with highest green space is:\n" +
                "\t\t\t" + boroughProfileLoader.getGreenSpace());
            stat4.setStyle("-fx-text-fill: green; ");
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