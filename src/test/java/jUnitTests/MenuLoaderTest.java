package jUnitTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Menu;
import seng202.team3.model.MenuItem;
import seng202.team3.parsing.MenuLoader;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MenuLoaderTest {
    private Menu menu;
    private HashMap<String, MenuItem> menuContent;
    private MenuItem item;

    /**
     * Make sure each test starts with a clean copy of the loaded data - in case
     * previous tests have side-effects.
     */
    @Before
    public void testLoadMenuFile() throws JAXBException {
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

    /**
     * Way too many assertions in this test, but you get the idea...
     */
    @Test
    public void testBabyFace() {
        assertNotNull("Baby face is in the sample menu", menuContent.get("BF"));
        item = menuContent.get("BF");
        assertEquals("Made with vodka", item.getIngredients().get(0).getName(), "Vodka");
        assertEquals("Made with cassis", item.getIngredients().get(1).getName(), "Cassis");
        assertEquals("Made with cream", item.getIngredients().get(2).getName(), "Cream");
        assertEquals("Made from three things", 3, item.getIngredients().size());
//        assertFalse("Not made with Beetroot", item.getIngredients().contains("Beetroot"));
    }
}
