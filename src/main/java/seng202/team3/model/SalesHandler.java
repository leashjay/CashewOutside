package seng202.team3.model;

import org.apache.commons.lang3.ObjectUtils;
import seng202.team3.util.OrderStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class SalesHandler {
    public HashMap<Integer, Order> orders = new HashMap<Integer, Order>();

    public void addOrder(Order orderToAdd) {
        int key = orderToAdd.getOrderId();
        if (!this.orders.containsKey(key)) {
            this.orders.put(key, orderToAdd);
        } else {
            throw new Error("Order of same orderId already present in SalesHandler.orders");
        }
    }

    public void removeOrder(Order orderToRemove) {

        this.orders.remove(orderToRemove.getOrderId());
    }

    public void refundOrder(Order orderToRefund) throws Error {

        boolean refundSuccess = processRefund(orderToRefund);
        if (refundSuccess) {
            orderToRefund.changeStatus(OrderStatus.REFUNDED);
        } else {
            throw new Error("Order unable to be Refunded.");
        }
    }

    private boolean processRefund(Order orderToProcess) {
        // TODO ask team about how our money system works.
        // Unsure what could be involved in processing the refund...
        boolean success = false;
        if (orderToProcess.getStatus() != OrderStatus.REFUNDED) {
            // confirmRefund();
            success = true; // placeholder
        }

        return success;
    }

    public void customerPays(float amountPaid, int orderId) {
        // TODO implement this method fully.
        Order order = orders.get(orderId);
        if (amountPaid > order.getTotalCost()) {
            // increaseCash(amountPaid);
        } else {
            System.out.println("Not enough money");
        }
        throw new Error("Unimplemented Method: customerPays");
    }

    public float calculateChange(float amountPaid, int orderId) {
        // TODO not allow negative values? depends on what is calling it.
        Order order = orders.get(orderId);
        return amountPaid - order.getTotalCost();
    }
}
