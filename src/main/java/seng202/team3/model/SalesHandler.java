package seng202.team3.model;

import seng202.team3.util.OrderStatus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.NONE)
public class SalesHandler {

    public HashMap<Integer, Order> orders = new HashMap<>(); // Orders keyed to their orderId

    /**
     * Used for JAXB custom serialization
     *
     * @return The Orders object to be serialized
     */
    @XmlElement(name = "orders")
    private Orders getOrders() {
        return new Orders(List.copyOf(orders.values()));
    }

    /**
     * Used for JAXB custom deserialization
     *
     * @param orders The Orders object to be deserialized
     */
    @XmlElement(name = "orders")
    private void setOrders(Orders orders) {
        for (Order order : orders.order) {
            this.orders.put(order.getOrderId(), order);
        }
    }

    /**
     * Container class to allow JAXB serialization/deserialization
     */
    static class Orders {
        public List<Order> order;

        public Orders() {
        }

        Orders(List<Order> order) {
            this.order = order;
        }
    }

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
