package jUnitTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Inventory;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.*;

public class InventoryTests {

    private HashMap<String, Ingredient> inventoryHashMap;
    private Inventory inventory;
    private Ingredient BBun;
    private Ingredient Cheese;
    private Ingredient TrimMilk;


    @Before
    public void setup() {
        inventoryHashMap = new HashMap<>();
        inventory = new Inventory("I'm a description", inventoryHashMap);

        BBun = new Ingredient("BBun", "Hamburger bun", UnitType.COUNT, ThreeValueLogic.YES, ThreeValueLogic.YES, ThreeValueLogic.NO, 10);
        Cheese = new Ingredient("Cheese", "Cheddar cheese slice", UnitType.COUNT, ThreeValueLogic.YES, ThreeValueLogic.NO, ThreeValueLogic.YES, 10);
        TrimMilk = new Ingredient("TrimMilk", "Trim Milk", UnitType.ML, ThreeValueLogic.YES, ThreeValueLogic.NO, ThreeValueLogic.YES, 10);

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
        TrimMilk.setIsVegan(ThreeValueLogic.YES);
        inventory.modifyIngredient(TrimMilk);

        for (Map.Entry<String, Ingredient> entry : inventoryHashMap.entrySet()) {
            if (entry.getValue() == TrimMilk) {
                assertEquals(ThreeValueLogic.YES, entry.getValue().getIsVegan());
            }
        }
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
        testStock.put(TrimMilk, 0f);

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
        testStock.put(TrimMilk, 0f);

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
        assertEquals(0, inventory.getIngredients().size());
        inventory.addIngredientsFromXML("./resources/data/Ingredients.xml");
        assertEquals(30, inventory.getIngredients().size());
        assertTrue(inventory.getIngredients().keySet().contains("BBun"));
    }
}

