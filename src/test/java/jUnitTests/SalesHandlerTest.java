package jUnitTests;

import org.junit.Test;
import seng202.team3.model.Order;
import seng202.team3.model.SalesHandler;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;

public class SalesHandlerTest {

    @Test
    public void addOrderTest() {
        Order order1 = new Order();
        order1.setOrderId(100);
        SalesHandler salesHandler = new SalesHandler();
        salesHandler.addOrder(order1);
        assertSame(salesHandler.getOrder(100), order1);
        assertEquals(1, salesHandler.getOrderHashMap().size());
        Order order2 = new Order();
        order2.setOrderId(100);
        try {
            salesHandler.addOrder(order2);
            throw new AssertionError("sales Handler was able to add order of same ID");
        } catch (Error e) {
            assertEquals("Order of same orderId already present in SalesHandler.orders", e.getMessage());
        }
    }


    @Test
    public void getChangeTest() {
        float change = SalesHandler.getChange(100, 49);
        assertEquals(51, change, 0.01);
    }

}
