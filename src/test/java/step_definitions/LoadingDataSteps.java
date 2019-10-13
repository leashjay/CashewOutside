package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seng202.team3.controller.AddXMLController;
import seng202.team3.model.*;
import seng202.team3.parsing.InventoryLoader;
import seng202.team3.parsing.MenuLoader;
import seng202.team3.parsing.SuppliersLoader;
import seng202.team3.util.ItemType;
import seng202.team3.util.MenuType;
import seng202.team3.util.PhoneType;
import seng202.team3.util.UnitType;
import seng202.team3.view.BusinessApp;

import javax.xml.bind.JAXBException;
import java.util.HashMap;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class LoadingDataSteps {
    private HashMap<String, Ingredient> inventoryHashMap;
    private HashMap<String, Supplier> supplierHashMap;
    private Inventory inventory;
    private HashMap<String, MenuItem> menuContents;
    private MenuItem testMenuItem;
    private Menu testMenu;
    private Ingredient testIngredient;
    private SupplierHandler supplierHandler;
    private SuppliersLoader suppliersLoader;
    private MenuItem testItem;
    private HashMap<String, Supplier> contactList;
    private Supplier GoodysGreens;
    private Business foodTruckBusiness;
    private String filename;
    private MenuLoader testMenuLoader;
    private InventoryLoader testInventoryLoader;

    /*Background conditions setting up new business */
    @Given("a {string} for operation")
    public void aForOperation(String string) throws JAXBException {
        foodTruckBusiness = new Business("./src/main/resources/data/Ingredients.xml", "./src/main/resources/data/SampleMenu.xml", BusinessApp.suppliersXML, BusinessApp.salesXML, BusinessApp.employeeXML, BusinessApp.truckXML);
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

    @Given("a Menu item is known")
    public void anMenuItemIsKnown() {
        Ingredient rice = new Ingredient("1", "Rice", 1f, UnitType.GRAM, 0.001f);
        HashMap riceStuff = new HashMap<>();
        riceStuff.put(rice, 200f);
        MenuItem testItem = new MenuItem("1", "Fried rice", riceStuff, ItemType.MAIN);
        menuContents.put(testItem.getId(), testItem);
        Menu testMenu = new Menu("Not Only Rice", "More than rice", MenuType.FESTIVAL, menuContents);
        this.testMenu = testMenu;
        this.testItem = testItem;
    }


    @Then("the menu item is in the program")
    public void theMenuItemIsInTheProgram() {
        assertTrue(testMenu.searchMenuContent(testItem));
    }

    @When("a menu item is added")
    public void aMenuItemIsAdded() {
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

    @Given("an ingredient {string} for an XML file containing ingredients")
    public void anIngredientForAnXMLFileContainingIngredients(String string) {
        filename = "./src/main/resources/data/Ingredients.xml";
        this.filename = filename;
    }

    @When("the ingredient {string} is loaded")
    public void theIngredientIsLoaded(String string) throws JAXBException {
        testInventoryLoader = new InventoryLoader();
        inventoryHashMap.clear();
        inventory = testInventoryLoader.loadIngredientsData(filename);
        this.inventory = inventory;
        inventoryHashMap = this.inventory.getIngredients();
    }

    @Then("the inventory map is not Null")
    public void theInventoryMapIsNotNull() {
        assertFalse(inventoryHashMap.isEmpty());
    }

    @Given("a menu {string} for an XML file containing menu items")
    public void aMenuForAnXMLFileContainingMenuItems(String string) {
        filename = "./src/main/resources/data/SampleMenu.xml";
        this.filename = filename;
    }

    @When("the menu {string} is loaded")
    public void theMenuIsLoaded(String string) throws JAXBException {
        testMenuLoader = new MenuLoader();
        menuContents.clear();
        testMenu = testMenuLoader.loadMenuData(filename);
        menuContents = testMenu.filterMenuItems();
        //throw new cucumber.api.PendingException();
    }

    @Then("the menu items map is not empty")
    public void theMenuItemsMapIsNotEmpty() throws JAXBException {
        assertFalse(menuContents.isEmpty());
        //MenuLoader testMenuLoader = new MenuLoader();
        //inventoryHashMap.clear();
        //inventory = testMenuLoader.loadMenuData(filename);
        //this.inventory = inventory;
        // inventoryHashMap = this.inventory.getIngredients();
        //throw new cucumber.api.PendingException();
    }

    @Given("a supplier {string} for an XML file containing suppliers")
    public void aSupplierForAnXMLFileContainingSuppliers(String string) {
        filename = "./src/main/resources/data/Suppliers.xml";
        this.filename = filename;
    }

    @When("the supplier {string} is loaded")
    public void theSupplierIsLoaded(String string) throws JAXBException {
        suppliersLoader = new SuppliersLoader();
        supplierHashMap = supplierHandler.getSuppliers();
        supplierHashMap.clear();
        supplierHandler = suppliersLoader.loadSuppliersData(filename);
        supplierHashMap = supplierHandler.getSuppliers();
    }

    @Then("the supplier map is not empty")
    public void theSupplierMapIsNotEmpty() {
        assertFalse(supplierHashMap.isEmpty());
    }

    @Given("a ingredient {string} for an XML file containing ingredients")
    public void aIngredientForAnXMLFileContainingIngredients(String string) {
        filename = "./src/main/resources/data/testdata/testErrorIngredients.xml";
        this.filename = filename;
    }

    @When("the {string} is used and an integrity error is found")
    public void theIsUsedAndAnIntegrityErrorIsFound(String string) throws JAXBException {
        AddXMLController.errorMessageList.clear();
        InventoryLoader testInventoryLoader = new InventoryLoader();
        // inventory = testInventoryLoader.loadIngredientsData(filename);
        throw new cucumber.api.PendingException();
    }

    @Then("an error message is returned to the user")
    public void anErrorMessageIsReturnedToTheUser() {
        assertFalse(AddXMLController.errorMessageList.isEmpty());
        throw new cucumber.api.PendingException();
    }

    @Given("a menu item {string} for an XML file containing menu items")
    public void aMenuItemForAnXMLFileContainingMenuItems(String string) {
        filename = "./src/main/resources/data/testdata/testErrorMenu.xml";
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

}
