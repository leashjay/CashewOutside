package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seng202.team3.model.*;
import seng202.team3.util.ItemType;
import seng202.team3.util.UnitType;

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
    private Order testOrder;
    private SalesHandler testSalesHandler;

//    /*Background conditions setting up new business */
//    @Given("a {string} for operation")
//    public void aForOperation(String string) throws JAXBException {
//        foodTruckBusiness = new Business("./src/main/resources/data/Ingredients.xml", "./src/main/resources/data/SampleMenu.xml", BusinessApp.suppliersXML, BusinessApp.salesXML, BusinessApp.employeeXML, BusinessApp.truckXML);
//    }
//
//    /*Background conditions setting up inventory list */
//    @Given("an {string} to hold ingredients")
//    public void anToHoldIngredients(String string) {
//
//    }
//
//    /*Background conditions setting up menu list */
//    @Given("an {string} to collect menu items")
//    public void anToCollectMenuItems(String string) {
//        testMenuItem = new MenuItem();
//        menuContents = new HashMap<>();
//        testMenu = new Menu("Test Menu", "A menu for filling and testing", MenuType.FESTIVAL, menuContents);
//    }


    @Given("a cash float")
    public void aCashFloat() throws JAXBException {
        testTruck = new Truck("./src/main/resources/data/Ingredients.xml");
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
        assertEquals(1, testSalesHandler.getOrderHashMap().size());
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

        beefburger = new MenuItem("iB1", "Imaginary Burger", recipe, ItemType.MAIN);

        beefburger.addIngredientToRecipe(ichicken, 5.0f);
        beefburger.addIngredientToRecipe(iloaf, 50.0f);
        beefburger.addIngredientToRecipe(isalt, 15.0f);

        this.beefburger = beefburger;

        testOrder.addToOrder(beefburger);
        testSalesHandler.addOrder(testOrder);
        assertEquals(1, testSalesHandler.getOrderHashMap().size());
    }

    @When("the customer pays ${double}")
    public void theCustomerPays$(Double double1) {
        //testSalesHandler.customerPays(testOrder.getOrderId());
        //this feature isnt implemented
    }

    @Then("the correct change of ${double} is calculated and the inventory stock levels are reduced accordingly")
    public void theCorrectChangeOf$IsCalculatedAndTheInventoryStockLevelsAreReducedAccordingly(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("the customer places an order for baby face")
    public void theCustomerPlacesAnOrderForBabyFace() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the baby face cost is added to the float")
    public void theBabyFaceCostIsAddedToTheFloat() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("the customer places an order for banana split")
    public void theCustomerPlacesAnOrderForBananaSplit() {
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


}