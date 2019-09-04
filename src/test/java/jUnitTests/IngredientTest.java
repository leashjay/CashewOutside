package jUnitTests;

import org.junit.Test;
import seng202.team3.model.Ingredient;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;

import static junit.framework.TestCase.assertEquals;

public class IngredientTest {

    /**
     * Tests the constructor for an ingredient item
     */
    @Test
    public void ingredientConstructorTest() {
        Ingredient ingredient = new Ingredient("4", "Pasta", UnitType.COUNT, ThreeValueLogic.YES, ThreeValueLogic.YES, ThreeValueLogic.NO, 10f);
        assertEquals(ingredient.getCode(), "4");
        assertEquals(ingredient.getName(), "Pasta");
        assertEquals(ingredient.getUnit(), UnitType.COUNT);
        assertEquals(ingredient.getIsVeg(), ThreeValueLogic.YES);
        assertEquals(ingredient.getIsVegan(), ThreeValueLogic.YES);
        assertEquals(ingredient.getIsGF(), ThreeValueLogic.NO);
        assertEquals(ingredient.getCost(), 10f);
    }

    /**
     * Tests setters and getters for an ingredient item
     */
    @Test
    public void ingredientSetterTest() {
        Ingredient ingredient = new Ingredient("4", "Pasta", UnitType.COUNT, ThreeValueLogic.YES, ThreeValueLogic.YES, ThreeValueLogic.NO, 10f);
        ingredient.setIsVegan(ThreeValueLogic.NO);
        ingredient.setCost(999);
        ingredient.setQuantity(1000);
        assertEquals(ingredient.getIsVegan(), ThreeValueLogic.NO);
        assertEquals(ingredient.getCost(), 999);
        assertEquals(ingredient.getQuantity(), 1000);
    }
}
