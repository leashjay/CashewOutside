package jUnitTests;

import org.junit.Test;
import seng202.team3.model.Supplier;
import seng202.team3.util.PhoneType;

import static junit.framework.TestCase.assertEquals;

public class SupplierTest {

    @Test
    public void firstTest(){
        Supplier supplier = new Supplier("1", "Countdown", "20 Riccarton Road", PhoneType.WORK, "9599999", "sadasd@gmail.com", "2330930");
        assertEquals(supplier.getName(), "Countdown");

    }
}
