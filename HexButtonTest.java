

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class HexButtonTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
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
    public void testBoroughName()
    {
        HexButton hexButto2 = new HexButton("test name");
        assertSame("test name", hexButto2.getBoroughName());
    }

    @Test
    public void testButtonColour()
    {
        HexButton hexButto2 = new HexButton("button1");
        assertSame("-fx-background-color: #ff0000", hexButto2.buttonColourSetter());
    }

    @Test
    public void testPropertyAmount()
    {
        HexButton hexButto1 = new HexButton("testButton");
        assertEquals(0, hexButto1.numberOfProperties());
    }
}



