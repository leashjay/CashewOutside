package jUnitTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Ingredient;
import seng202.team3.model.MenuItem;
import seng202.team3.model.Supplier;
import seng202.team3.util.ItemType;
import seng202.team3.util.PhoneType;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class MenuItemTest {

    private Ingredient rice;
    private Ingredient carrot;
    private Ingredient peas;
    private Ingredient egg;
    private HashMap<Ingredient, Float> friedRiceIngredients;
    private MenuItem friedRice;

    @Before
    public void setup(){
        rice = new Ingredient("1", "Rice", UnitType.GRAM, ThreeValueLogic.YES, ThreeValueLogic.YES,
                ThreeValueLogic.YES, 0.001f);
        carrot = new Ingredient("2", "Carrot", UnitType.COUNT, ThreeValueLogic.YES, ThreeValueLogic.YES,
                ThreeValueLogic.YES, 0.01f);
        peas = new Ingredient("3", "Peas", UnitType.GRAM, ThreeValueLogic.YES, ThreeValueLogic.YES,
                ThreeValueLogic.YES, 0.01f);
        egg = new Ingredient("4", "Rice", UnitType.GRAM, ThreeValueLogic.NO, ThreeValueLogic.YES,
                ThreeValueLogic.YES, 1f);

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
     * Checks if the method to calculate the cost price of a recipe is working as expected
     */
    @Test
    public void calculateCostToMakeTest(){
       assertEquals(friedRice.getCostPrice(), 1.2f);
       friedRice.addIngredientToRecipe(egg, 1f);
       assertEquals(friedRice.getCostPrice(), 2.2f);
    }


}
