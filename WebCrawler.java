import org.jsoup.Jsoup;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

/**
 * Our Web Crawler class to fetch data from the website
 * which displays the actual coronavirus cases in London
 * by each borough. This class uses the JSoup library
 * to crawl the website and execute this task.
 */
public class WebCrawler {

    private ArrayList<BoroughCoronaCases> casesByBorough = new ArrayList<>();

    /**
     * The main implementation of the WebCrawler which gets the
     * data from the website and makes BoroughCoronaCases objects from
     * the data and stores them in a list
     * @throws IOException If something goes wrong while parsing
     */
    public void fetchData() throws IOException {
        boolean firstRow = true;
        Document doc = Jsoup.connect("https://www.mylondon.news/news/health/london-coronavirus-map-exact-number-17973647").get();
        Elements rows = doc.select("tr");
        for (Element row : rows) {
            if (firstRow) {
                // skip parsing the first row of table (which contains column headers)
                firstRow = false;
                continue;
            }
            Elements columns = row.select("td");
            if (columns.get(0).text().equals("Total")) {
                // break out of the loop if fetching the last row of table (it contains the total cases)
                break;
            }
            String boroughName = columns.get(0).text();
            String cases = columns.get(1).text();
            double intCases = Double.parseDouble(cases);
            casesByBorough.add(new BoroughCoronaCases(boroughName, intCases));
        }
    }

    /**
     * @return The list containing BoroughCoronaCases objects.
     */
    public ArrayList<BoroughCoronaCases> getBoroughCasesList() {
        return casesByBorough;
    }

    /**
     *
     * @param doubleString the string to be converted to Double type
     * @return the Double value of the string, or -1.0 if the string is 
     * either empty or just whitespace
     */
    private Double convertDouble(String doubleString) {
        if(doubleString != null && !doubleString.trim().equals("")) {
            return Double.parseDouble(doubleString);
        }
        return -1.0;
    }
}
