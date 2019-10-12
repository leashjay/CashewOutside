package jUnitTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.*;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.view.BusinessApp;

import javax.xml.bind.JAXBException;

import static junit.framework.TestCase.assertEquals;

public class BusinessTest {
    private Business testBusiness;
    private Inventory testInventory;
    private Menu testMenu;
    private Ingredient onion;

    /**
     * Initialising a business instance for test execution
     *
     * @throws JAXBException
     */
    @Before
    public void setUpBusiness() throws JAXBException {
        testBusiness = new Business("./src/main/resources/data/Ingredients.xml", "./src/main/resources/data/SampleMenu.xml", BusinessApp.suppliersXML, BusinessApp.salesXML, BusinessApp.employeeXML, BusinessApp.truckXML);
    }

    /**
     * Check if ingredient data was persisted after loading ingredient and menu xml
     */
    @Test
    public void checkIngredientAttribute() {
        testInventory = testBusiness.getTruck().getInventory();
        onion = testInventory.getIngredients().get("Onion");

        assertEquals("Quantity of onion in stock = 12.5g", 12.5f, onion.getQuantity());
        assertEquals("Cost of onion in stock = NZD 0.5 ", 0.5f, onion.getCost());
        assertEquals("Onion is Vegetarian", ThreeValueLogic.YES, onion.getIsVegetarian());
    }

    /**
     * Check if menu item data was persisted after loading ingredient and menu xml
     */
    @Test
    public void checkMenuItemAttribute() {
        testMenu = testBusiness.getMenuManager();
        MenuItem beefBurger = testMenu.getMenuItem().get("BB1");
        float quantityNeeded = 0;
        for (Ingredient ing: beefBurger.getIngredients().keySet()) {
            if (ing.getCode().equals("Onion")) {
                quantityNeeded = beefBurger.getIngredients().get(ing);
            }
        }
        assertEquals("Quantity of onion needed for BB1 is 50g", 50f, quantityNeeded);
    }

    @Test
    /**
     * Tests the functionality of checking and getting the ID of next order the business will make
     */
    public void orderIdTest(){
        int lastOrderId = testBusiness.getLastOrderID();
        testBusiness.makeNextOrderID();
        testBusiness.makeNextOrderID();
        assertEquals(testBusiness.getLastOrderID(), lastOrderId + 2);
    }
}
