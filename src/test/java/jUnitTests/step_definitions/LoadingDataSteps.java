package jUnitTests.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Inventory;
import seng202.team3.util.UnitType;

import java.util.HashMap;

import static junit.framework.TestCase.assertTrue;

public class LoadingDataSteps {
    private HashMap<String, Ingredient> inventoryHashMap;
    private Inventory inventory = new Inventory();
    private Ingredient ingredient;

    @Given("an Ingredient has a {string} {string} and a default type with quantity {int}")
    public void anIngredientHasAAndADefaultTypeWithQuantity(String string, String string2, Integer int1) {
        Ingredient ingredient = new Ingredient(string, string2, int1, UnitType.COUNT, int1);
    }

    @When("the Ingredient is added")
    public void theIngredientIsAdded() {
        inventory.addIngredient(ingredient);

    }

    @Then("the Ingredient is in the system")
    public void theIngredientIsInTheSystem() {
        assertTrue(inventory.searchStock(ingredient));

    }


}
