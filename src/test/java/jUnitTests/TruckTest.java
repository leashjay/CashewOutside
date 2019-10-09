package jUnitTests;

import org.junit.Before;
import seng202.team3.model.Truck;

import javax.xml.bind.JAXBException;

public class TruckTest {
    private Truck truck;

    @Before
    public void initialize() throws JAXBException {
        truck = new Truck("./src/main/resources/data/Ingredients.xml");
    }


}
