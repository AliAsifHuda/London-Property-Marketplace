import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import com.opencsv.CSVReader;
import java.net.URISyntaxException;

/**
 * Loads various types of statistics from the London Boroughs
 * in the london-borough-profiles.csv file
 */
public class BoroughProfileLoader {

    /**
     * Get some specific type of profiles from the London boroughs profile
     * @param columnNum The number of column which has the required data
     * @return A list containing objects of the required type.
     */
    public ArrayList<BoroughProfile> getProfiles(int columnNum) {
        ArrayList<BoroughProfile> boroughProfiles = new ArrayList<>();
        try {
            URL url = getClass().getResource("london-borough-profiles.csv");
            CSVReader reader = new CSVReader(new FileReader(new File(url.toURI()).getAbsolutePath()));
            String [] line;
            //skip the first row (column headers)
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                if (line[1].equals("Inner London")) {
                    break;
                }
                String boroughName = line[1];
                String boroughProfile = "" + line[columnNum - 1];
                BoroughProfile profile = new BoroughProfile(boroughName, boroughProfile);
                boroughProfiles.add(profile);
            }
        } catch(IOException | URISyntaxException e) {
            System.out.println("Failure loading borough profiles! Something went wrong");
            e.printStackTrace();
        }
        return boroughProfiles;
    }
}
