import javafx.scene.layout.*;
import java.util.HashSet;
import javafx.scene.control.Tooltip;
import java.util.List;
/**
 * Write a description of class SecondPane here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SecondPane
{
    private static final AirbnbDataLoader dataLoader = new AirbnbDataLoader();
    private StackPane stackPane = new StackPane();
    private List<String> boroughs;

    public SecondPane()
    {
        dataLoader.load();
        boroughs = dataLoader.getBoroughs();
        setButtons();
    }

    private void setButtons(){
        // A set containing all the buttons in the borough
        HashSet<HexButton> boroughButtons = new HashSet<>();
        BoroughInfo boroughInfo = new BoroughInfo();
        int index = 0;
        //initial starting position values of buttons
        int a = -55 , b = -175 , c = -215 , d = -175 , e = -135 , f = -96;
        for (String boroughName : boroughs) {
            HexButton hexButton = new HexButton(boroughName);
            hexButton.createButton(boroughName);
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
    }

    /**
     * Create and display the map and buttons on the second screen.
     * @return The StackPane displaying the map and buttons
     */
    public StackPane imagePane()
    {        
        return stackPane;
    }

}
