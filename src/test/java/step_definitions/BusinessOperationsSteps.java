package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seng202.team3.model.*;
import seng202.team3.util.ItemType;
import seng202.team3.util.UnitType;
import static junit.framework.TestCase.assertEquals;

import java.util.HashMap;


public class BusinessOperationsSteps {

    Truck truck = new Truck();
    SalesHandler salesHandler = new SalesHandler();
    Double cashAdded;
    Order friedRiceOrder;



    @Given("The business adds ${double} to the starting float")
    public void theBusinessAdds$ToTheStartingFloat(Double cashAmount) {
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
        salesHandler.addOrder(friedRiceOrder);
        

    }

    @Then("the cash floats reflects the correct total")
    public void theCashFloatsReflectsTheCorrectTotal() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(truck.getCashAccount(), cashAdded - friedRiceOrder.getTotalCost());
    }

    @Given("The business opens the day with a ${double} float")
    public void theBusinessOpensTheDayWithA$Float(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
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
        throw new cucumber.api.PendingException();
    }

    @When("the price gets amended to ${double}")
    public void thePriceGetsAmendedTo$(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the new cost of a hot chocolate is ${double}")
    public void theNewCostOfAHotChocolateIs$(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }


}