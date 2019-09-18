package jUnitTests;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Ingredient;
import seng202.team3.model.MenuItem;
import seng202.team3.model.Order;
import seng202.team3.model.SalesHandler;
import seng202.team3.parsing.SalesLoader;
import seng202.team3.util.OrderStatus;
import seng202.team3.util.UnitType;

import java.util.HashMap;

import static seng202.team3.util.ItemType.ASIAN;

public class SalesLoaderTest {
    private String fName;
    private SalesLoader testLoader;
    private SalesHandler testSales;

    @Before
    public void populateDBSales() {
        testSales = new SalesHandler();
        Order order1 = new Order();
        order1.setOrderId(1);
        order1.changeStatus(OrderStatus.COMPLETE);
        order1.updateFlags();
        HashMap<Ingredient, Float> testIngredients = new HashMap<Ingredient, Float>();
        Ingredient Kimchi = new Ingredient();
        Kimchi.setCode("Kimchi");
        Kimchi.setUnit(UnitType.GRAM);
        Kimchi.setCost((float) 3.2);
        testIngredients.put(Kimchi, (float) 100);
        MenuItem newEntry = new MenuItem("KimchiStew", "A korean cuisine", testIngredients, ASIAN);
        order1.addToOrder(newEntry);
        testSales.addOrder(order1);
    }

    @Test
    public void testExportSalesXML() throws Exception {
        fName = "./resources/data/testdata/testSales.xml";
        testLoader = new SalesLoader();
        testLoader.exportIngredientsData(fName, testSale);
    }
}
