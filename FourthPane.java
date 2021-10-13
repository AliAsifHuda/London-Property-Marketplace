import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import java.util.Arrays;
import javafx.collections.FXCollections;
import java.io.IOException;
import java.text.ParseException;
import java.lang.InterruptedException;
/**
 * Write a description of class FourthPane here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FourthPane
{
    WebCrawler webCrawler = new WebCrawler();
    CategoryAxis yAxis = new CategoryAxis();  
    NumberAxis xAxis = new NumberAxis();
    BarChart<Number, String> barChart = new BarChart<>(xAxis, yAxis);
    XYChart.Series<Number, String> series1 = new XYChart.Series<>();
    XYChart.Series<Number, String> series2 = new XYChart.Series<>();
    XYChart.Series<Number , String> series3 = new XYChart.Series<>();
    public FourthPane()
    {
        try{
            webCrawler.fetchData();

            barChart.setLegendVisible(false);
            barChart.minHeight(540);
            barChart.setTranslateX(-20);
            barChart.setTitle("Covid-19 deaths in each borough");
            xAxis.setLabel("Deaths");
            yAxis.setLabel("Borough");

            SetYAxis();
            setFirstSeries();
            setSecondSeries();
            setThirdSeries();
        }catch(Exception e){
        }
    }

    /**
     * A Pane for showing a graph that co-relates to covid 19 cases in each borough.
     * @return The barchary displaying cases in each borough.
     */
    public BarChart chartGraph() throws IOException, ParseException
    {
        barChart.getData().clear();

        //Setting the data to bar chart  
        barChart.getData().addAll(series1, series2, series3);
        return barChart;
    } 

    private void SetYAxis(){
        yAxis.setCategories(FXCollections.<String>
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
    }

    private void setFirstSeries()
    {
        for(int i = 0; i<=5; i++){
            series1.getData().add(new XYChart.Data<>(webCrawler.getBoroughCasesList().get(i).getCases(),
                    webCrawler. getBoroughCasesList().get(i).getBoroughName()));
        }
    }

    private void setSecondSeries()
    {
        for(int i = 6; i<=20; i++){
            series2.getData().add(new XYChart.Data<>(webCrawler.getBoroughCasesList().get(i).getCases(),
                    webCrawler. getBoroughCasesList().get(i).getBoroughName()));
        }
    }

    private void setThirdSeries()
    {
        for(int i = 21; i<=28; i++){
            series2.getData().add(new XYChart.Data<>(webCrawler.getBoroughCasesList().get(i).getCases(),
                    webCrawler. getBoroughCasesList().get(i).getBoroughName()));
        }
    }
}
