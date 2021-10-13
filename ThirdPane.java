import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
/**
 * 
 */
public class ThirdPane
{
    HBox hbox = new HBox(150);
    private Label top = new Label();
    private Label stat1 = new Label();
    private Label stat2 = new Label();
    private Label stat3 = new Label();
    private Label stat4 = new Label();

    final Button stat1b = new Button(" < ");
    final Button stat2b = new Button(" < ");
    final Button stat3b = new Button(" < ");
    final Button stat4b = new Button(" < ");

    final Button stat1f = new Button(" > ");
    final Button stat2f = new Button(" > ");
    final Button stat3f = new Button(" > ");
    final Button stat4f = new Button(" > ");

    private static final AirbnbDataLoader dataLoader = new AirbnbDataLoader();
    private int caseCounter1 = 1;
    private int caseCounter2 = 1;
    private int caseCounter3 = 1;
    private int caseCounter4 = 1;
    private BoroughProfileLoader boroughProfileLoader = new BoroughProfileLoader();
    private GridPane gridPane = new GridPane();

    /**
     * Constructor for objects of class ThirdPane
     */
    public ThirdPane()
    {
        dataLoader.load();
        setStat();
        setGridPane();
        setButton();
        hbox.requestLayout();
    }

    /**
     * A Pane for showing Statistics.
     * @return The StackPane displaying Statistics.
     */
    public HBox statisticsPane() {
        hbox.getChildren().clear();
        hbox.setTranslateX(20);
        hbox.setTranslateY(-30);
        hbox.setMinSize(700, 450);
        hbox.setMaxSize(700, 450);
        hbox.getChildren().add(gridPane);
        hbox.setMargin(gridPane, new Insets(40, 10, 10, 20));

        return hbox;
    }

    /**
     * A Pane containing data and buttons.
     */
    private void setGridPane(){
        gridPane.setMinSize(700, 450);
        gridPane.add(top, 1, 0, 4, 2);
        gridPane.add(stat1, 3, 2, 4, 2);
        gridPane.add(stat1b, 1, 2, 2, 2);
        gridPane.add(stat1f, 8, 2, 2, 2);
        gridPane.add(stat2, 3, 4, 4, 2);
        gridPane.add(stat2b, 1, 4, 2, 2);
        gridPane.add(stat2f, 7, 4, 2, 2);
        gridPane.add(stat3, 3, 6, 4, 2);
        gridPane.add(stat3b, 1, 6, 2, 2);
        gridPane.add(stat3f, 7, 6, 2, 2);
        gridPane.add(stat4, 3, 8, 4, 2);
        gridPane.add(stat4b, 1, 8, 2, 2);
        gridPane.add(stat4f, 7, 8, 2, 2);
    }

    /**
     * Text containg stats.
     */
    private void setStat(){
        top.setText("\t\t\t\t statistics");
        top.setStyle("-fx-text-fill: #4285f4; -fx-font-size : 30; -fx-font-style : oblique;");
        stat1.setText("   Average number of reviews per property: \n" +
            " \t\t\t\t  " + dataLoader.getNumberOfReviews()/dataLoader.getListings().size());
        stat2.setText("     Total number of available properties:  \n" +
            "\t\t\t\t" + dataLoader.getAvailability());
        stat3.setText("        Total number of homes available:    \n" +
            "\t\t\t\t" +  dataLoader.getHome());
        stat4.setText("      This is The most expensive borough:   \n" + "\t\t\t" +
            dataLoader.getMostExpensiveBorough());
        stat4.setStyle("-fx-text-fill: red;");
    }

    private void setButton(){
        stat1b.setOnAction(this::stat1Action);
        stat2b.setOnAction(this::stat2Action);
        stat3b.setOnAction(this::stat3Action);
        stat4b.setOnAction(this::stat4Action);

        stat1f.setOnAction(this::stat1Action);
        stat2f.setOnAction(this::stat2Action);
        stat3f.setOnAction(this::stat3Action);
        stat4f.setOnAction(this::stat4Action);
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
}
