package jUnitTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.*;
import seng202.team3.util.PhoneType;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;

import java.util.Date;
import java.util.HashMap;

import static junit.framework.TestCase.*;

public class SupplierHandlerTest {

    private Ingredient beans;
    private Ingredient carrots;
    private Ingredient brocolli;
    private HashMap<String, Supplier> suppliers;
    private Inventory inventory;
    private Supplier countdown;
    private Supplier mootsMeatMarket;
    private Supplier paknsave;

    @Before
    public void setup() {
        beans = new Ingredient("1", "Beans", UnitType.GRAM, ThreeValueLogic.YES, ThreeValueLogic.YES,
                ThreeValueLogic.YES, 20);
        brocolli = new Ingredient("2", "Brocolli", UnitType.COUNT, ThreeValueLogic.YES, ThreeValueLogic.YES,
                ThreeValueLogic.YES, 15);
        carrots = new Ingredient("3", "Carrots", UnitType.COUNT, ThreeValueLogic.YES, ThreeValueLogic.YES,
                ThreeValueLogic.YES, 10);
        inventory = new Inventory();
        suppliers = new HashMap<>();

         countdown = new Supplier("1", "Countdown", "20 Church Corner", PhoneType.WORK, "9593999", "countdown@gmail.com", "www.countdown.com");
         mootsMeatMarket = new Supplier("2", "Moots Meat Market", "52 Riccarton Road", PhoneType.WORK, "9597999", "moots@gmail.com", "www.moots.com");
         paknsave = new Supplier("3", "paknsave", "73 Riccarton Road", PhoneType.HOME, "9599989", "paknsave@gmail.com", "www.paknsave.com");
    }


    @Test
    public void orderFromSupplierTest() {
        suppliers.put(countdown.getSid(), countdown);
        SupplierHandler supplierHandler = new SupplierHandler("Array containing one supplier", suppliers);
        HashMap<Ingredient, Float> orderHashMap = new HashMap<>();
        orderHashMap.put(beans, 20f);
        orderHashMap.put(carrots, 40f);
        orderHashMap.put(brocolli, 50f);
        SupplierOrder supplierOrder = new SupplierOrder(countdown, orderHashMap, new Date());
        supplierHandler.orderFromSupplier(supplierOrder, inventory);
    }

    //TODO agree on whether to use a hashmap or a list for suppliers
    @Test
    public void modifySuppliersTest(){
        HashMap<String, Supplier> suppliers = new HashMap<>();
        SupplierHandler supplierHandler = new SupplierHandler(suppliers);
        supplierHandler.addSupplier(countdown);
        supplierHandler.addSupplier(mootsMeatMarket);
        assertEquals(supplierHandler.getSuppliers().size(), 2);
        supplierHandler.removeSupplier(mootsMeatMarket.getSid());
        assertFalse(supplierHandler.getSuppliers().containsKey(mootsMeatMarket));
        System.out.println(supplierHandler.getSuppliers().size());
        assertEquals(supplierHandler.getSuppliers().size(), 1);
        supplierHandler.addSupplier(paknsave);
        assertEquals(2, supplierHandler.getSuppliers().size());
    }

    @Test
    public void replaceSuppliersTest(){
        HashMap<String, Supplier> oldSuppliers = new HashMap<>();
        SupplierHandler supplierHandler = new SupplierHandler(suppliers);
        supplierHandler.addSupplier(countdown);
        supplierHandler.addSupplier(mootsMeatMarket);

        HashMap<String, Supplier> newSuppliers = new HashMap<>();
        newSuppliers.put(paknsave.getSid(), paknsave);
        supplierHandler.setSuppliers(newSuppliers);

        assertEquals(supplierHandler.getSuppliers().size(), 1);
        assertTrue(supplierHandler.getSuppliers().containsValue(paknsave));
        assertFalse(supplierHandler.getSuppliers().containsValue(countdown));
        assertFalse(supplierHandler.getSuppliers().containsValue(mootsMeatMarket));



    }





}
