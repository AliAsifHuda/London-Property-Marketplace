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
public class HexaButton extends Application
{
    // We keep track of the count, and label displaying the count:
    private int count = 0;
    private Label myLabel = new Label("0");
    private Button myButton;
    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage)
    {
        getButton("name", 0, 0);
    }

    public Button getButton(String burroughName, int x, int y)
    {
        Button myButton = new Button();
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[]{
                200.0, 250.0,
                1100.0, 500.0,
                2000.0, 250.0, 
                2000.0, -250.0, 
                1100.0, -500.0,
                200.0, -250.0,
            });                                                                                                                            
        myButton.setShape(polygon);
        myButton.setMinSize(110,110);
        myButton.setTranslateX(x);
        myButton.setTranslateY(y);
        myButton.setText(burroughName);
        return myButton;
    } 

    public void setText(String text)
    {
        myButton.setText(text);
    }

}