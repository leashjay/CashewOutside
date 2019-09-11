package jUnitTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Menu;
import seng202.team3.model.MenuItem;
import seng202.team3.parsing.MenuLoader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MenuLoaderTest {
    private Menu menu;
    private HashMap<String, MenuItem> menuContent;
    private MenuItem item;

    /**
     * Make sure each test starts with a clean copy of the loaded data - in case
     * previous tests have side-effects.
     */
    @Before
    public void testLoadMenuFile() throws Exception {
        String fName = "./resources/data/SampleMenu.xml";
        int numExpected = 6;
        String pathName = "";

        try {
            pathName = (new File(fName)).toURI().toURL().toString();
        } catch (IOException ioe) {
            System.err.println("Problem reading file: <" + fName + ">  Check for typos");
            System.err.println(ioe);
            System.exit(666);// a bit brutal!
        }

        MenuLoader testLoader = new MenuLoader();
        menu = testLoader.loadMenuData(fName);
        menuContent = menu.getMenuItem();

        assertEquals("All XML ingredients record should be added", numExpected, menuContent.size());
    }

    //TODO Rewrite this test with new Ingredients implementation.
    /**
     * Way too many assertions in this test, but you get the idea...
     */
    @Test
    public void testBabyFace() {
        assertNotNull("Baby face is in the sample menu", menuContent.get("BF"));
        item = menuContent.get("BF");
        Set<String> ingredientNames = new HashSet<String>();
        Set<Float> ingredientQuantities = new HashSet<Float>();
        for (Map.Entry<Ingredient, Float> entry : item.getIngredients().entrySet()) {
            ingredientNames.add(entry.getKey().getCode());
            ingredientQuantities.add(entry.getValue());
        }
        assertTrue("Made with vodka", ingredientNames.contains("Vodka"));
        assertTrue("Made with cassis", ingredientNames.contains("Cassis"));
        assertTrue("Made with cream", ingredientNames.contains("Cream"));
        assertEquals("Made from three things", 3, item.getIngredients().size());
        assertFalse("Not made with Beetroot", ingredientNames.contains("BeetRoot"));

        assertEquals("All ingredients in BabyFace are of size 30ml", 1, ingredientQuantities.size());
    }
}
