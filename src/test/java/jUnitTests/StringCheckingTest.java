package jUnitTests;

import org.junit.Test;
import seng202.team3.util.StringChecking;

import static junit.framework.TestCase.*;

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
}
