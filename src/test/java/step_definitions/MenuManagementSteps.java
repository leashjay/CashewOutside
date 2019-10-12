package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seng202.team3.model.*;
import seng202.team3.util.ItemType;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;
import seng202.team3.view.BusinessApp;

import java.util.HashMap;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MenuManagementSteps {
    Inventory testInventory;
    Ingredient choc;
    Menu testMenu;
    Ingredient regMilk;
    MenuItem chocMilk;
    Order testOrder;
    ThreeValueLogic customerIsGF;

    @Given("choc and regMilk in inventory")
    public void chocAndRegMilkInInventory() {
        HashMap<String, Ingredient> ingredients = new HashMap<String, Ingredient>();
        choc = new Ingredient("Choc", "Chocolate", 250f, UnitType.GRAM, 0.5f);
        regMilk = new Ingredient("RgMilk", "Regular Milk", 750, UnitType.ML, 0.005f);
        ingredients.put(choc.getCode(), choc);
        ingredients.put(regMilk.getCode(), regMilk);
        testInventory = new Inventory("Test Inventory", ingredients);
    }

    @When("a menu item is created for chocolate milk")
    public void aMenuItemIsCreatedForChocolateMilk() {
        HashMap<Ingredient, Float> ingredients = new HashMap<Ingredient, Float>();
        ingredients.put(choc, 50f);
        ingredients.put(regMilk, 250f);

        chocMilk = new MenuItem("ChocM", "Chocolate Milk", ingredients, ItemType.BEVERAGE);
    }

    @Then("correct serving size is calculated")
    public void correctServingSizeIsCalculated() {
        chocMilk.calculateServing(testInventory);
        assertEquals(3, chocMilk.getServings());
    }

    @Given("choc milk and no stock")
    public void chocMilkAndNoStock() {
        choc = new Ingredient("Choc", "Chocolate", UnitType.GRAM, ThreeValueLogic.YES, ThreeValueLogic.YES, ThreeValueLogic.YES, 2.5f, 250f);
        regMilk = new Ingredient("RegMilk", "Regular Milk", UnitType.ML, ThreeValueLogic.NO, ThreeValueLogic.YES, ThreeValueLogic.YES, 0.005f, 750f);
        testInventory = BusinessApp.getBusiness().getTruck().getInventory();


        testInventory.getIngredients().get(choc.getCode()).setQuantity(0f);
        testInventory.getIngredients().get(regMilk.getCode()).setQuantity(0f);
    }


    @When("an order is placed for chocolate milk")
    public void anOrderIsPlacedForChocolateMilk() {
        HashMap<Ingredient, Float> ingredients = new HashMap<Ingredient, Float>();
        ingredients.put(choc, 50f);
        ingredients.put(regMilk, 250f);

        chocMilk = new MenuItem("ChocM", "Chocolate Milk", ingredients, ItemType.BEVERAGE);

        testOrder = new Order();
        testOrder.addToOrder(chocMilk);

    }

    @Then("the menu item serving count is {int}")
    public void theMenuItemServingCount(Integer int1) {
        chocMilk.calculateServing(testInventory);
        assertEquals(0, chocMilk.getServings());
        assertFalse(testOrder.enoughStock()); // this boolean triggers an exception handler in SalesController class
    }

    @Given("When an order is made for a menu item")
    public void whenAnOrderIsMadeForAMenuItem() {
        choc = new Ingredient("Choc", "Chocolate", UnitType.GRAM, ThreeValueLogic.YES, ThreeValueLogic.YES, ThreeValueLogic.YES, 2.5f, 250f);
        regMilk = new Ingredient("RegMilk", "Regular Milk", UnitType.ML, ThreeValueLogic.NO, ThreeValueLogic.YES, ThreeValueLogic.YES, 0.005f, 750f);
        HashMap<Ingredient, Float> ingredients = new HashMap<Ingredient, Float>();
        ingredients.put(choc, 50f);
        ingredients.put(regMilk, 250f);

        chocMilk = new MenuItem("ChocM", "Chocolate Milk", ingredients, ItemType.BEVERAGE);

        testOrder = new Order();
        testOrder.addToOrder(chocMilk);
    }

    @When("an intolerance for gluten is flagged")
    public void anIntoleranceForGlutenIsFlagged() {
        customerIsGF = ThreeValueLogic.NO;
    }

    @Then("check menu item attribute by filter")
    public void checkMenuItemAttributeByFilter() {
        assertEquals(ThreeValueLogic.YES, testOrder.getOrderedItems().get(0).isGlutenFree()); //will show in GUI after filtering that the menu item is gluten free
        assertTrue(customerIsGF != testOrder.getOrderedItems().get(0).isGlutenFree());
    }

    @Given("When an order is made for a vegetarian item")
    public void whenAnOrderIsMadeForAVegetarianItem() {
        choc = new Ingredient("Choc", "Chocolate", UnitType.GRAM, ThreeValueLogic.YES, ThreeValueLogic.YES, ThreeValueLogic.YES, 2.5f, 250f);
        regMilk = new Ingredient("RegMilk", "Regular Milk", UnitType.ML, ThreeValueLogic.YES, ThreeValueLogic.YES, ThreeValueLogic.YES, 0.005f, 750f);
        HashMap<Ingredient, Float> ingredients = new HashMap<Ingredient, Float>();
        ingredients.put(choc, 50f);
        ingredients.put(regMilk, 250f);

        chocMilk = new MenuItem("ChocM", "Chocolate Milk", ingredients, ItemType.BEVERAGE);
    }

    @When("when they order a vegetarian meal")
    public void whenTheyOrderAVegetarianMeal() {
        testOrder = new Order();
        testOrder.addToOrder(chocMilk);
    }

    @Then("a search of all ingredients show only yes on vege flags")
    public void aSearchOfAllIngredientsShowOnlyYesOnVegeFlags() {
        assertEquals(ThreeValueLogic.YES, choc.getIsVegetarian());
        assertEquals(ThreeValueLogic.YES, regMilk.getIsVegetarian());

        assertEquals(ThreeValueLogic.YES, testOrder.getOrderedItems().get(0).isVegetarian());
    }

    @Given("a menu item")
    public void aMenuItem() {
        choc = new Ingredient("Choc", "Chocolate", UnitType.GRAM, ThreeValueLogic.YES, ThreeValueLogic.YES, ThreeValueLogic.YES, 2.5f, 250f);
        regMilk = new Ingredient("RegMilk", "Regular Milk", UnitType.ML, ThreeValueLogic.YES, ThreeValueLogic.YES, ThreeValueLogic.YES, 0.005f, 750f);
        HashMap<Ingredient, Float> ingredients = new HashMap<Ingredient, Float>();
        ingredients.put(choc, 50f);
        ingredients.put(regMilk, 250f);

        this.chocMilk = new MenuItem("ChocM", "Chocolate Milk", ingredients, ItemType.BEVERAGE);
        this.testMenu = BusinessApp.getBusiness().getMenuManager();
        testMenu.addMenuItem(chocMilk);
    }

    @When("the user wants to delete it")
    public void theUserWantsToDeleteIt() {
        testMenu.removeMenuItem(chocMilk.getId());
    }

    @Then("the menu item can be deleted and is no longer present AND exceptions are handled")
    public void theMenuItemCanBeDeletedAndIsNoLongerPresentANDExceptionsAreHandled() {
        assertFalse(testMenu.getMenuItem().containsKey("ChocM"));
    }

    @Given("a recipe")
    public void aRecipe() {
        choc = new Ingredient("Choc", "Chocolate", UnitType.GRAM, ThreeValueLogic.YES, ThreeValueLogic.YES, ThreeValueLogic.YES, 2.5f, 250f);
        regMilk = new Ingredient("RegMilk", "Regular Milk", UnitType.ML, ThreeValueLogic.YES, ThreeValueLogic.YES, ThreeValueLogic.YES, 0.005f, 750f);
        HashMap<Ingredient, Float> ingredients = new HashMap<Ingredient, Float>();
        ingredients.put(choc, 50f);
        ingredients.put(regMilk, 250f);

        this.chocMilk = new MenuItem("ChocM", "Chocolate Milk", ingredients, ItemType.BEVERAGE);
        assertTrue(chocMilk.getIngredients().size() != 0);
    }

    @When("an ingredient is added")
    public void anIngredientIsAdded() {
        Ingredient newIng = new Ingredient("TrimMilk", "Trim Milk", UnitType.ML, ThreeValueLogic.YES, ThreeValueLogic.YES, ThreeValueLogic.YES, 0.005f, 750f);

        this.chocMilk.addIngredientToRecipe(newIng, 750f);
    }

    @Then("the ingredient is found in the recipe")
    public void theIngredientIsFoundInTheRecipe() {
        assertTrue(chocMilk.getRecipeQuantity("TrimMilk") != 0f);
    }

    @Given("A business")
    public void aBusiness() {
        this.testMenu = BusinessApp.getBusiness().getMenuManager();
    }

    @When("a new menu item is added")
    public void aNewMenuItemIsAdded() {
        choc = new Ingredient("Choc", "Chocolate", UnitType.GRAM, ThreeValueLogic.YES, ThreeValueLogic.YES, ThreeValueLogic.YES, 2.5f, 250f);
        regMilk = new Ingredient("RegMilk", "Regular Milk", UnitType.ML, ThreeValueLogic.YES, ThreeValueLogic.YES, ThreeValueLogic.YES, 0.005f, 750f);
        HashMap<Ingredient, Float> ingredients = new HashMap<Ingredient, Float>();
        ingredients.put(choc, 50f);
        ingredients.put(regMilk, 250f);

        this.chocMilk = new MenuItem("ChocM", "Chocolate Milk", ingredients, ItemType.BEVERAGE);
        this.testMenu.addMenuItem(chocMilk);
    }

    @Then("it is available in the application")
    public void itIsAvailableInTheApplication() {
        assertTrue(testMenu.getMenuItem().containsKey("ChocM"));
    }

    @Given("a testable menu item")
    public void aTestableMenuItem() {
        choc = new Ingredient("Choc", "Chocolate", UnitType.GRAM, ThreeValueLogic.YES, ThreeValueLogic.YES, ThreeValueLogic.YES, 2.5f, 250f);
        regMilk = new Ingredient("RegMilk", "Regular Milk", UnitType.ML, ThreeValueLogic.YES, ThreeValueLogic.YES, ThreeValueLogic.YES, 0.005f, 750f);
        HashMap<Ingredient, Float> ingredients = new HashMap<Ingredient, Float>();
        ingredients.put(choc, 50f);
        ingredients.put(regMilk, 250f);

        this.chocMilk = new MenuItem("ChocM", "Chocolate Milk", ingredients, ItemType.BEVERAGE);
        testMenu = BusinessApp.getBusiness().getMenuManager();
        testMenu.addMenuItem(chocMilk);

    }

    @When("there is no inventory")
    public void thereIsNoInventory() {
        testInventory = BusinessApp.getBusiness().getTruck().getInventory();
        testInventory.getIngredients().get("Choc").setQuantity(0f);
    }

    @Then("the order cannot be added")
    public void theOrderCannotBeAdded() {
        testOrder = new Order();
        testOrder.setToNextID();
        testOrder.addToOrder(testMenu.getMenuItem().get("ChocM"));
        String errorMessage = "";
        try {
            testOrder.confirmOrder();
        } catch (Error e) {
            errorMessage = e.getMessage();
        }

        assertTrue(errorMessage.length() != 0);
    }

}

