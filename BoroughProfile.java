/**
 * A class for different kinds of profiles. (i.e., crime rate,
 * employment rate etc.) in each borough of London.
 * @author David J. Barnes, Michael Kölling, Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (3)
 */

public class BoroughProfile {

    private String boroughName;
    private String profileValue;

    public BoroughProfile(String boroughName, String profileValue) {
        this.boroughName = boroughName;
        this.profileValue = profileValue;
    }

    /**
     * @return The value of the profile type of this object (crime rate, employment rate etc).
     */
    public String getProfileValue() {
        return profileValue;
    }

    /**
     * @return The borough of this object.
     */
    public String getBoroughName() {
        return boroughName;
    }
}
