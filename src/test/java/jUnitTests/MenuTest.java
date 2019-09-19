package jUnitTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Menu;
import seng202.team3.model.MenuItem;
import seng202.team3.util.ItemType;
import seng202.team3.util.MenuType;
import seng202.team3.util.UnitType;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class MenuTest {
    private Ingredient rice;
    private MenuItem justRice;
    private HashMap<Ingredient, Float>  riceStuff;
    private Menu notOnlyRice;

    private Ingredient wings;
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

}
