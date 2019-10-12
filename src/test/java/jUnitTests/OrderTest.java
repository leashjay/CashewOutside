package jUnitTests;


import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Business;
import seng202.team3.model.Ingredient;
import seng202.team3.model.MenuItem;
import seng202.team3.model.Order;
import seng202.team3.util.ItemType;
import seng202.team3.util.UnitType;
import seng202.team3.view.BusinessApp;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.TestCase.*;

public class OrderTest {

    Ingredient egg;
    Business business;
    MenuItem menuItem;
    MenuItem itemToRemove;
    ArrayList<MenuItem> menuItems;
    HashMap<Ingredient, Float> ingredients = new HashMap<>();
    float cost;

//    String id, String name, HashMap<Ingredient, Float> ingredients, ItemType type

    /**
     * setup some objects to be used by the tests
     * @throws JAXBException for when creating a new Business
     */
    @Before
    public void setup() throws JAXBException {
        Ingredient rice = new Ingredient("1", "Rice", 1f, UnitType.GRAM, 0.001f);
        Ingredient carrot = new Ingredient("2", "Carrot", 2f, UnitType.COUNT, 0.01f);
        Ingredient peas = new Ingredient("3", "Peas", 3f, UnitType.GRAM, 0.01f);
        egg = new Ingredient("4", "Rice", 4f, UnitType.GRAM, 1f);
        ingredients.put(rice, 1f);
        ingredients.put(carrot, 1f);
        ingredients.put(peas, 1f);
        ingredients.put(egg, 1f);
        business = new Business(BusinessApp.ingredientsXML, BusinessApp.menuXML, BusinessApp.suppliersXML, BusinessApp.salesXML, BusinessApp.employeeXML, BusinessApp.truckXML);
        menuItem = new MenuItem("This", "a", ingredients, ItemType.MAIN);
        menuItems = new ArrayList<>();
        itemToRemove = new MenuItem("Different", "b", ingredients, ItemType.ASIAN);
        menuItems.add(new MenuItem("This", "b", ingredients, ItemType.MAIN));
        menuItems.add(itemToRemove);
        menuItems.add(new MenuItem("This", "a", ingredients, ItemType.ASIAN));
        //Setup costs
        cost = 0;
        for (int i = 0; i < menuItems.size(); i++) {
            cost += menuItems.get(i).getSalePrice();
        }

    }

    /**
     * Test the functionality of order.setToNextID()
     */
    @Test
    public void setToNextIDTest() {
        Order order = new Order();
        int oldOrderID = business.getLastOrderID();
        order.setOrderId(oldOrderID);
        order.setToNextID();
        assertTrue(order.getOrderId() > oldOrderID);
        assertEquals(oldOrderID + 1, order.getOrderId());
    }

    /**
     * Test that new MenuItems can be added to an Order and are stored properly
     */
    @Test
    public void addToOrderTest() {
        Order order = new Order();
        assertEquals(0, order.getOrderedItems().size());
        order.addToOrder(menuItem);
        assertEquals(1, order.getOrderedItems().size());
        assertSame(menuItem, order.getOrderedItems().get(0));
    }

    /**
     * Test that the right MenuItem is removed from the order, and is actually removed
     */
    @Test
    public void removeFromOrderTest() {
        Order order = new Order();
        int sizeMenuItems = this.menuItems.size();
        for (MenuItem itemToAdd : this.menuItems) {
            order.addToOrder(itemToAdd);
        }
        assertEquals(sizeMenuItems, order.getOrderedItems().size());
        assertTrue(order.getOrderedItems().contains(itemToRemove));
        order.removeFromOrder(itemToRemove);
        assertEquals(sizeMenuItems - 1, order.getOrderedItems().size());
        assertFalse(order.getOrderedItems().contains(itemToRemove));
    }

    /**
     * Test that the static method calculateOrderTest works accurately
     */
    @Test
    public void calculateOrderTest() {
        float calculatedCost = Order.calculateOrder(menuItems);
        assertEquals(cost, calculatedCost, 0.01);
    }
}
