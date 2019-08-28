package jUnitTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Inventory;
import seng202.team3.parsing.InventoryLoader;
import seng202.team3.util.UnitType;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Unit tests for loading Ingredients data in JAXB
 */

public class InventoryLoaderTest {
    private Inventory testInventory;

    @Before
    public void testLoadIngredientsXML() throws JAXBException {
        String fName = "./resources/data/Ingredients.xml";
        int numExpected = 30;
        String pathName = "";

        try {
            pathName = (new File(fName)).toURI().toURL().toString();
        } catch (IOException ioe) {
            System.err.println("Problem reading file: <" + fName + ">  Check for typos");
            System.err.println(ioe);
            System.exit(666);// a bit brutal!
        }

        InventoryLoader testLoader = new InventoryLoader();
        testInventory = testLoader.readIngredientsFile(fName);
        List<Ingredient> ingredients = testInventory.getIngredients();

        assertEquals("All XML ingredients record should be added", numExpected, ingredients.size());
    }

    @Test
    public void testKnownValues() {
        //Ingredient testIngredient = new Ingredient("Salt", "Table Salt", 0, UnitType.ML, ThreeValueLogic.UNKNOWN, ThreeValueLogic.UNKNOWN, ThreeValueLogic.UNKNOWN);
        assertEquals("Known ingredient in ingredient list", testInventory.getIngredients().get(0).getUnit(), UnitType.ML);
    }
}
