package jUnitTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Business;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Inventory;
import seng202.team3.util.UnitType;
import seng202.team3.view.BusinessApp;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.*;

public class InventoryTest {

    private HashMap<String, Ingredient> inventoryHashMap;
    private Inventory inventory;
    private Ingredient BBun;
    private Ingredient Cheese;
    private Ingredient TrimMilk;


    @Before
    public void setup() {
        inventoryHashMap = new HashMap<>();
        inventory = new Inventory("I'm a description", inventoryHashMap);

        BBun = new Ingredient("BBun", "Hamburger bun", 0f, UnitType.COUNT, 10);
        Cheese = new Ingredient("Cheese", "Cheddar cheese slice", 0f, UnitType.COUNT, 10);
        TrimMilk = new Ingredient("TrimMilk", "Trim Milk", 9f, UnitType.ML, 10);

    }

    /**
     * This tests the addIngredient and removeIngredient methods
     */
    @Test
    public void addAndRemoveIngredientTest() {
        inventory.addIngredient(BBun);
        inventory.addIngredient(Cheese);
        inventory.addIngredient(TrimMilk);

        assertTrue(inventory.searchStock(BBun));
        assertTrue(inventory.searchStock(Cheese));

        inventory.removeIngredient(Cheese.getCode());
        assertFalse(inventory.searchStock(Cheese));
    }

    /**
     * Tests the modifyStock method
     */
    @Test
    public void modifyStockTest() {
        inventory.addIngredient(TrimMilk);
        TrimMilk.setCost(20, TrimMilk.getQuantity());
        inventory.modifyIngredient(TrimMilk);

        for (Map.Entry<String, Ingredient> entry : inventoryHashMap.entrySet()) {
            if (entry.getValue() == TrimMilk) {
                assertEquals(15f, entry.getValue().getCost());
            }
        }
    }

    /**
     * Tests compareAndAddIngredint method
     */
    @Test
    public void compareAndAddIngredientTest() {
        inventory.getIngredients().put(BBun.getCode(), BBun);
        inventory.getIngredients().put(Cheese.getCode(), Cheese);
        inventory.getIngredients().put(TrimMilk.getCode(), TrimMilk);

        HashMap<String, Ingredient> newIngredients = new HashMap<String, Ingredient>();
        newIngredients.put(BBun.getCode(), BBun);
        newIngredients.put(Cheese.getCode(), Cheese);
        newIngredients.put(TrimMilk.getCode(), TrimMilk);

        inventory.compareAndAddIngredient(newIngredients);

        assertEquals(18f, inventory.getIngredients().get(TrimMilk.getCode()).getQuantity());

    }


    /**
     * This tests the add and remove stock methods
     */
    @Test
    public void addAndRemoveStockTest() {
        inventory.addStock(BBun, 100f);
        for (Map.Entry<String, Ingredient> entry : inventoryHashMap.entrySet()) {
            if (entry.getValue() == BBun) {
                assertEquals(100f, entry.getValue().getQuantity());
            }
        }

        inventory.removeStock(BBun, 50f);
        for (Map.Entry<String, Ingredient> entry : inventoryHashMap.entrySet()) {
            if (entry.getValue() == BBun) {
                assertEquals(50f, entry.getValue().getQuantity());
            }
        }
    }

    /**
     * tests that the check stock method returns the correct HashMap
     */
    @Test
    public void checkStockTest() {
        HashMap<Ingredient, Float> testStock = new HashMap<>();
        testStock.put(BBun, 10f);
        testStock.put(Cheese, 20f);
        testStock.put(TrimMilk, 9f);

        inventory.addStock(BBun, 10f);
        inventory.addStock(Cheese, 20f);
        inventory.addIngredient(BBun);
        inventory.addIngredient(Cheese);
        inventory.addIngredient(TrimMilk);

        assertEquals(testStock, inventory.checkStock());
    }

    /**
     * Tests if the low stock report method returns the correct HashMap
     */
    @Test
    public void lowStockReportTest() {
        HashMap<Ingredient, Float> testStock = new HashMap<>();
        testStock.put(BBun, 10f);
        testStock.put(TrimMilk, 9f);

        inventory.addStock(BBun, 10f);
        inventory.addStock(Cheese, 20f);
        inventory.addIngredient(BBun);
        inventory.addIngredient(Cheese);
        inventory.addIngredient(TrimMilk);

        assertEquals(testStock, inventory.lowStockReport());
    }

    /**
     * Tests if the method to add ingredients from an XML file is working
     */
    @Test
    public void testAddIngredientFromXML() throws JAXBException {
        Business testBusiness = new Business("./src/main/resources/data/Ingredients.xml", BusinessApp.menuXML, BusinessApp.suppliersXML, BusinessApp.salesXML, BusinessApp.employeeXML, BusinessApp.truckXML);
        Inventory testInventory = testBusiness.getTruck().getInventory();
        assertEquals(31, testInventory.getIngredients().size());


        testInventory.addIngredientsFromXML("./src/main/resources/data/testdata/testIngredients1.xml");
        assertEquals(32, testInventory.getIngredients().size());
        assertTrue(testInventory.getIngredients().keySet().contains("OSauce"));
    }

    /**
     * Tests if compareAndAdd function is updating HashMap of ingredients accordingly
     */
    @Test
    public void testCompareAndAdd() throws JAXBException {
        Business testBusiness = new Business("./src/main/resources/data/Ingredients.xml", BusinessApp.menuXML, BusinessApp.suppliersXML, BusinessApp.salesXML, BusinessApp.employeeXML, BusinessApp.truckXML);
        Inventory testInventory = testBusiness.getTruck().getInventory();
        Float saltQuantity = testInventory.getIngredients().get("Salt").getQuantity();
        assertEquals(500f, saltQuantity);

        testInventory.addIngredientsFromXML("./src/main/resources/data/Ingredients.xml");
        Float newSaltQuantity = testInventory.getIngredients().get("Salt").getQuantity();
        assertEquals((float) saltQuantity + 500, newSaltQuantity);
    }
}

