import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import com.opencsv.CSVReader;
import java.net.URISyntaxException;

/**
 * 
 * @author David J. Barnes, Michael Kölling, Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (3)
 */
public class AirbnbDataLoader {
    // The set containing all the boroughs in the data
    private ArrayList<String> boroughsList;

    private static final ArrayList<AirbnbListing> listings = new ArrayList<>();

    private HashMap<Integer, AirbnbListing> listingsMap;
    //ArrayList to contain the number of reviews of each property
    private ArrayList<Integer> reviews = new ArrayList<>();

    private ArrayList<Integer> available = new ArrayList<>();

    private ArrayList<String> home = new ArrayList<>();

    public AirbnbDataLoader() {
        boroughsList = new ArrayList<>();
        listingsMap = new HashMap<>();
    }

    /** 
     * Return an ArrayList containing the all listings in
     * the AirBnB London data set csv file.
     */
    public final void load() {
        listings.clear();
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
                if (!boroughsList.contains(listing.getNeighbourhood())) {
                    // add all different boroughs to this set.
                    boroughsList.add(listing.getNeighbourhood());
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
    public static List<AirbnbListing> getListings() {
        //List <AirbnbListing> unmodifaibleListings = Collections.unmodifiableList(listings);
        //return unmodifaibleListings; 
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
    public int getAvailability() {
        return available.size();
    }

    /**
     * @return The number of homes available.
     */
    public int getHome() {
        return home.size();
    }

    /**
     * @return A Set of type String containing all the boroughs in our dataset
     */
    public List<String> getBoroughs() {
        List<String> unmodifableBoroughList = Collections.unmodifiableList(boroughsList);
        return unmodifableBoroughList;
    }

    /**
     * @return The most expensive borough to be used in the stats button
     */
    public String getMostExpensiveBorough() {
        HashMap<Integer, String> boroughPriceValues  = new HashMap<>();
        for (String borough : boroughsList) {
           boroughPriceValues.put(getTotalPriceValue(borough), borough);
        }
        int maxValue = Collections.max(boroughPriceValues.keySet());        
        return boroughPriceValues.get(maxValue);
    }

    /**
     * Returns the 'price value' of a borough (obtained by multiplying the price
     * of each listing of borough with it's 'minimum nights' field).
     * @param borough The borough whose 'price value' we require.
     * @return The 'price value' of the borough.
     */
    private int getTotalPriceValue(String borough) {
        // using set for efficient iteration
        int boroughPriceValue = 0;
        HashSet<AirbnbListing> listingsSet = new HashSet<>(listings);
        for (AirbnbListing listing : listingsSet) {
            if (listing.getNeighbourhood().equals(borough)) {
                // add the values of the price and minimum nights multiplied to the int declared above
                boroughPriceValue += listing.getPrice() * listing.getMinimumNights();
            }
        }
        return boroughPriceValue;
    }
}
