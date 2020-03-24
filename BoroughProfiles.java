/**
 * A class for different kinds of profiles. (i.e., crime rate,
 * employment rate etc.) in each borough of London.
 */

public class BoroughProfiles {

    private String boroughName;
    private int transportAcessibility;
    private int crimeRate;
    private int carbon;
    private int greenSpace;

    public BoroughProfiles(String boroughName, int transportAcessibility, int crimeRate, int carbon, int greenSpace) {
        this.boroughName = boroughName;
        this.transportAcessibility = transportAcessibility;
        this.crimeRate = crimeRate;
        this.carbon = carbon;
        this.greenSpace = greenSpace;
    }
}
