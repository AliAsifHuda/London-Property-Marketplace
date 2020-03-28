/**
 * A class for different kinds of profiles. (i.e., crime rate,
 * employment rate etc.) in each borough of London.
 * @author David J. Barnes, Michael Kölling, Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (3)
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
