package jUnitTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Truck;

import javax.xml.bind.JAXBException;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class TruckTest {
    private Truck truck;

    @Before
    public void initialize() throws JAXBException {
        truck = new Truck("./resources/data/Ingredients.xml");
    }

    /**
     * Test the add denomination method
     */
    @Test
    public void testAddDenom() {
        truck.addDenom("NZD 100");
        assertTrue(truck.getCashFloat().get(10000) == 11);

        truck.addDenom("NZD 0");
        assertFalse(truck.getCashFloat().containsKey(0));
    }
}
