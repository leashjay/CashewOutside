package jUnitTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Truck;

import javax.xml.bind.JAXBException;

import static junit.framework.TestCase.*;

public class TruckTest {
    private Truck truck;

    /**
     * Setup truck and truck inventory and set up cash account
     *
     * @throws JAXBException
     */
    @Before
    public void initialize() throws JAXBException {
        truck = new Truck();
        truck.createTruckInventory("./src/main/resources/data/Ingredients.xml");
        truck.setCashAccount(500f);
    }

    /**
     * Test for increaseCashFloat method
     */
    @Test
    public void increaseCashFloatTest() {
        truck.increaseCashFloat(100f);

        assertEquals(600f, truck.getCashAccount());
    }

    /**
     * Test on decreaseCashFloat method
     */
    @Test
    public void decreaseCashFloatTest() {
        truck.decreaseCashFloat(100f);
        assertEquals(400f, truck.getCashAccount());

        String errorMessage = "";
        try {
            truck.decreaseCashFloat(500f);
        } catch (Error e) {
            errorMessage += e;
        }

        assertTrue(errorMessage.length() != 0);
    }

    /**
     * Test on hasEnoughCash method
     */
    @Test
    public void hasEnoughCashTest() {
        assertFalse(truck.hasEnoughCash(600f));
        assertTrue(truck.hasEnoughCash(100f));
    }




}
