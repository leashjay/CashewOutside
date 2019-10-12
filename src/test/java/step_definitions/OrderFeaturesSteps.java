package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seng202.team3.model.*;
import seng202.team3.util.ItemType;
import seng202.team3.util.OrderStatus;
import seng202.team3.util.UnitType;
import seng202.team3.view.BusinessApp;

import javax.xml.bind.JAXBException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

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
    private Order testOrder1;
    private SalesHandler testSalesHandler;
    private float quantity1;
    private float quantity2;
    private float quantity3;
    private Ingredient testIngredient1;
    private Ingredient testIngredient2;
    private Ingredient testIngredient3;
    private Float initialCashFloat;
    private Float iMenuItemPrice;
    private Float initialChocQtt;
    private Float initialRegMilkQtt;
    private Float initialLemCanQtt;


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

        isauce.setCost(0.10f);// Write code here that turns the phrase above into concrete actions
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
        assertEquals((quantity1 - 5.0f), (quantity1 - imaginaryBurger.getRecipeQuantity(testIngredient1.getCode())));
        assertEquals((quantity2 - 50.0f), (quantity2 - imaginaryBurger.getRecipeQuantity(testIngredient2.getCode())));
        assertEquals((quantity3 - 15.0f), (quantity3 - imaginaryBurger.getRecipeQuantity(testIngredient3.getCode())));

    }

    @Given("the customer places an order for baby face")
    public void theCustomerPlacesAnOrderForBabyFace() {
        testMenu = BusinessApp.getBusiness().getMenuManager();
        testSalesHandler = BusinessApp.getBusiness().getSalesHandler();
        testOrder = new Order();
        iMenuItem = testMenu.getMenuItem().get("BF");
        testOrder.addToOrder(iMenuItem);
        testOrder.setToNextID();
        testSalesHandler.addOrder(testOrder);
        System.out.println(testOrder.getOrderId());
    }

    /**
     * (10 - (.90 * 5)*1.1)
     *
     * @param double1
     */
    @When("the customer pays ${double} for baby face")
    public void theCustomerPays$ForBabyFace(Double double1) {
        iMenuItemPrice = iMenuItem.getSalePrice();
        initialCashFloat = testTruck.getCashAccount();
        assertEquals(10.00f - iMenuItemPrice, testSalesHandler.customerPays(10.00f, testOrder.getOrderId()), 0.01);
    }

    @Then("the baby face sale amount is added to the float")
    public void theBabyFaceCostIsAddedToTheFloat() {
        assertEquals(initialCashFloat + iMenuItemPrice, testTruck.getCashAccount(), 0.01);
    }

    @Given("the customer places an order for hot chocolate")
    public void theCustomerPlaceAnOrderForHotChocolate() {
        testMenu = BusinessApp.getBusiness().getMenuManager();
        testOrder = new Order();
        testOrder.setToNextID();
        iMenuItem = testMenu.getMenuItem().get("HotChoc");
        testOrder.addToOrder(iMenuItem);
        testSalesHandler.addOrder(testOrder);

    }

    @When("the customer pays ${double} for hot chocolate")
    public void theCustomerPays$ForHotChocolate(Double double1) {
        iMenuItemPrice = iMenuItem.getSalePrice();
        inventory = BusinessApp.getBusiness().getTruck().getInventory();
        initialChocQtt = inventory.getIngredients().get("Choc").getQuantity();
        initialRegMilkQtt = inventory.getIngredients().get("RegMilk").getQuantity();

        assertEquals(12.0f - iMenuItemPrice, testSalesHandler.customerPays(12.0f, testOrder.getOrderId()));
    }

    @Then("the inventory stock levels are reduced accordingly")
    public void theInventoryStockLevelsAreReducedAccordingly() {
        Float recipeChocQtt = iMenuItem.getRecipeQuantity("Choc");
        Float recipeRegMilkQtt = iMenuItem.getRecipeQuantity("RegMilk");

        assertEquals(initialChocQtt - recipeChocQtt, inventory.getIngredients().get("Choc").getQuantity());
        assertEquals(initialRegMilkQtt - recipeRegMilkQtt, inventory.getIngredients().get("RegMilk").getQuantity());
    }

    @Given("an order is placed for {int} LemCans")
    public void anOrderIsPlacedForLemCans(Integer int1) {
        testMenu = BusinessApp.getBusiness().getMenuManager();
        testOrder = new Order();
        testOrder.setToNextID();
        iMenuItem = testMenu.getMenuItem().get("Lem");
        testOrder.addToOrder(iMenuItem);
        testSalesHandler.addOrder(testOrder);
    }

    @When("${int} cash is handed over and a sale made")
    public void $CashIsHandedOverAndASaleMade(Integer int1) {
        iMenuItemPrice = iMenuItem.getSalePrice();
        inventory = BusinessApp.getBusiness().getTruck().getInventory();
        initialLemCanQtt = inventory.getIngredients().get("LemCan").getQuantity();
        initialCashFloat = BusinessApp.getBusiness().getTruck().getCashAccount();

        assertEquals(5f - iMenuItemPrice, testSalesHandler.customerPays(5f, testOrder.getOrderId()));
    }

    @Then("the float shows the new correct total and the inventory stock levels are reduced accordingly")
    public void theFloatShowsTheNewCorrectTotalAndTheInventoryStockLevelsAreReducedAccordingly() {
        assertEquals(initialCashFloat + iMenuItemPrice, BusinessApp.getBusiness().getTruck().getCashAccount());
        Float recipeLemCanQtt = iMenuItem.getRecipeQuantity("LemCan");

        assertEquals(initialLemCanQtt - recipeLemCanQtt, inventory.getIngredients().get("LemCan").getQuantity());
    }

    @Given("an order is cancelled by customer")
    public void anOrderIsCancelledByCustomer() {
        testMenu = BusinessApp.getBusiness().getMenuManager();
        testOrder = new Order();
        testOrder.setToNextID();
        iMenuItem = testMenu.getMenuItem().get("Lem");
        testOrder.addToOrder(iMenuItem);
        testSalesHandler.addOrder(testOrder);
        inventory = BusinessApp.getBusiness().getTruck().getInventory();

        initialCashFloat = BusinessApp.getBusiness().getTruck().getCashAccount();
        initialLemCanQtt = inventory.getIngredients().get("LemCan").getQuantity();


    }

    @When("the refund is made")
    public void theRefundIsMade() {
        assertEquals(testOrder.getTotalCost(), testSalesHandler.refundOrder(testOrder.getOrderId()));
    }

    @Then("the cash float reflects the transaction and the inventory stock levels remains unchanged")
    public void theCashFloatReflectsTheTransactionAndTheInventoryStockLevelsRemainsUnchanged() {
        assertEquals(initialCashFloat - testOrder.getTotalCost(), BusinessApp.getBusiness().getTruck().getCashAccount());

        assertEquals(initialLemCanQtt, inventory.getIngredients().get("LemCan").getQuantity());
    }


    @Given("an order is made")
    public void anOrderIsMade() {
        testMenu = BusinessApp.getBusiness().getMenuManager();
        testOrder = new Order();
        testOrder.setToNextID();
        testOrder.addToOrder(testMenu.getMenuItem().get("Lem"));
        testOrder.confirmOrder();
        testSalesHandler.addOrder(testOrder);

        assertTrue(testSalesHandler.getOrderHashMap().containsKey(testOrder.getOrderId()));
    }

    @When("another order is made")
    public void anotherOrderIsMade() {
        testOrder1 = new Order();
        testOrder1.setToNextID();
        testOrder1.addToOrder(testMenu.getMenuItem().get("Lem"));
        testOrder1.confirmOrder();
        testSalesHandler.addOrder(testOrder1);

        assertTrue(testSalesHandler.getOrderHashMap().containsKey(testOrder1.getOrderId()));
    }

    @Then("there are two orders queued")
    public void thereAreTwoOrdersQueued() {
        assertEquals(OrderStatus.QUEUED, testSalesHandler.getOrder(testOrder.getOrderId()).getStatus());
        assertEquals(OrderStatus.QUEUED, testSalesHandler.getOrder(testOrder1.getOrderId()).getStatus());
    }


}