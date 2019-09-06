package seng202.team3.model;

import java.util.Date;
import java.util.HashMap;

/**
 * Contains information about orders of ingredients from suppliers
 */
public class SupplierOrder {

    private Supplier supplier;
    private HashMap<Ingredient, Float> orderItems;
    private Date orderDate;
    private Date recievedDate;


    /**
     * Constructor for the SupplierOrder class
     *
     * @param supplier   The supplier that made the order
     * @param orderItems A hashset of the items and quantities in the order
     * @param orderDate  the date at which the order took place
     */
    public SupplierOrder(Supplier supplier, HashMap<Ingredient, Float> orderItems, Date orderDate) {
        this.supplier = supplier;
        this.orderItems = orderItems;
        this.orderDate = orderDate;
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


}
