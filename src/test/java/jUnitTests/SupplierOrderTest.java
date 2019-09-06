package jUnitTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Inventory;
import seng202.team3.model.Supplier;
import seng202.team3.model.SupplierOrder;
import seng202.team3.util.PhoneType;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;

import java.util.HashMap;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertNull;

public class SupplierOrderTest {

    private Supplier countdown;
    private Supplier mootsMeatMarket;
    private Ingredient beans;
    private Ingredient carrots;
    private Ingredient brocolli;
    private  HashMap<Ingredient, Float> ingredientFloatHashMap;


    @Before
    public void setup(){
        countdown = new Supplier("1", "Countdown", "20 Church Corner", PhoneType.WORK, "9593999", "countdown@gmail.com", "www.countdown.com");
        mootsMeatMarket = new Supplier("2", "Moots Meat Market", "52 Riccarton Road", PhoneType.WORK, "9597999", "moots@gmail.com", "www.moots.com");
        beans = new Ingredient("1", "Beans", UnitType.GRAM, ThreeValueLogic.YES, ThreeValueLogic.YES,
                ThreeValueLogic.YES, 20);
        brocolli = new Ingredient("2", "Brocolli", UnitType.COUNT, ThreeValueLogic.YES, ThreeValueLogic.YES,
                ThreeValueLogic.YES, 15);
        carrots = new Ingredient("3", "Carrots", UnitType.COUNT, ThreeValueLogic.YES, ThreeValueLogic.YES,
                ThreeValueLogic.YES, 10);
        ingredientFloatHashMap = new HashMap<>();
        ingredientFloatHashMap.put(beans, 5000f);
        ingredientFloatHashMap.put(carrots, 2000f);
        SupplierOrder supplierOrder = new SupplierOrder(countdown,ingredientFloatHashMap);
    }

    @Test
    public void createSupplierOrder(){
        SupplierOrder supplierOrder = new SupplierOrder(countdown, ingredientFloatHashMap);
        assertEquals(supplierOrder.getOrderItems(), ingredientFloatHashMap);
        assertFalse(supplierOrder.getOrderRecieved());
        assertEquals(supplierOrder.getSupplier(), countdown);
        assertNull(supplierOrder.getRecievedDate());

    }

    @Test
    public void receiveSupplierOrder(){
        SupplierOrder supplierOrder = new SupplierOrder(countdown, ingredientFloatHashMap);
        HashMap<String, Ingredient> inventoryIngredients = new HashMap<>();
        Inventory inventory = new Inventory("Inventory", inventoryIngredients);
        HashMap<Ingredient, Float> ingredientFloatHashMap = new HashMap<>();
        ingredientFloatHashMap.put(beans, 5000f);
        inventory.receiveOrder(supplierOrder);
        assertTrue(supplierOrder.getOrderRecieved());
        assertNotNull(supplierOrder.getOrderDate());
        assertNotNull(supplierOrder.getRecievedDate());


    }




}
