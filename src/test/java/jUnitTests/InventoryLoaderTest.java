package jUnitTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Inventory;
import seng202.team3.parsing.InventoryLoader;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for loading Ingredients data in JAXB
 */

public class InventoryLoaderTest {
    private Inventory testInventory;
    HashMap<String, Ingredient> ingredients;

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
        ingredients = testInventory.getIngredients();

        assertEquals("All XML ingredients record should be added", numExpected, ingredients.size());
    }

    @Test
    public void testKnownValues() {
        //Ingredient testIngredient = new Ingredient("Salt", "Table Salt", 0, UnitType.ML, ThreeValueLogic.UNKNOWN, ThreeValueLogic.UNKNOWN, ThreeValueLogic.UNKNOWN);
        String aCode = "BBun";
        assertTrue("Key in keylist", ingredients.keySet().contains(aCode));
        Ingredient i = ingredients.get(aCode);
        assertNotNull("Corresponding object in collection", i);
        assertEquals("It's a burger bun, isn't it?", "Hamburger bun", i.getName());
    }
}
