package seng202.team3;


import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;

/**
 * @Author Cheng Yi Kok
 *
 * Dummy test case to fulfill set up check list
 */

public class CykTestCase
{
    private int result;


    @Test
    public void dummyTest() {
        result = 1 + 1;
        assertTrue( result == 2);
    }

    //Andrews test case
    @Test
    public void testForAndrew(){
        assertFalse(1 == 3);
    }

    /**
     * Hunter's Test Case
     */
    @Test
    public void testForHunter() {
        assertTrue(1 == 1);
    }

    @Test
    public void jeromesTest() { assertFalse(666 == 999); }
