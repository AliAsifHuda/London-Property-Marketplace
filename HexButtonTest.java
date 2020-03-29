import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class HexButtonTest.
 *
 * @author David J. Barnes, Michael Kölling, Muhammad Abdullah k19037983, Ali Asif k19033243, 
 * Suleyman Ahmed k19036135 and Muhammad Shehzad k19018196
 * @version 2020.03.29 (2)
 */
public class HexButtonTest
{
    /**
     * Default constructor for test class HexButtonTest
     */
    public HexButtonTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    /**
     * checks whether the borough name that the button returns is correct or not.
     */
    public void testBoroughName()
    {
        HexButton hexButto2 = new HexButton("test name");
        assertSame("test name", hexButto2.getBoroughName());
    }

    @Test
    /**
     * checks whether the background colour that the button returns based on the
     * number of properties is correct or not.
     */
    public void testButtonColour()
    {
        HexButton hexButto2 = new HexButton("button1");
        assertSame("-fx-background-color: #ff0000", hexButto2.buttonColourSetter());
    }

    @Test
    /**
     * checks whether the number of properties in the button's borough that the 
     * button returns is correct or not
     */
    public void testPropertyAmount()
    {
        HexButton hexButto1 = new HexButton("testButton");
        assertEquals(0, hexButto1.numberOfProperties());
    }
}



