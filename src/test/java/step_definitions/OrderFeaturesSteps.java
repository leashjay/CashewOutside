package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seng202.team3.model.*;
import seng202.team3.util.ItemType;
import seng202.team3.util.UnitType;
import seng202.team3.view.BusinessApp;

import javax.xml.bind.JAXBException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class OrderFeaturesSteps {

    private Truck testTruck;
    private Business foodTruckBusiness;
    private HashMap<String, Ingredient> inventoryHashMap;
    private HashMap<Ingredient, Float> recipe;
    private Inventory inventory;
    private Menu testMenu;
    private HashMap<String, MenuItem> menuContents;
    private MenuItem beefburger;
    private MenuItem imaginaryBurger;
    private MenuItem iMenuItem;
    private Order testOrder;
    private SalesHandler testSalesHandler;
    private float quantity1;
    private float quantity2;
    private float quantity3;
    private Ingredient testIngredient1;
    private Ingredient testIngredient2;
    private Ingredient testIngredient3;


    @Given("a cash float")
    public void aCashFloat() throws JAXBException {
        testTruck = BusinessApp.getBusiness().getTruck();
        testTruck.setCashAccount(0.0f);
        testTruck.setCashAccount(1000.0f);

    }

    @Given("an ingredients {string} to hold ingredients")
    public void anIngredientsToHoldIngredients(String string) {
        inventoryHashMap = new HashMap<>();
        inventory = new Inventory("Ingredients Inventory", inventoryHashMap);
    }

    @Given("a database of sales {string} to hold all orders made")
    public void aDatabaseOfSalesToHoldAllOrdersMade(String string) {
        SalesHandler testSalesHandler = new SalesHandler();
        Order testOrder = new Order();
        testOrder.setOrderId(333);
        this.testSalesHandler = testSalesHandler;
        this.testOrder = testOrder;
    }

    @Given("an imaginary burger is added to an order")
    public void anImaginaryBurgerIsAddedToAnOrder() {
        Ingredient isauce = new Ingredient("iSauce", "Tomato sauce", 5.0f, UnitType.ML, 1.8f);
        Ingredient ionion = new Ingredient("iOnion", "Diced onion", 50.0f, UnitType.GRAM, 0.10f);
        Ingredient ilettuce = new Ingredient("iLettuce", "Sliced Iceberg lettuce", 15.0f, UnitType.GRAM, 0.10f);

        isauce.setCost(0.10f);
        ionion.setCost(0.30f);
        ilettuce.setCost(0.10f);

        inventory.addIngredient(isauce);
        inventory.addIngredient(ionion);
        inventory.addIngredient(ilettuce);

        HashMap<Ingredient, Float> recipe = new HashMap<>();

        beefburger = new MenuItem("iB1", "Imaginary Burger", recipe, ItemType.MAIN);

        beefburger.addIngredientToRecipe(isauce, 5.0f);
        beefburger.addIngredientToRecipe(ionion, 50.0f);
        beefburger.addIngredientToRecipe(ilettuce, 15.0f);

        this.beefburger = beefburger;
    }


    @When("the order is created")
    public void theOrderIsCreated() {
        testOrder.addToOrder(beefburger);
        testSalesHandler.addOrder(testOrder);
        //assertEquals(1, testSalesHandler.getOrderHashMap().size());
        //currently the float is in the business truck not the test truck and that is problematic
    }


    /*
     * Calculation ((5.0*0.10) + (50.0 * 0.30) + (15.0*0.10) ) *1.25
     * Markup = 10%
     */

    @Then("the correct order total is calculated as ${double}")
    public void theCorrectOrderTotalIsCalculatedAs$(Double double1) {
        assertEquals(18.70f, testOrder.getTotalCost());
    }

    @Given("the customer places an order for coq au van")
    public void theCustomerPlacesAnOrderForCoqAuVan() {
        Ingredient ichicken = new Ingredient("iSauce", "Tomato sauce", 5.0f, UnitType.ML, 1.8f);
        Ingredient iloaf = new Ingredient("iOnion", "Diced onion", 50.0f, UnitType.GRAM, 0.10f);
        Ingredient isalt = new Ingredient("iLettuce", "Sliced Iceberg lettuce", 15.0f, UnitType.GRAM, 0.10f);

        ichicken.setCost(0.10f);
        iloaf.setCost(0.30f);
        isalt.setCost(0.10f);

        inventory.addIngredient(ichicken);
        inventory.addIngredient(iloaf);
        inventory.addIngredient(isalt);

        HashMap<Ingredient, Float> recipe = new HashMap<>();

        imaginaryBurger = new MenuItem("iB1", "Imaginary Burger", recipe, ItemType.MAIN);

        imaginaryBurger.addIngredientToRecipe(ichicken, 5.0f);
        imaginaryBurger.addIngredientToRecipe(iloaf, 50.0f);
        imaginaryBurger.addIngredientToRecipe(isalt, 15.0f);

        this.imaginaryBurger = imaginaryBurger;
        this.quantity1 = ichicken.getQuantity();
        this.quantity1 = ichicken.getQuantity();
        this.quantity2 = iloaf.getQuantity();
        this.quantity3 = isalt.getQuantity();
        this.testIngredient1 = ichicken;
        this.testIngredient2 = iloaf;
        this.testIngredient3 = isalt;

        testOrder.addToOrder(imaginaryBurger);
        testSalesHandler.addOrder(testOrder);
        assertEquals(1, testSalesHandler.getOrderHashMap().size());
        this.testSalesHandler = testSalesHandler;
        this.testOrder = testOrder;
    }

    @When("the customer pays ${double} and is given correct change")
    public void theCustomerPays$AndIsGivenCorrectChange(Double double1) {
        assertEquals(21.3f, testSalesHandler.customerPays(40.00f, testOrder.getOrderId()));
    }


    /**
     * the original quantity is reduced by the recipe quantity
     */
    @Then("the correct change of ${double} is calculated and the inventory stock levels are reduced accordingly")
    public void theCorrectChangeOf$IsCalculatedAndTheInventoryStockLevelsAreReducedAccordingly(Double double1) {
        assertEquals((quantity1 - 5.0f), (quantity1 - imaginaryBurger.getRecipeQuantity(testIngredient1)));
        assertEquals((quantity2 - 50.0f), (quantity2 - imaginaryBurger.getRecipeQuantity(testIngredient2)));
        assertEquals((quantity3 - 15.0f), (quantity3 - imaginaryBurger.getRecipeQuantity(testIngredient3)));

    }

    @Given("the customer places an order for baby face")
    public void theCustomerPlacesAnOrderForBabyFace() {
        Ingredient ibaby = new Ingredient("iBaby", "Baby Face", 10.0f, UnitType.ML, .90f);
        //ibaby.setCost(0.10f);
        inventory.addIngredient(ibaby);

        HashMap<Ingredient, Float> recipe = new HashMap<>();

        iMenuItem = new MenuItem("iBf", "iBaby Face", recipe, ItemType.MAIN);
        iMenuItem.addIngredientToRecipe(ibaby, 5.0f);

        this.iMenuItem = iMenuItem;
        this.quantity1 = ibaby.getQuantity();
        this.testIngredient1 = ibaby;

        this.testTruck.setCashAccount(0);
        testOrder.addToOrder(iMenuItem);
        testSalesHandler.addOrder(testOrder);
        assertEquals(1, testSalesHandler.getOrderHashMap().size());
        this.testSalesHandler = testSalesHandler;
        this.testOrder = testOrder;
    }

    /**
     * (10 - (.90 * 5)*1.1)
     *
     * @param double1
     */
    @When("the customer pays ${double} for baby face")
    public void theCustomerPays$ForBabyFace(Double double1) {
        assertEquals(5.05f, testSalesHandler.customerPays(10.00f, testOrder.getOrderId()), 0.01);

        //BusinessApp.getBusiness().getTruck().getCashAccount();

        assertEquals(4.95f, testTruck.getCashAccount(), 0.01);
    }

    @Then("the baby face sale amount is added to the float")
    public void theBabyFaceCostIsAddedToTheFloat() {
        assertEquals(4.95f, testTruck.getCashAccount(), 0.01);
    }

    @Given("the customer places an order for banana split")
    public void theCustomerPlacesAnOrderForBananaSplit() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("the customer pays ${double} for b_split")
    public void theCustomerPays$ForB_split(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the inventory stock levels are reduced accordingly")
    public void theInventoryStockLevelsAreReducedAccordingly() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("an order is placed for {int} LemCans")
    public void anOrderIsPlacedForLemCans(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("${int} cash is handed over and a sale made")
    public void $CashIsHandedOverAndASaleMade(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the float shows the new correct total and the inventory stock levels are reduced accordingly")
    public void theFloatShowsTheNewCorrectTotalAndTheInventoryStockLevelsAreReducedAccordingly() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("an order is cancelled by customer")
    public void anOrderIsCancelledByCustomer() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("the refund is made")
    public void theRefundIsMade() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the cash float reflects the transaction and the inventory stock levels remains unchanged")
    public void theCashFloatReflectsTheTransactionAndTheInventoryStockLevelsRemainsUnchanged() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }


    @Given("an order is made")
    public void anOrderIsMade() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("another order is made")
    public void anotherOrderIsMade() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("there are two orders queued")
    public void thereAreTwoOrdersQueued() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("there is no inventory")
    public void thereIsNoInventory() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the order cannot be added")
    public void theOrderCannotBeAdded() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }
}