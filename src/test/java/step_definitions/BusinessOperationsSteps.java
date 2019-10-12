package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seng202.team3.model.*;
import seng202.team3.util.ItemType;
import seng202.team3.util.MenuType;
import seng202.team3.util.UnitType;
import static junit.framework.TestCase.assertEquals;

import java.util.HashMap;


public class BusinessOperationsSteps {

    Truck truck = new Truck();
    SalesHandler salesHandler = new SalesHandler();
    float cashAdded;
    Order friedRiceOrder;
    Menu menu = new Menu("Default menu", "the default menu", MenuType.SUMMER, new HashMap<String, MenuItem>());
    MenuItem hotChocolate;



    @Given("The business adds ${float} to the starting float")
    public void theBusinessAdds$ToTheStartingFloat(float cashAmount) {
        cashAdded = cashAmount;
        truck.setCashAccount(cashAmount);

    }

    @When("a order takes place")
    public void aOrderTakesPlace() {
        friedRiceOrder = new Order();
        Ingredient rice = new Ingredient("1", "Rice", 1000f, UnitType.GRAM, 0.01f);
        HashMap<Ingredient, Float>friedRiceIngredients = new HashMap<>();
        friedRiceIngredients = new HashMap<>();
        friedRiceIngredients.put(rice, 200f);
        MenuItem friedRice = new MenuItem("1", "Fried rice", friedRiceIngredients, ItemType.MAIN);
        friedRiceOrder.addToOrder(friedRice);
        friedRiceOrder.setOrderId(1);
        salesHandler.addOrder(friedRiceOrder);
        salesHandler.customerPays(friedRiceOrder.getTotalCost(), 1, truck);

    }

    @Then("the cash floats reflects the correct total")
    public void theCashFloatsReflectsTheCorrectTotal() {
        assertEquals(truck.getCashAccount(), cashAdded + friedRiceOrder.getTotalCost());
    }

    @Given("The business opens the day with a ${float} float")
    public void theBusinessOpensTheDayWithA$Float(Float cashAmount) {
        cashAdded = cashAmount;
        truck.setCashAccount(cashAmount);
    }

    @When("several orders are taken during the day")
    public void severalOrdersAreTakenDuringTheDay() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the business closes the float and a earnings value can be calculated")
    public void theBusinessClosesTheFloatAndAEarningsValueCanBeCalculated() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("The business has a hot chocolate on the menu")
    public void theBusinessHasAHotChocolateOnTheMenu() {
        // Write code here that turns the phrase above into concrete actions
        Ingredient milk = new Ingredient("1", "Milk", 1000f, UnitType.ML, 0.1f);
        Ingredient milo = new Ingredient("2", "Milo", 1000f, UnitType.GRAM, 0.1f);
        HashMap<Ingredient, Float>hotChocolateIngredients = new HashMap<>();
        hotChocolateIngredients.put(milk, 200f);
        hotChocolateIngredients.put(milo, 10f);
        hotChocolate = new MenuItem("2", "Hot Chocolate", hotChocolateIngredients, ItemType.BEVERAGE);
        hotChocolate.setSalePrice(2);
        hotChocolate.setMarkUp(1);
        menu.addMenuItem(hotChocolate);
    }

    @When("the price gets amended to ${float}")
    public void thePriceGetsAmendedTo$(float newSalePrice) {
        hotChocolate.setSalePrice(newSalePrice);
    }

    @Then("the new cost of a hot chocolate is ${float}")
    public void theNewCostOfAHotChocolateIs$(Float newSalePrice) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(hotChocolate.calculateSalePrice());
        assertEquals(menu.getMenuItem(hotChocolate.getId()).getSalePrice(), newSalePrice);
    }


}