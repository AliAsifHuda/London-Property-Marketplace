import javafx.application.Application;
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
    Scene scene2, scene3, scene4;
    private int counter = 0;
    private AirbnbDataLoader dataLoader = new AirbnbDataLoader();
    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param window the primary stage for this application.
     */
    @Override
    public final void start(Stage window) {
        // window = window;
        window.setTitle("Property");
        //controls and lines for the top bit
        HBox commonLayout = new HBox(15); 
        Label fromLabel = new Label("From");
        Label backLabel = new Label("To");
        ComboBox<Integer> fromComboBox1 = new ComboBox<>();
        fromComboBox1.getItems().add(dataLoader.getMinPriceListing().getPrice());
        fromComboBox1.setPromptText("-");
        ComboBox<Integer> toComboBox1 = new ComboBox<>();
        toComboBox1.getItems().add(dataLoader.getMaxPriceListing().getPrice());
        toComboBox1.setPromptText("-");
        AnchorPane fromToButtonPane = new AnchorPane();
        commonLayout.setHgrow(fromToButtonPane , Priority.ALWAYS); 
        commonLayout.getChildren().add(fromToButtonPane);
        //add all comtrol items to layout style
        commonLayout.getChildren().addAll(fromLabel, fromComboBox1, backLabel, toComboBox1);

        //text for the middle
        Text text1 = new Text("Welcome to London Property Marketplace \u0627\u0644\u0633\u0644\u0627\u0645 \u0639\u0644\u064a\u0643\u0645 ");
        TextFlow textFlowPane = new TextFlow();
        textFlowPane.getChildren().addAll(text1);

        //controls and lines for the bottom bit
        HBox bottomBox = new HBox(15);
        bottomBox.setStyle("-fx-border-color: black; -fx-border-width: 0.25px 0px 0px 0px; -fx-padding: 5px ;");
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
        BorderPane.setMargin(commonLayout, new Insets(10, 10, 10, 10)); 
        borderPane.setCenter(textFlowPane);
        borderPane.setBottom(bottomBox);
        BorderPane.setMargin(bottomBox, new Insets(10, 10, 10, 10));
        borderPane.getStylesheets().add("abc.css");

        // image panel
        Image image = new Image("boroughs.png");
        ImageView imageView= new ImageView(image);
        imageView.setFitHeight(700);
        imageView.setFitWidth(780);
        //backButton.setOnAction(e -> borderPane.setCenter(imageView));

        buttonForward.setOnAction( e -> {
            if (counter==0) {
                borderPane.setCenter(imageView);
                counter++;
            } else if  (counter==1) {
                borderPane.setCenter(textFlowPane);
                counter=0;
            }
        });

        backButton.setOnAction( e -> {
            if (counter==0) {
                borderPane.setCenter(imageView);
                counter++;
            } else if  (counter==1) {
                borderPane.setCenter(textFlowPane);
                counter=0;
            }
        });

        // Show the Stage (window)
        scene1 = new Scene(borderPane,500, 500);
        window.setScene(scene1);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


