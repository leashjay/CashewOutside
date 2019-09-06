package seng202.team3.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains information about orders of ingredients from suppliers
 */
public class SupplierOrder {

    private Supplier supplier;
    private HashMap<Ingredient, Float> orderItems;
    private Date orderDate;
    private Date recievedDate;
    private boolean orderRecieved;



    /**
     * Constructor for the SupplierOrder class
     *
     * @param supplier   The supplier that made the order
     * @param orderItems A hashset of the items and quantities in the order
     */
    public SupplierOrder(Supplier supplier, HashMap<Ingredient, Float> orderItems) {
        this.supplier = supplier;
        this.orderItems = orderItems;
        orderDate = new Date();
        orderRecieved = false;
        recievedDate = null;
    }

    /**
     * Returns a hashmap showing items in the order and their quantities
     *
     * @return a hashmap showing items in the order and their quantities
     */
    public HashMap<Ingredient, Float> getOrderItems() {
        return orderItems;
    }

    /**
     * Returns the date which the order was made at
     *
     * @return The date the order was made at
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Returns the supplier that made the order
     *
     * @return the supplier that made the order
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Sets the date at which the order has been recieved
     * @param recievedDate the date at which the order was recieved
     */
    public void setReceivedDate(Date recievedDate) {
        this.recievedDate = recievedDate;
    }

    /**
     * Sets whether the order has been recieved or not
     * @param hasBeenReceived boolean showing if the order has been recieved
     */
    public void setBeenReceived(boolean hasBeenReceived){
        orderRecieved = hasBeenReceived;
    }

    /**
     * Gets the date at which the order was recieved
     * @return if the order has been recieved
     */
    public boolean getOrderReceived(){
        return orderRecieved;
    }

    /**
     *Gets the date at which the order was received
     * @return the date at which the order was received
     */
    public Date getRecievedDate(){
        return recievedDate;
    }

    public String getOrderAsString(){
        String orderInfo = "Order has been made from " + supplier + "\n";
        for (Map.Entry<Ingredient, Float> entry : orderItems.entrySet()) {
            Ingredient ingredient = entry.getKey();
            orderInfo += ingredient.getName() + ":  " + entry.getValue() + "\n";
        }
        return orderInfo;
    }


}
