package jUnitTests;

import org.junit.Test;
import seng202.team3.model.Supplier;

import static junit.framework.TestCase.assertEquals;

public class SupplierTest {

    @Test
    public void firstTest(){
        Supplier supplier = new Supplier("1", "Countdown", "20 Riccarton Road", "2330930");
        assertEquals(supplier.getName(), "Countdown");

    }
}
