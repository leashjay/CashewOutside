package jUnitTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.*;
import seng202.team3.util.PhoneType;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SupplierHandlerTest {

    private Ingredient beans;
    private Ingredient carrots;
    private Ingredient brocolli;
    private ArrayList<Supplier> suppliers;
    private Inventory inventory;

    @Before
    public void setup() {
        beans = new Ingredient("1", "Beans", UnitType.GRAM, ThreeValueLogic.YES, ThreeValueLogic.YES,
                ThreeValueLogic.YES, 20);
        brocolli = new Ingredient("2", "Brocolli", UnitType.COUNT, ThreeValueLogic.YES, ThreeValueLogic.YES,
                ThreeValueLogic.YES, 15);
        carrots = new Ingredient("3", "Carrots", UnitType.COUNT, ThreeValueLogic.YES, ThreeValueLogic.YES,
                ThreeValueLogic.YES, 10);
        inventory = new Inventory();
        suppliers = new ArrayList<Supplier>();

    }


    @Test
    public void orderFromSupplierTest(){
        Supplier supplier = new Supplier("1", "Countdown", "20 Riccarton Road", PhoneType.WORK, "9599999", "countdown@gmail.com", "www.countdown.com");
        suppliers.add(supplier);
        SupplierHandler supplierHandler= new SupplierHandler("Array containing one supplier", suppliers);
        HashMap<Ingredient,Float> orderHashMap = new HashMap<>();
        orderHashMap.put(beans, 20f);
        orderHashMap.put(carrots, 40f);
        orderHashMap.put(brocolli, 50f);
        SupplierOrder supplierOrder = new SupplierOrder(supplier, orderHashMap, new Date());
        supplierHandler.orderFromSupplier(supplierOrder, inventory);
    }



}
