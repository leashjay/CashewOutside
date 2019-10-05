package jUnitTests.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seng202.team3.model.*;
import seng202.team3.parsing.InventoryLoader;
import seng202.team3.util.ItemType;
import seng202.team3.util.MenuType;
import seng202.team3.util.PhoneType;
import seng202.team3.util.UnitType;
import seng202.team3.view.BusinessApp;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.*;

public class LoadingDataSteps {
    private HashMap<String, Ingredient> inventoryHashMap;
    private Inventory inventory;
    //private Ingredient ingredient;
    private HashMap<String, MenuItem> menuContents;
    private MenuItem testMenuItem;
    private Menu testMenu;
    private Ingredient testIngredient;
    private InventoryLoader testLoader;
    private SupplierHandler supplierHandler;
    private HashMap<String, Supplier> contactList;
    private Supplier GoodysGreens;
    private Business foodTruckBusiness;
    private String filename;
    private MenuItem testItem;

    /*Background conditions setting up new business */
    @Given("a {string} for operation")
    public void aForOperation(String string) throws JAXBException {
        foodTruckBusiness = new Business("./src/main/resources/data/Ingredients.xml", "./src/main/resources/data/SampleMenu.xml", BusinessApp.suppliersXML, BusinessApp.salesXML, BusinessApp.employeeXML);
    }

    /*Background conditions setting up inventory list */
    @Given("an {string} to hold ingredients")
    public void anToHoldIngredients(String string) {
        inventoryHashMap = new HashMap<>();
        inventory = new Inventory("Ingredients Inventory", inventoryHashMap);
    }

    /*Background conditions setting up menu list */
    @Given("an {string} to collect menu items")
    public void anToCollectMenuItems(String string) {
        testMenuItem = new MenuItem();
        menuContents = new HashMap<>();
        testMenu = new Menu("Test Menu", "A menu for filling and testing", MenuType.FESTIVAL, menuContents);
    }

    /*Background conditions setting up supplier contact list */
    @Given("an {string} to suppliers details")
    public void anToSuppliersDetails(String string) {
        contactList = new HashMap<String, Supplier>();
        supplierHandler = new SupplierHandler("All the suppliers", contactList);
    }

    @Given("an Ingredient is known")
    public void anIngredientIsKnown() {
        Ingredient testIngredient = new Ingredient("llc", "lettuce", 1.0f, UnitType.COUNT, 0.10f);
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

    @Given("an Menuitem is known")
    public void anMenuIsKnown() {
        Ingredient rice = new Ingredient("1", "Rice", 1f, UnitType.GRAM, 0.001f);
        HashMap riceStuff = new HashMap<>();
        riceStuff.put(rice, 200f);
        MenuItem testItem = new MenuItem("1", "Fried rice", riceStuff, ItemType.MAIN);
        menuContents.put(testItem.getId(),testItem);
        Menu testMenu = new Menu("Not Only Rice", "More than rice", MenuType.FESTIVAL, menuContents);
        this.testMenu = testMenu;
        this.testItem = testItem;
    }

    @Then("the menuitem is in the program")
    public void theMenuIsInTheProgram() { assertTrue(testMenu.searchMenuContent(testItem)); }

    @When("a menu is added")
    public void aMenuIsAdded() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }


    @Given("an supplier is known")
    public void anSupplierIsKnown() {
        Supplier GoodysGreens = new Supplier("GG", "Goody's Greens", "12 Farm Lane", PhoneType.WORK, "0210556677", "goody@green.com", "www.goodysgreens.com");
        this.GoodysGreens = GoodysGreens;
    }


    @When("a supplier is added")
    public void aSupplierIsAdded() {
        supplierHandler.addSupplier(GoodysGreens);
    }

    @Then("the supplier is in the program")
    public void theSupplierIsInTheProgram() {
        assertTrue(supplierHandler.getSuppliers().containsValue(GoodysGreens));
    }

    @Given("a {string} for an XML file containing ingredients")
    public void aForAnXMLFileContainingIngredients(String string) {
        filename = "./src/main/resources/data/Ingredients.xml";
        this.filename = filename;
    }

    @When("the {string} is loaded")
    public void theIsLoaded(String string) throws JAXBException {
        InventoryLoader testLoader = new InventoryLoader();
        inventoryHashMap.clear();
        inventory = testLoader.loadIngredientsData(filename);
        this.inventory = inventory;
        inventoryHashMap = this.inventory.getIngredients();
    }

    @Then("the inventory map is not Null")
    public void theInventoryMapIsNotNull() {
        assertFalse(inventoryHashMap.isEmpty());
    }

    @Given("a {string} for an XML file containing menu items")
    public void aForAnXMLFileContainingMenuItems(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the menu items map is not empty")
    public void theMenuItemsMapIsNotEmpty() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("a {string} for an XML file containing suppliers")
    public void aForAnXMLFileContainingSuppliers(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the supplier map is not empty")
    public void theSupplierMapIsNotEmpty() {
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