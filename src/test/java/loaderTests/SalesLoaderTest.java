package loaderTests;

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

import static org.junit.Assert.assertEquals;
import static seng202.team3.util.ItemType.ASIAN;

public class SalesLoaderTest {
    private String fName;
    private SalesLoader testLoader;
    private SalesHandler testSales;

    private Order testOrder;
    private Order testOrder2;

    @Before
    public void populateDBSales() {
        testSales = new SalesHandler();

        testOrder = new Order();
        testOrder.setOrderId(1);
        testOrder.changeStatus(OrderStatus.COMPLETE);
        testOrder.updateFlags();

        HashMap<Ingredient, Float> testIngredients = new HashMap<Ingredient, Float>();

        Ingredient Kimchi = new Ingredient();
        Kimchi.setCode("Kimchi");
        Kimchi.setUnit(UnitType.GRAM);
        Kimchi.setCost((float) 3.2, Kimchi.getQuantity());

        testIngredients.put(Kimchi, (float) 100);
        MenuItem newEntry = new MenuItem("KimchiStew", "A korean cuisine", testIngredients, ASIAN);
        testOrder.addToOrder(newEntry);
        testSales.addOrder(testOrder);

        testOrder2 = new Order();
        testOrder2.setOrderId(2);
        testOrder2.changeStatus(OrderStatus.COOKING);
        testOrder2.updateFlags();
        testOrder2.addToOrder(newEntry);

        testSales.addOrder(testOrder2);
    }

    @Test
    public void testExportSalesXML() throws Exception {
        fName = "./src/main/resources/data/testdata/testExportSale.xml";
        testLoader = new SalesLoader();
        testLoader.exportSalesData(fName, testSales);

        SalesHandler handler = testLoader.loadSalesData("./src/main/resources/data/testdata/testExportSale.xml");

        assertEquals(handler.getOrder(1).getStatus(), testOrder.getStatus());
        assertEquals(handler.getOrder(2).getStatus(), testOrder2.getStatus());
        assertEquals(handler.getOrder(1).getTotalCost(), testOrder.getTotalCost(), 0.01);
        assertEquals(handler.getOrder(2).getTotalCost(), testOrder2.getTotalCost(), 0.01);
    }
}
