package jUnitTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Ingredient;
import seng202.team3.model.MenuItem;
import seng202.team3.util.ItemType;
import seng202.team3.util.UnitType;
import seng202.team3.view.BusinessApp;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MenuItemTest {

    private Ingredient rice;
    private Ingredient carrot;
    private Ingredient peas;
    private Ingredient egg;
    private HashMap<Ingredient, Float> friedRiceIngredients;
    private MenuItem friedRice;

    @Before
    public void setup() {
        rice = new Ingredient("1", "Rice", 1f, UnitType.GRAM, 0.001f);
        carrot = new Ingredient("2", "Carrot", 2f, UnitType.COUNT, 0.01f);
        peas = new Ingredient("3", "Peas", 3f, UnitType.GRAM, 0.01f);
        egg = new Ingredient("4", "Rice", 4f, UnitType.GRAM, 1f);

        friedRiceIngredients = new HashMap<>();
        friedRiceIngredients.put(rice, 200f);
        friedRiceIngredients.put(carrot, 50f);
        friedRiceIngredients.put(peas, 50f);
        friedRice = new MenuItem("1", "Fried rice", friedRiceIngredients, ItemType.MAIN);
    }


    /**
     * Tests the construction of a basic menu item
     */
    @Test
    public void menuItemCreationTest() {
        MenuItem friedRice = new MenuItem("1", "Fried rice", friedRiceIngredients, ItemType.MAIN);
        assertEquals(friedRice.getId(), "1");
        assertEquals(friedRice.getName(), "Fried rice");
        assertEquals(friedRice.getIngredients(), friedRiceIngredients);
        assertEquals(friedRice.getType(), ItemType.MAIN);

    }

    /**
     * Tests hasEnoughStock method
     * Will return false, as inventory does not have the ingredients to make fried rice
     */
    @Test
    public void hasEnoughStockTest() {
        assertFalse(friedRice.hasEnoughStock());
    }

    @Test
    public void decreaseStockTest() {
        Ingredient tsauce = new Ingredient("TSauce", "Tomato Sauce", 1500, UnitType.ML, 1.8f);
        HashMap<Ingredient, Float> testItemIngredient = new HashMap<Ingredient, Float>();
        testItemIngredient.put(tsauce, 10f);
        MenuItem testItem = new MenuItem("1", "Testing", testItemIngredient, ItemType.MAIN);
        Float initialQtt = BusinessApp.getBusiness().getTruck().getInventory().getIngredients().get("TSauce").getQuantity();
        testItem.decreaseStock();
        Float finalQtt = BusinessApp.getBusiness().getTruck().getInventory().getIngredients().get("TSauce").getQuantity();
        assertEquals(initialQtt - 10f, finalQtt);

    }



    /**
     * Checks if the method to calculate the cost price of a recipe is working as expected
     */
    @Test
    public void calculateCostToMakeTest(){
       assertEquals(friedRice.getCostPriceFromIngredients(), 1.2f);
       friedRice.addIngredientToRecipe(egg, 1f);
       assertEquals(friedRice.getCostPriceFromIngredients(), 2.2f);
    }

    @Test
    /**
     * Tests the functionality of calculating the sales price of an item based on its cost price and markup
     */
    public void getSalePriceTest(){
        assertEquals(friedRice.calculateSalePrice(), 1.32f);
    };


    /**
     * Tests checking the quantity of an ingredient within a recipe with expected and unexpected values.
     */
    @Test
    public void ingredientQuantityInOrderTest(){
        assertEquals(friedRice.getRecipeQuantity(peas.getCode()), 50f);
        assertEquals(friedRice.getRecipeQuantity(egg.getCode()), 0f);
    }




}
