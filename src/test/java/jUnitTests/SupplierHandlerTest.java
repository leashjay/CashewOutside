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
    private ArrayList<Supplier> suppliers;
    private Inventory inventory;

    @Before
    public void setup() {
        Ingredient beans = new Ingredient("1", "Beans", 3, UnitType.GRAM, ThreeValueLogic.YES, ThreeValueLogic.YES,
                ThreeValueLogic.YES);
        inventory = new Inventory();

    }


    @Test
    public void orderFromSupplierTest(){
        Supplier supplier = new Supplier("1", "Countdown", "20 Riccarton Road", PhoneType.WORK, "9599999", "countdown@gmail.com", "www.countdown.com");
        suppliers.add(supplier);
        SupplierHandler supplierHandler= new SupplierHandler("Array containing one supplier", suppliers);
        HashMap<Ingredient,Float> orderHashMap = new HashMap<>();
        orderHashMap.put(beans, 20f);
        SupplierOrder supplierOrder = new SupplierOrder(supplier, orderHashMap, new Date());
        supplierHandler.orderFromSupplier(supplierOrder, inventory);
    }

//    @Test
//    public void supplierCreationTest(){
//        Supplier supplier = new Supplier("1", "Countdown", "20 Riccarton Road", PhoneType.WORK, "9599999", "countdown@gmail.com", "www.countdown.com");
//    ArrayList SupplierHandler = new SupplierHandler("Array containing one supplier", new ArrayList<Supplier>(supplier));
//    }



}
