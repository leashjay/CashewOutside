package seng202.team3.model;

import seng202.team3.parsing.SalesLoader;
import seng202.team3.util.OrderStatus;
import seng202.team3.view.BusinessApp;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;

/**
 * Holds list of orders stored
 */

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.NONE)
public class SalesHandler {
    /**
     * SalesLoader object to import/export method
     */
    private SalesLoader salesLoader;

    public HashMap<Integer, Order> orders = new HashMap<>(); // Orders keyed to their orderId

    private float cashAccount;

    /**
     * The orders that are to be displayed in the kitchen window
     */
    public HashMap<Integer, Order> displayOrders = new HashMap<>(); // Orders keyed to their orderId


/*    @XmlElement(name = "cashAccount")
    public float getCashAccountFromTruck() {
        cashAccount = Truck.cashAccount;
        return cashAccount;
    }*/

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
            this.displayOrders.put(order.getOrderId(), order);
            this.orders.put(order.getOrderId(), order);
        }
    }

    public HashMap<Integer, Order> getOrdersHashMap() {
        return this.orders;
    }

    public HashMap<Integer, Order> getDisplayOrdersHashMap() {
        return this.displayOrders;
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
            this.displayOrders.put(key, orderToAdd);
            this.orders.put(key, orderToAdd);
        } else {
            throw new Error("Order of same orderId already present in SalesHandler.orders");
        }
    }

    /**
     * Getter for orders
     *
     * @return orders
     */
    public HashMap<Integer, Order> getOrderHashMap() {
        return orders;}
    public void removeOrder(Integer idToRemove) {
        this.orders.remove(idToRemove);
    }
    public void removeDisplayOrder(Integer idToRemove) {
        this.displayOrders.remove(idToRemove);
    }

    public float refundOrder(Integer idToRefund) throws Error {
        Order orderToRefund = this.orders.get(idToRefund);
        boolean refundSuccess = processRefund(orderToRefund);
        if (refundSuccess) {
            orderToRefund.changeStatus(OrderStatus.REFUNDED);
        } else {
            throw new Error("Order unable to be Refunded.");
        }
        return orderToRefund.getTotalCost();
    }


    public Order getOrder(Integer id) {
        return this.orders.get(id);
    }

    private boolean processRefund(Order orderToProcess) {
        boolean success = true;
        float cost = orderToProcess.getTotalCost();
        success = BusinessApp.getBusiness().getTruck().hasEnoughCash(cost);
        if (success && orderToProcess.canBeRefunded()) {
            BusinessApp.getBusiness().getTruck().decreaseCashFloat(cost);
            orderToProcess.refund();
        } else {
            success = false;
        }

        return success;
    }

    /**
     * customer pays for an order
     * @param amountPaid the amount of money the customer pays
     * @param orderId the order to pay for
     * @param truck the truck that takes the order
     */
    public float customerPays(float amountPaid, int orderId, Truck truck) {
        Order customerOrder = this.getOrder(orderId);
        float price = customerOrder.getTotalCost();
        // TODO change below line to a truck field for nicer testing
        truck.increaseCashFloat(price);
        return calculateChange(amountPaid, orderId);
    }

    /**\
     * small method to get the amount of change
     * @param amountPaid the amount of money paid by the customer
     * @param orderCost the cost of the order
     * @return change - can be negative (error checking not handled here)
     */
    public static float getChange(float amountPaid, float orderCost) {
        return amountPaid - orderCost;
    }

    private float calculateChange(float amountPaid, int orderId) {
        Order order = orders.get(orderId);
        return amountPaid - order.getTotalCost();
    }

    /**
     * Add order data from XML
     *
     * @param file
     */
    public void addOrdersFromXML(String file) throws JAXBException {
        salesLoader = new SalesLoader();
        SalesHandler salesHandler = salesLoader.loadSalesData(file);
        if (orders != null) {
            HashMap<Integer, Order> newOrders = salesHandler.getOrderHashMap();
            orders.putAll(newOrders);
        }

    }


}
