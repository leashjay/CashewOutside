package jUnitTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Inventory;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;

import static junit.framework.TestCase.*;

public class IngredientTest {

    private Ingredient baklava;
    private Ingredient flour;
    private Ingredient coke;
    private Ingredient tomato;

    @Before
    public void setup(){
         flour = new Ingredient("6", "Flour", 12.1f, UnitType.GRAM, 10f);
         tomato = new Ingredient("7", "Tomato", 12.1f, UnitType.COUNT, 10f);
         coke = new Ingredient("8", "coke", 12f, UnitType.ML, 5f);
         baklava = new Ingredient("9", "baklava", 13f, UnitType.UNKNOWN, 15f);

    }

    /**
     * Tests the constructor for an ingredient item
     */
    @Test
    public void ingredientConstructorTest() {
        Ingredient ingredient = new Ingredient("4", "Pasta", 12.1f, UnitType.COUNT, 10f);
        assertEquals(ingredient.getCode(), "4");
        assertEquals(ingredient.getName(), "Pasta");
        assertEquals(ingredient.getUnit(), UnitType.COUNT);
        assertEquals(ingredient.getQuantity(), 12.1f);
        assertEquals(ingredient.getIsVegetarian(), ThreeValueLogic.UNKNOWN);
        assertEquals(ingredient.getIsVegan(), ThreeValueLogic.UNKNOWN);
        assertEquals(ingredient.getIsGlutenFree(), ThreeValueLogic.UNKNOWN);
        assertEquals(ingredient.getCost(), 10f);
    }

    /**
     * Tests setters and getters for an ingredient item
     */
    @Test
    public void ingredientSetterTest() {
        Ingredient ingredient = new Ingredient("4", "Pasta", 12.1f, UnitType.COUNT, 10f);
        ingredient.setIsVegan(ThreeValueLogic.NO);
        ingredient.setCost(999, ingredient.getQuantity());
        ingredient.setQuantity(1000);
        assertEquals(ingredient.getIsVegan(), ThreeValueLogic.NO);
        assertEquals(ingredient.getCost(), 504.5f);
        assertEquals(ingredient.getQuantity(), 1000f);
    }

    /**
     * Tests the boundary values for the low stock functionality for ingredients with unit MLS.
     */
    @Test
    public void lowStockMlsTest(){
        coke.setQuantity(Inventory.LOWSTOCKML + 1);
        assertFalse(coke.isLowStock());
        coke.setQuantity(Inventory.LOWSTOCKML);
        assertFalse(coke.isLowStock());
        coke.setQuantity(Inventory.LOWSTOCKML - 1);
        assertTrue(coke.isLowStock());
    }

    /**
     * Tests the the boundary values for the low stock functionality for ingredients with unit unknown
     */
    @Test
    public void lowStockUnknownTest(){
        baklava.setQuantity(Inventory.LOWSTOCKUNKNOWN + 1);
        assertFalse(baklava.isLowStock());
        baklava.setQuantity(Inventory.LOWSTOCKUNKNOWN);
        assertFalse(baklava.isLowStock());
        baklava.setQuantity(Inventory.LOWSTOCKUNKNOWN - 1);
        assertTrue(coke.isLowStock());
    }

    /**
     * Tests the the boundary values for the low stock functionality for ingredients with the unit grams
     */
    @Test
    public void lowStockGramsTest(){
        flour.setQuantity(Inventory.LOWSTOCKGRAMS + 1);
        assertFalse(flour.isLowStock());
        flour.setQuantity(Inventory.LOWSTOCKGRAMS);
        assertFalse(flour.isLowStock());
        flour.setQuantity(Inventory.LOWSTOCKGRAMS - 1);
        assertTrue(flour.isLowStock());
    }

    /**
     * Tests the the boundary values for  low stock functionality for ingredients with the unit count.
     */
    @Test
    public void lowStockCountTest(){
        tomato.setQuantity(Inventory.LOWSTOCKCOUNT + 1);
        assertFalse(tomato.isLowStock());
        tomato.setQuantity(Inventory.LOWSTOCKCOUNT);
        assertFalse(tomato.isLowStock());
        tomato.setQuantity(Inventory.LOWSTOCKCOUNT - 1);
        assertTrue(tomato.isLowStock());
    }

    /**
     * Test to check the functionality of getting a unit type from a string value works with the expected case
     */
    @Test
    public void convertToUnitTypeTestExpected(){
        String unitTypeString = "COUNT";
        assertEquals(tomato.convertToUnit(unitTypeString), UnitType.COUNT);
    }

    /**
     * Tests to check whether the functionality of getting the unit type works with a boundary case (lowercase letters rather than capital)
     */
    @Test
    public void convertUnitTypeTestBoundary(){
        String unitTypeString = "count";
        assertNotSame(tomato.convertToUnit(unitTypeString), UnitType.COUNT);
        assertEquals(tomato.convertToUnit(unitTypeString), UnitType.UNKNOWN);
    }

    @Test
    public void convertUnitTypeTestExpectional(){
        String unitTypeString = "a;dkfska";
        assertEquals(tomato.convertToUnit(unitTypeString), UnitType.UNKNOWN);
    }

    /**
     * Test to see whether changing the quantity of an item works as expected. Tests check boundary around 0.
     */
    @Test
    public void decreaseQuantityTest(){
        baklava.setQuantity(10f);
        baklava.decreaseQuantity(9);
        assertEquals(baklava.getQuantity(), 1f);
        baklava.decreaseQuantity(1f);
        assertEquals(baklava.getQuantity(), 0f);
    }

    /**
     * Asserts an error is thrown when the quantity of an ingredient is decreased below zero
     */
    @Test(expected = Error.class)
    public void decreaseQuantityBelowZeroTest(){
        baklava.setQuantity(1f);
        baklava.decreaseQuantity(2f);
    }

    /**
     * Test to see whether calculating the new average price of an item when receiving stock works as expected
     */
    public void setCostTest(){}



}
