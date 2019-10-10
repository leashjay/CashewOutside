package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seng202.team3.model.*;
import seng202.team3.util.ItemType;
import seng202.team3.util.UnitType;

import javax.xml.bind.JAXBException;
import java.util.HashMap;

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

    @Given("a beefburger is added to an order")
    public void aBeefburgerIsAddedToAnOrder() {
        Ingredient tsauce = new Ingredient("TSauce", "Tomato sauce", 5.0f, UnitType.ML, 1.8f);
        Ingredient onion = new Ingredient("Onion", "Diced onion", 100.0f, UnitType.GRAM, 0.10f);
        Ingredient lettuce = new Ingredient("Lettuce", "Sliced Iceberg lettuce", 2.0f, UnitType.COUNT, 0.10f);
        Ingredient beetroot = new Ingredient("Beetroot", "Beetroot slice", 3.0f, UnitType.COUNT, 0.10f);
        Ingredient burgerbun = new Ingredient("BBun", "Hamburger bun", 3.0f, UnitType.COUNT, 0.50f);
        Ingredient mayo = new Ingredient("Mayo", "Eater plain Mayonnaise", 10.0f, UnitType.ML, 0.10f);
        Ingredient burgerpattie = new Ingredient("BPat", "Beef patty", 12.0f, UnitType.COUNT, 1.00f);

        inventory.addIngredient(tsauce);
        inventory.addIngredient(onion);
        inventory.addIngredient(lettuce);
        inventory.addIngredient(beetroot);
        inventory.addIngredient(burgerbun);
        inventory.addIngredient(mayo);
        inventory.addIngredient(burgerpattie);

        HashMap<Ingredient, Float> recipe = new HashMap<>();

        beefburger = new MenuItem("BB1", "Beefburger", recipe, ItemType.MAIN);

        beefburger.addIngredientToRecipe(tsauce, 5.0f);
        beefburger.addIngredientToRecipe(onion, 50.0f);
        beefburger.addIngredientToRecipe(lettuce, 0.15f);
        beefburger.addIngredientToRecipe(beetroot, 1.0f);
        beefburger.addIngredientToRecipe(burgerbun, 1.0f);
        beefburger.addIngredientToRecipe(mayo, 0.10f);
        beefburger.addIngredientToRecipe(burgerpattie, 1.0f);

        this.beefburger = beefburger;
    }


    @When("the order is created")
    public void theOrderIsCreated() {
        testOrder.addToOrder(beefburger);
    }

    @Then("the correct order total is calculated as ${double}")
    public void theCorrectOrderTotalIsCalculatedAs$(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("the customer places an order for coq au van")
    public void theCustomerPlacesAnOrderForCoqAuVan() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("the customer pays ${double}")
    public void theCustomerPays$(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
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