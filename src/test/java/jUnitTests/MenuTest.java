package jUnitTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Business;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Menu;
import seng202.team3.model.MenuItem;
import seng202.team3.util.ItemType;
import seng202.team3.util.MenuType;
import seng202.team3.util.UnitType;
import seng202.team3.view.BusinessApp;

import javax.xml.bind.JAXBException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class MenuTest {
    private Ingredient rice;
    private MenuItem justRice;
    private HashMap<Ingredient, Float>  riceStuff;
    private Menu notOnlyRice;

    private Ingredient wings;
    private Ingredient beans;
    private MenuItem justWings;
    private HashMap<Ingredient, Float>  wingsStuff;

    private HashMap<String, MenuItem> menuContents;

    @Before
    public void setup() {

        menuContents = new HashMap<String, MenuItem>();
        rice = new Ingredient("1", "Rice", 1f, UnitType.GRAM, 0.001f);
        riceStuff = new HashMap<>();
        riceStuff.put(rice, 200f);
        justRice = new MenuItem("1", "Fried rice", riceStuff, ItemType.MAIN);
        menuContents.put(justRice.getId(),justRice);

        wings = new Ingredient("12", "Wings", 1f, UnitType.COUNT, 1f);
        beans = new Ingredient("14", "Beans", 1f, UnitType.COUNT, 1f);
        wingsStuff = new HashMap<>();
        wingsStuff.put(wings, 6f);
        justWings = new MenuItem("12", "Wings", wingsStuff, ItemType.GRILL);

        notOnlyRice = new Menu("Not Only Rice", "More than rice", MenuType.FESTIVAL, menuContents);
    }

    @Test
    public void menuCreationTest() {

            assertEquals(notOnlyRice.getTitle(), "Not Only Rice");
            assertEquals(notOnlyRice.getDesc(), "More than rice");
            assertEquals(notOnlyRice.getMenuType(), MenuType.FESTIVAL);
            assertEquals(notOnlyRice.getMenuItem(), menuContents);
    }

    @Test
    public void menuManipulationTest() {
        notOnlyRice.addMenuItem(justWings);
        menuContents.put(justWings.getId(), justWings);
        assertEquals(notOnlyRice.getMenuItem(), menuContents);
    }

    /**
     * Tests if the method to add menu item from an XML file is working
     */
    @Test
    public void testAddMenuItemFromXML() throws JAXBException {
        Business testBusiness = new Business(BusinessApp.ingredientsXML, "./src/main/resources/data/SampleMenu.xml", BusinessApp.suppliersXML, BusinessApp.salesXML, BusinessApp.employeeXML, BusinessApp.truckXML);
        Menu testMenu = testBusiness.getMenuManager();
        assertEquals(6, testMenu.getMenuItem().size());


        testMenu.addMenuItemFromXML("./src/test/resources/data/testdata/testMenu1.xml");
        assertEquals(7, testMenu.getMenuItem().size());
        assertTrue(testMenu.getMenuItem().keySet().contains("KS1"));
    }

    @Test
    public void ingredientDeletionTest(){
        notOnlyRice.addMenuItem(justWings);
        HashMap<Ingredient, Float> chickenOnRiceIngredients = new HashMap<>();
        chickenOnRiceIngredients.put(wings, 6f);
        chickenOnRiceIngredients.put(rice, 6f);
        MenuItem chickenOnRice = new MenuItem("12", "Wings", chickenOnRiceIngredients, ItemType.GRILL);
        notOnlyRice.addMenuItem(chickenOnRice);
        assertEquals(notOnlyRice.removeIngredientFromMenuItems(rice), 2);
        assertEquals(notOnlyRice.removeIngredientFromMenuItems(rice), 0);
        assertEquals(notOnlyRice.removeIngredientFromMenuItems(beans), 0);

    }

}
