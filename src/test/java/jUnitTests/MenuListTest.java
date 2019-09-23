package jUnitTests;

import org.junit.Before;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Menu;
import seng202.team3.model.MenuItem;
import seng202.team3.util.ItemType;
import seng202.team3.util.MenuType;
import seng202.team3.util.UnitType;

import java.util.HashMap;

public class MenuListTest {

    private Ingredient rice;
    private MenuItem justRice;
    private HashMap<Ingredient, Float>  riceStuff;
    private Menu notOnlyRice;

    private HashMap<String, MenuItem> menuContents;

    private Menu summerFun;
    private Menu summerFunMK2;
    private Menu winterFun;
    private Menu winterFunMK2;
    private Menu Festival;
    private Menu Festival2;

    //Menu(String title, String desc, MenuType menuType, HashMap<String, MenuItem> menuContent)
    @Before
    public void setup() {
        rice = new Ingredient("1", "Rice", 1f, UnitType.GRAM, 0.001f);
        riceStuff = new HashMap<>();
        riceStuff.put(rice, 200f);
        justRice = new MenuItem("1", "Fried rice", riceStuff, ItemType.MAIN);
        menuContents.put(justRice.getId(),justRice);
        notOnlyRice = new Menu("Not Only Rice", "More than rice", MenuType.FESTIVAL, menuContents);
    }
}
