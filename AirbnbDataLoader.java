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
 * 
 * @author David J. Barnes, Michael Kölling, Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (3)
 */
public class AirbnbDataLoader {
    // The set containing all the boroughs in the data
    private ArrayList<String> boroughsSet;

    private static ArrayList<AirbnbListing> listings = new ArrayList<>();

    private HashMap<Integer, AirbnbListing> listingsMap;
    //ArrayList to contain the number of reviews of each property
    private ArrayList<Integer> reviews = new ArrayList<>();

    private ArrayList<Integer> available = new ArrayList<>();

    private ArrayList<String> home = new ArrayList<>();

    public AirbnbDataLoader() {
        boroughsSet = new ArrayList<>();
        listingsMap = new HashMap<>();
        //        listings = load();
    }

    /** 
     * Return an ArrayList containing the all listings in
     * the AirBnB London data set csv file.
     */
    public void load() {
        System.out.print("Begin loading Airbnb london dataset...");
        try {
            URL url = getClass().getResource("airbnb-london.csv");
            CSVReader reader = new CSVReader(new FileReader(new File(url.toURI()).getAbsolutePath()));
            String [] line;
            //skip the first row (column headers)
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                String id = line[0];
                String name = line[1];
                String host_id = line[2];
                String host_name = line[3];
                String neighbourhood = line[4];
                double latitude = convertDouble(line[5]);
                double longitude = convertDouble(line[6]);
                String room_type = line[7];
                int price = convertInt(line[8]);
                int minimumNights = convertInt(line[9]);
                int numberOfReviews = convertInt(line[10]);
                String lastReview = line[11];
                double reviewsPerMonth = convertDouble(line[12]);
                int calculatedHostListingsCount = convertInt(line[13]);
                int availability365 = convertInt(line[14]);

                AirbnbListing listing = new AirbnbListing(
                        id, name, host_id, host_name, neighbourhood, latitude,
                        longitude, room_type, price, minimumNights,
                        numberOfReviews, lastReview,
                        reviewsPerMonth, calculatedHostListingsCount, availability365
                    );
                if (!boroughsSet.contains(listing.getNeighbourhood())) {
                    // add all different boroughs to this set.
                    boroughsSet.add(listing.getNeighbourhood());
                }
                listingsMap.put(listing.getPrice(), listing);
                listings.add(listing);
                reviews.add(listing.getNumberOfReviews());
                if (listing.getAvailability365()>0) {
                    available.add(listing.getAvailability365());
                }
                if (!listing.getRoom_type().equals("Private room")) {
                    home.add(listing.getId());
                }
            }
        } catch(IOException | URISyntaxException e) {
            System.out.println("Failure! Something went wrong");
            e.printStackTrace();
        }
        System.out.println("Success! Number of loaded records: " + listings.size());
        //        return listings;
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
     * @return A list containing all the objects in our data set
     */
    public static ArrayList<AirbnbListing> getListings() {
        return listings; 
    }

    /**
     * @return The listing object with the least price.
     */
    public AirbnbListing getMinPriceListing() {
        // the minimum price of all elements in the set.
        int minPrice = Collections.min(listingsMap.keySet());
        // return the listing with the least price
        return listingsMap.get(minPrice);
    }

    /**
     * @return The listing object with the highest price.
     */
    public AirbnbListing getMaxPriceListing() {
        // the maximum price of all elements in the set.
        int maxPrice = Collections.max(listingsMap.keySet());
        // return the listing with the highest price
        return listingsMap.get(maxPrice);
    }

    /**
     * @return The sum of number of reviews of all properties.
     */
    public int getNumberOfReviews() {
        int sum = 0;
        for (int i = 0; i < reviews.size(); i++) {
            sum = sum + reviews.get(i);
        }
        return sum;
    }

    /**
     * @return The number of properties available.
     */
    public int getAvailability()
    {
        return available.size();
    }

    /**
     * @return The number of homes available.
     */
    public int getHome()
    {
        return home.size();
    }

    /**
     * @return A Set of type String containing all the boroughs in our dataset
     */
    public ArrayList<String> getBoroughs() {
        return boroughsSet;
    }
}
