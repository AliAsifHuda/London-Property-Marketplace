import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import com.opencsv.CSVReader;
import java.net.URISyntaxException;
import java.util.stream.*;

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
                int Average_Public_Transport_Accessibility_Score = convertInt(line[64]);
                int Crime_rates_per_thousand_population = convertInt(line[48]);
                int Total_carbon_emissions = convertInt(line[59]);
                int greenSpace = convertInt(line[58]);
                BoroughProfiles listing = new BoroughProfiles(
                        name, Average_Public_Transport_Accessibility_Score,
                        Crime_rates_per_thousand_population , Total_carbon_emissions, greenSpace
                    );
                transportMap.put(Average_Public_Transport_Accessibility_Score, name);
                crimeMap.put(Crime_rates_per_thousand_population, name);
                carbonMap.put(Total_carbon_emissions , name);
                greenMap.put(greenSpace , name);
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
     * @return The listing object with the highest carbon emmisions.
     */
    public String getCarbonEmmission() {
        // the maximum crime rate of all elements in the set.
        int maxCarbon = Collections.max(carbonMap.keySet());
        // return the listing with the highest crime rate.
        return carbonMap.get(maxCarbon);
    }

    /**
     * @return The listing object with the highest green space.
     */
    public String getGreenSpace() {
        // the maximum crime rate of all elements in the set.
        int maxGreen = Collections.max(greenMap.keySet());
        // return the listing with the highest crime rate.
        return greenMap.get(maxGreen);
    }
    // /**
    // * Get some specific type of profiles from the London boroughs profile
    // * @param columnNum The number of column which has the required data
    // * @return A list containing objects of the required type.
    // */
    // public ArrayList<BoroughProfiles> getProfiles(int columnNum) {
    // ArrayList<BoroughProfiles> boroughProfiles = new ArrayList<>();
    // try {
    // URL url = getClass().getResource("london-borough-profiles.csv");
    // CSVReader reader = new CSVReader(new FileReader(new File(url.toURI()).getAbsolutePath()));
    // String [] line;
    // //skip the first row (column headers)
    // reader.readNext();
    // while ((line = reader.readNext()) != null) {
    // String boroughName = line[1];
    // String boroughProfile = "" + line[columnNum - 1];
    // BoroughProfiles profile = new BoroughProfiles(boroughName, boroughProfile);
    // boroughProfiles.add(profile);
    // }
    // } catch(IOException | URISyntaxException e) {
    // System.out.println("Failure loading borough profiles! Something went wrong");
    // e.printStackTrace();
    // }
    // return boroughProfiles;
    // }
}
