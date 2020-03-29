/**
 * A class for storing the coronavirus cases
 * in each borough of London
 * @author Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (3)
 */
public class BoroughCoronaCases {

    private String borough;
    private double cases;

    public BoroughCoronaCases(String borough, double cases) {
        this.borough = borough;
        this.cases = cases;
    }

    /**
     * @return The name of this borough
     */
    public String getBoroughName() {
        return borough;
    }

    /**
     * @return The number of cases in this borough (of type String)
     */
    public double getCases() {
        return cases;
    }
}
