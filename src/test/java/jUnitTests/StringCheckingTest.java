package jUnitTests;

import org.junit.Test;
import seng202.team3.util.StringChecking;

import static junit.framework.TestCase.*;

/**
 * email unit tests were taken from https://www.regextester.com/96927
 * This is to ensure we comply with email standards, and not just a few test cases I can think of.
 */

public class StringCheckingTest {

    @Test
    public void isFloatTest1() {
        assertTrue(StringChecking.isFloat("9"));
    }

    @Test
    public void isFloatTest2() {
        assertTrue(StringChecking.isFloat("0."));
    }
    @Test
    public void isFloatTest3() {
        assertTrue(StringChecking.isFloat(".0"));
    }

    @Test
    public void isFloatTest4() {
        assertTrue(StringChecking.isFloat("1.092"));
    }

    @Test
    public void isFloatTestNotPositive() {
        assertFalse(StringChecking.isFloat("-3"));
    }

    @Test
    public void isFloatTest5() {
        assertTrue(StringChecking.isFloat("+3"));
    }

    @Test
    public void isFloatTestNotAFloat1() {
        assertFalse(StringChecking.isFloat("."));
    }

    @Test
    public void isFloatTestNotAFloat2() {
        assertFalse(StringChecking.isFloat(""));
    }

    @Test
    public void isFloatTestNotAFloat3() {
        assertFalse(StringChecking.isFloat("1e+1"));
    }

    @Test
    public void isTwoDPFloatTest1() {
        assertTrue(StringChecking.isTwoDPFloat("9"));
    }

    @Test
    public void isTwoDPFloatTest2() {
        assertTrue(StringChecking.isTwoDPFloat("0."));
    }
    @Test
    public void isTwoDPFloatTest3() {
        assertTrue(StringChecking.isTwoDPFloat(".0"));
    }

    @Test
    public void isTwoDPFloatTest4() {
        assertTrue(StringChecking.isTwoDPFloat("1.09"));
    }

    @Test
    public void isTwoDPFloatTestNotPositive() {
        assertFalse(StringChecking.isTwoDPFloat("-3"));
    }

    @Test
    public void isTwoDPFloatTest6() {
        assertTrue(StringChecking.isTwoDPFloat("+3.01"));
    }

    @Test
    public void isTwoDPFloatTestNotAFloat1() {
        assertFalse(StringChecking.isTwoDPFloat("."));
    }

    @Test
    public void isTwoDPFloatTestNotAFloat2() {
        assertFalse(StringChecking.isTwoDPFloat(""));
    }

    @Test
    public void isTwoDPFloatTestNotAFloat3() {
        assertFalse(StringChecking.isTwoDPFloat("1e+1"));
    }

    @Test
    public void isTwoDPFloatTestNotAFloat4() {
        assertFalse(StringChecking.isTwoDPFloat("1.000"));
    }

    @Test
    public void isTwoDPFloatTestNotAFloat5() {
        assertFalse(StringChecking.isTwoDPFloat("+"));
    }

    @Test
    public void isTwoDPFloatTestNotAFloat6() {
        assertFalse(StringChecking.isTwoDPFloat("+."));
    }

    @Test
    public void isEmailTest1() {
        assertTrue(StringChecking.isEmail("mkyong@yahoo.com"));
    }

    @Test
    public void isEmailTest2() {
        assertTrue(StringChecking.isEmail("mkyong-100@yahoo.com"));
    }

    @Test
    public void isEmailTest3() {
        assertTrue(StringChecking.isEmail("mkyong.100@yahoo.com"));
    }

    @Test
    public void isEmailTest4() {
        assertTrue(StringChecking.isEmail("mkyong111@mkyong.com"));
    }

    @Test
    public void isEmailTest5() {
        assertTrue(StringChecking.isEmail("mkyong-100@mkyong.net"));
    }

    @Test
    public void isEmailTest6() {
        assertTrue(StringChecking.isEmail("mkyong.100@mkyong.com.au"));
    }

    @Test
    public void isEmailTest7() {
        assertTrue(StringChecking.isEmail("mkyong@1.com"));
    }

    @Test
    public void isEmailTest8() {
        assertTrue(StringChecking.isEmail("mkyong@gmail.com.com"));
    }

    @Test
    public void isEmailTest9() {
        assertTrue(StringChecking.isEmail("mkyong+100@gmail.com"));
    }

    @Test
    public void isEmailTest10() {
        assertTrue(StringChecking.isEmail("mkyong-100@yahoo-test.com"));
    }

    @Test
    public void isNotEmailTest1() {
        assertFalse(StringChecking.isEmail("mkyong"));
    }

    @Test
    public void isNotEmailTest2() {
        assertFalse(StringChecking.isEmail("mkyong@.com.my"));
    }

    @Test
    public void isNotEmailTest3() {
        assertFalse(StringChecking.isEmail("mkyong123@.com"));
    }

    @Test
    public void isNotEmailTest4() {
        assertFalse(StringChecking.isEmail("mkyong123@.com.com"));
    }

    @Test
    public void isNotEmailTest5() {
        assertFalse(StringChecking.isEmail(".mkyong@mkyong.com"));
    }

    @Test
    public void isNotEmailTest6() {
        assertFalse(StringChecking.isEmail("mkyong()*@gmail.com"));
    }

    @Test
    public void isNotEmailTest7() {
        assertFalse(StringChecking.isEmail("mkyong@%*.com"));
    }

    @Test
    public void isNotEmailTest8() {
        assertFalse(StringChecking.isEmail("mkyong..2002@gmail.com"));
    }

    @Test
    public void isNotEmailTest9() {
        assertFalse(StringChecking.isEmail("mkyong.@gmail.com"));
    }

    @Test
    public void isNotEmailTest10() {
        assertFalse(StringChecking.isEmail("mkyong@mkyong@gmail.com"));
    }
}
