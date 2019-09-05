package seng202.team3.model;

import seng202.team3.util.OrderStatus;

import java.util.HashMap;

public class SalesHandler {
    private HashMap<Integer, Order> orders = new HashMap<>(); // Orders keyed to their orderId

    /**
     *
     * @param orderToAdd the Order to be added to the orders HashMap
     */
    public void addOrder(Order orderToAdd) {
        int key = orderToAdd.getOrderId();
        if (!this.orders.containsKey(key)) {
            this.orders.put(key, orderToAdd);
        } else {
            throw new Error("Order of same orderId already present in SalesHandler.orders");
        }
    }

    public void removeOrder(Integer idToRemove) {
        this.orders.remove(idToRemove);
    }

    public void refundOrder(Integer idToRefund) throws Error {
        Order orderToRefund = this.orders.get(idToRefund);
        boolean refundSuccess = processRefund(orderToRefund);
        if (refundSuccess) {
            orderToRefund.changeStatus(OrderStatus.REFUNDED);
        } else {
            throw new Error("Order unable to be Refunded.");
        }
    }

    public Order getOrder(Integer id) {
        return this.orders.get(id);
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
