package jUnitTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Menu;
import seng202.team3.model.MenuItem;
import seng202.team3.model.MenuList;
import seng202.team3.util.ItemType;
import seng202.team3.util.MenuType;
import seng202.team3.util.UnitType;

import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class MenuListTest {

    private Ingredient rice;
    private MenuItem justRice;
    private HashMap<Ingredient, Float>  riceStuff;

    private HashMap<String, MenuItem> menuContents;

    private Menu summerFun;
    private Menu winterFun;
    private Menu festival;
    private Menu festival2;
    private ArrayList<Menu> menuArrayList;
    private ArrayList<Menu> summerMenus;
    private ArrayList<Menu> festivalMenus;
    private ArrayList<Menu> winterMenus;
    private MenuList menus;

    /**
     * sets up and initialises all values needed for testing by making the ArrayLists of Menus and puting them in the
     * MenuList constructor and then also consequently adding and removing from these ArrayLists to compare against the
     * MenuList class's one
     * NOTE: this is a large method due to 'nested typing'
     */
    @Before
    public void setup() {
        rice = new Ingredient("1", "Rice", 1f, UnitType.GRAM, 0.001f);
        riceStuff = new HashMap<>();
        riceStuff.put(rice, 200f);
        justRice = new MenuItem("1", "Fried rice", riceStuff, ItemType.MAIN);
        menuContents = new HashMap<>();
        menuContents.put(justRice.getId(),justRice);

        festival = new Menu("Big Festival", "for RNV + RNA goers", MenuType.FESTIVAL, menuContents);
        winterFun =  new Menu("Chilly chilly", "Ski time", MenuType.WINTER, menuContents);
        festival2 = new Menu("Party time", "for any odd drunk person", MenuType.FESTIVAL, menuContents);
        summerFun = new Menu("Chilly chilly", "Ski time", MenuType.SUMMER, menuContents);
        menuArrayList = new ArrayList<>();
        summerMenus = new ArrayList<>();
        winterMenus = new ArrayList<>();
        festivalMenus = new ArrayList<>();
        menuArrayList.add(festival);
        menuArrayList.add(winterFun);
        menuArrayList.add(summerFun);
        festivalMenus.add(festival);
        summerMenus.add(summerFun);
        winterMenus.add(winterFun);

        menus = new MenuList(menuArrayList);
    }

    /**
     * Tests the construction and getters of the MenuList class
     */
    @Test
    public void gettersAndInitTest() {
        assertEquals(menus.getMenus(), menuArrayList);
        assertEquals(menus.getFestivalMenus(), festivalMenus);
        assertEquals(menus.getSummerMenus(), summerMenus);
        assertEquals(menus.getWinterMenus(), winterMenus);
    }

    /**
     * Tests the adding and removal of items into the MenuList class
     */
    @Test
    public void addersAndRemoversTest() {
        menus.addMenu(festival2);
        menuArrayList.add(festival2);
        festivalMenus.add(festival2);
        assertEquals(menus.getMenus(), menuArrayList);
        //assertEquals(menus.getFestivalMenus(), festivalMenus);
        assertEquals(menus.getWinterMenus(), winterMenus);
        assertEquals(menus.getSummerMenus(), summerMenus);
        menus.removeMenu(festival);
        menuArrayList.remove(festival);
        assertEquals(menus.getMenus(), menuArrayList);
    }
}
