package loaderTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Inventory;
import seng202.team3.parsing.InventoryLoader;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for loading Ingredients data in JAXB
 */
public class InventoryLoaderTest {
    private InventoryLoader testLoader;
    private String fName;
    private Inventory testInventory;
    private HashMap<String, Ingredient> ingredients;



    @Before
    public void testLoadIngredientsXML() throws Exception {
        fName = "./src/main/resources/data/Ingredients.xml";
        int numExpected = 31;
        String pathName = "";
        testLoader = new InventoryLoader();
        testInventory = testLoader.loadIngredientsData(fName);
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


    @Test
    public void testOptionalFields() {
        String aCode = "Beetroot";
        assertTrue("Key in keylist", ingredients.keySet().contains(aCode));
        Ingredient i = ingredients.get(aCode);
        assertNotNull("Corresponding object in collection", i);
        assertEquals("It's a beetroot slice, isn't it?", "Beetroot slice", i.getName());
        assertEquals("Beetroot is gluten free", ThreeValueLogic.YES, i.getIsGlutenFree());
    }

    @Test
    public void testDefaultAttribute() {
        String aCode = "Mayo";
        assertTrue("Key in keylist", ingredients.keySet().contains(aCode));
        Ingredient i = ingredients.get(aCode);
        assertNotNull("Corresponding object in collection", i);
        assertEquals("It's Eater plain Mayonnaise, isn't it?", "Eater plain Mayonnaise", i.getName());
        assertEquals("No idea whether mayo is gluten free", ThreeValueLogic.UNKNOWN, i.getIsGlutenFree());
        assertEquals("How much does mayo cost?", (float) 1.1, i.getCost());
    }

    @Test
    public void testExportDBtoXML() throws Exception {
        Ingredient newEntry = new Ingredient("Kimchi", "chikim", 3f, UnitType.GRAM, 1.2f);
        testInventory.getIngredients().put(newEntry.getCode(), newEntry);
        testLoader.exportIngredientsData("./src/main/resources/data/testdata/testExportIngredients.xml", testInventory);


        ingredients.clear();
        assertEquals("Reset list of ingredients", 0, ingredients.size());

        testInventory = testLoader.loadIngredientsData("./src/main/resources/data/testdata/testExportIngredients.xml");
        ingredients = testInventory.getIngredients();
        assertEquals("All XML ingredients record should be added", 32, ingredients.size());
    }


}
