package loaderTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Menu;
import seng202.team3.model.MenuItem;
import seng202.team3.parsing.MenuLoader;
import seng202.team3.util.UnitType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static seng202.team3.util.ItemType.ASIAN;

public class MenuLoaderTest {
    private MenuLoader testLoader;
    private Menu testMenu;
    private HashMap<String, MenuItem> menuContent;
    private MenuItem item;

    /**
     * Make sure each test starts with a clean copy of the loaded data - in case
     * previous tests have side-effects.
     */
    @Before
    public void testLoadMenuFile() throws Exception {
        String fName = "./src/main/resources/data/SampleMenu.xml";
        int numExpected = 6;
        testLoader = new MenuLoader();
        testMenu = testLoader.loadMenuData(fName);
        menuContent = testMenu.filterMenuItems();

        assertEquals("All XML ingredients record should be added", numExpected, menuContent.size());
    }

    //TODO Rewrite this test with new Ingredients implementation.
    /**
     * Way too many assertions in this test, but you get the idea...
     */
    @Test
    public void testBabyFace() {
        assertNotNull("Baby face is in the sample testMenu", menuContent.get("BF"));
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

    @Test
    public void testExportDBtoXML() throws Exception {
        HashMap<Ingredient, Float> testIngredients = new HashMap<Ingredient, Float>();
        Ingredient Kimchi = new Ingredient();
        Kimchi.setCode("Kimchi");
        Kimchi.setUnit(UnitType.GRAM);
        testIngredients.put(Kimchi, (float) 100);
        MenuItem newEntry = new MenuItem("KimchiStew", "A korean cuisine", testIngredients, ASIAN);
        menuContent.put("KimchiStew", newEntry);
        testLoader.exportMenuData("./src/main/resources/data/testdata/testExportMenu.xml", testMenu);


        menuContent.clear();
        assertEquals("Reset list of menu items", 0, menuContent.size());

        testMenu = testLoader.loadMenuData("./src/main/resources/data/testdata/testExportMenu.xml");
        menuContent = testMenu.filterMenuItems();
        assertEquals("All XML ingredients record should be added", 7, menuContent.size());
    }
}
