/**
 * A class for different kinds of profiles. (i.e., crime rate,
 * employment rate etc.) in each borough of London.
 */

public class BoroughProfiles {

    private String boroughName;
    private String profileValue;

    public BoroughProfiles(String boroughName, String profileValue) {
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
