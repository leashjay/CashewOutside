package jUnitTests;

import org.junit.Test;
import seng202.team3.model.Supplier;
import seng202.team3.util.PhoneType;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class SupplierTest {

    /**
     * Tests the constructor of a new supplier creation of a new supplier
     */
    @Test
    public void supplierCreationTest(){
        Supplier supplier = new Supplier("1", "Countdown", "20 Riccarton Road", PhoneType.WORK, "9599999", "countdown@gmail.com", "www.countdown.com");
        assertEquals(supplier.getName(), "Countdown");
        assertEquals(supplier.getSid(), "1");
        assertEquals(supplier.getAddress(), "20 Riccarton Road");
        assertEquals(supplier.getPhoneType(), PhoneType.WORK);
        assertEquals(supplier.getPhoneNumber(), "9599999");
        assertEquals(supplier.getEmail(), "countdown@gmail.com");
        assertEquals(supplier.getUrl(), "www.countdown.com");
    }

    /**
     * Tests the modification of a suppliers attributes
     */
    @Test
    public void supplierModificationTest(){
        Supplier supplier = new Supplier("1", "Countdown", "20 Riccarton Road", PhoneType.WORK, "9599999", "countdown@gmail.com", "www.countdown.com");
        supplier.setAddress("13 Church Corner");
        assertEquals(supplier.getAddress(), "13 Church Corner");
        assertNotEquals(supplier.getAddress(), "20 Riccarton Road");
        supplier.setPhoneNumber("080012345");
        assertEquals(supplier.getPhoneNumber(), "080012345");
        assertNotEquals(supplier.getPhoneNumber(), "9599999");

    }
}
