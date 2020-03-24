/**
 * A class for different kinds of profiles. (i.e., crime rate,
 * employment rate etc.) in each borough of London.
 */

public class BoroughProfiles {

    private String boroughName;
    private String profileValue;
    private int transportAcessibility;
    private int crimeRate;
    private int carbon;
    private int greenSpace;
    
    public BoroughProfiles(String boroughName, int transportAcessibility, int crimeRate, int carbon, int greenSpace) {
        //this.boroughName = boroughName;
        this.profileValue = profileValue;
        this.transportAcessibility = transportAcessibility;
        this.crimeRate = crimeRate;
        this.carbon = carbon;
        this.greenSpace = greenSpace;
    }

    /**
     * @return The value of the profile type of this object (crime rate, employment rate etc).
     */
    // public String getProfileValue() {
        // return profileValue;
    // }

    /**
     * @return The borough of this object.
     */
    public String getBoroughName() {
        return boroughName;
    }
    
    /**
     * @return The transport acessibility of this object.
     */
    public double getTransportAcessibility() {
        return transportAcessibility;
    }
}
