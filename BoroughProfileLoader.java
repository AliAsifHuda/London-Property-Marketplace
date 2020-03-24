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
    private HashMap<Integer , String> transportMap;
    private HashMap<Integer , String> crimeMap;
    private HashMap<Integer , String> carbonMap;
    private HashMap<Integer , String> greenMap;

    public BoroughProfileLoader() {
        transportMap = new HashMap<>();
        crimeMap  = new HashMap<>();
        carbonMap = new HashMap<>();
        greenMap = new HashMap<>();
        load();
    }

    /** 
     * Return an ArrayList containing the all listings in
     * the AirBnB London data set csv file.
     */

    public void load() {
        try {
            URL url = getClass().getResource("london-borough-profiles.csv");
            CSVReader reader = new CSVReader(new FileReader(new File(url.toURI()).getAbsolutePath()));
            String [] line;
            //skip the first row (column headers)
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                String name = line[1];
                int transportAccessibility = convertInt(line[64]);
                int crimeRate = convertInt(line[48]);
                int carbonEmissions = convertInt(line[59]);
                int greenSpace = convertInt(line[58]);
                BoroughProfiles listing = new BoroughProfiles(
                        name, transportAccessibility, crimeRate , carbonEmissions , greenSpace
                    );
                transportMap.put(transportAccessibility, name);
                crimeMap.put(crimeRate , name);
                carbonMap.put(carbonEmissions , name);
                greenMap.put(greenSpace , name);

                if (line[1].equals("Inner London")) {
                    break;
                }
                String boroughName = line[1];
            }
        } catch(IOException | URISyntaxException e) {
            System.out.println("Failure! Something went wrong");
            e.printStackTrace();
        }
    }

    /**
     * @param intString The string to be converted to Integer type
     * @return The Integer value of the string, or -1 if the string is
     * either empty or just whitespace
     */
    private Integer convertInt(String intString) {
        if(intString != null && !intString.trim().equals("")) {
            return Integer.parseInt(intString);
        }
        return -1;
    }

    /**
     * @return The listing object with the highest transport acessibility.
     */
    public String getMaxTransportAcessibility() {
        // the maximum acessibility of all elements in the set.
        int maxAcessibility = Collections.max(transportMap.keySet());
        // return the listing with the highest acessibility.
        return transportMap.get(maxAcessibility);
    }

    /**
     * @return The listing object with the highest crime rate.
     */
    public String getMaxCrimeRate() {
        // the maximum crime rate of all elements in the set.
        int maxCrime = Collections.max(crimeMap.keySet());
        // return the listing with the highest crime rate.
        return crimeMap.get(maxCrime);
    }

    /**
     * @return The listing object with the highest carbon emisions.
     */
    public String getCarbonEmission() {
        // the maximum crime rate of all elements in the set.
        int maxCarbon = Collections.max(carbonMap.keySet());
        // return the listing with the highest carbon emession.
        return carbonMap.get(maxCarbon);
    }

    /**
     * @return The listing object with the highest green space.
     */
    public String getGreenSpace() {
        // the maximum crime rate of all elements in the set.
        int maxGreen = Collections.max(greenMap.keySet());
        // return the listing with the highest green space.
        return greenMap.get(maxGreen);
    }
}
