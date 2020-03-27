/**
 * A class for storing the coronavirus cases
 * in each borough of London
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
