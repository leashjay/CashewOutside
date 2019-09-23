package jUnitTests.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seng202.team3.model.*;
import seng202.team3.util.MenuType;
import seng202.team3.util.UnitType;

import java.util.HashMap;

import static junit.framework.TestCase.assertTrue;

public class LoadingDataSteps {
    private HashMap<String, Ingredient> inventoryHashMap;
    private Inventory inventory;
    //private Ingredient ingredient;
    private HashMap<String, MenuItem> menuContents;
    private MenuItem testMenuItem;
    private Menu testMenu;
    private Ingredient testIngredient;
    private Supplier supplier;
    private Inventory sInventory;


    @Given("an {string} to hold ingredients")
    public void anToHoldIngredients(String string) {
        inventoryHashMap = new HashMap<>();
        inventory = new Inventory("Ingredients Inventory", inventoryHashMap);
    }

    @Given("an {string} to collect menu items")
    public void anToCollectMenuItems(String string) {
        testMenuItem = new MenuItem();
        menuContents = new HashMap<>();
        testMenu = new Menu("Test Menu", "A menu for filling and testing", MenuType.FESTIVAL, menuContents);
    }

    @Given("an {string} to suppliers details")
    public void anToSuppliersDetails(String string) {
        sInventory = new Inventory();
        supplier = new Supplier();
    }

    @Given("an Ingredient has a {string}, {string}, {double}, {string} and costs ${float}")
    public void anIngredientHasaAndCosts$(String code, String name, float quantity, String unitType, float cost) {
        Ingredient testIngredient = new Ingredient("llc", "lettuce", 1.0f, UnitType.COUNT, 0.10f);
        Ingredient testIngredient2 = new Ingredient(code, name, quantity, unitType, cost);
        this.testIngredient = testIngredient;

    }

    @When("the Ingredient is added")
    public void theIngredientIsAdded() {
        inventory.addIngredient(testIngredient);
    }

    @Then("the Ingredient is in the program")
    public void theIngredientIsInTheProgram() {
        assertTrue(inventory.searchStock(testIngredient));
    }

    @Given("an Menu has a {string}, {string}, {string}, {string} costs ${double} and has a list of ingredients:")
    public void anMenuHasACosts$AndHasAListOfIngredients(String string, String string2, String string3, String string4, Double double1, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new cucumber.api.PendingException();
    }


    @When("a menu is added")
    public void aMenuIsAdded() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the menu is in the program")
    public void theMenuIsInTheProgram() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("an supplier has a {string}, {string}, {string}, {string}, {string}, sometimes an {string} and\\/or a{string}")
    public void anSupplierHasASometimesAnAndOrA(String string, String string2, String string3, String string4, String string5, String string6, String string7) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("a supplier is added")
    public void aSupplierIsAdded() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the supplier is in the program")
    public void theSupplierIsInTheProgram() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("a {string} for an XML file containing ingredients")
    public void aForAnXMLFileContainingIngredients(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("the {string} is used in the application")
    public void theIsUsedInTheApplication(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the inventory map is not Null")
    public void theInventoryMapIsNotNull() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("a {string} for an XML file containing menu items")
    public void aForAnXMLFileContainingMenuItems(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the menu items map is not Null")
    public void theMenuItemsMapIsNotNull() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("a {string} for an XML file containing suppliers")
    public void aForAnXMLFileContainingSuppliers(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the supplier map is not Null")
    public void theSupplierMapIsNotNull() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("the {string} is used and an integrity error is found")
    public void theIsUsedAndAnIntegrityErrorIsFound(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("an error message is returned to the user")
    public void anErrorMessageIsReturnedToTheUser() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

}