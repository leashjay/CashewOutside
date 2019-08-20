package seng202.team3.model;


import java.util.HashMap;

/**
 * Truck class holds truck inventory including stock and cash floats
 **/

public class Truck {
    private Inventory truckInventory;

    // cashFloat Key: denomination of type string ("$<denomination")
    // cashFloat Value: denomination count of type int
    private HashMap<String, Integer> cashFloat;

    /**
     * Constructor for Truck class
     */
    public Truck() {
        truckInventory = new Inventory();
        cashFloat = new HashMap<String, Integer>();

        // Add denominations into cash float map
        cashFloat.put("$100", 0);
        cashFloat.put("$50", 0);
        cashFloat.put("$20", 0);
        cashFloat.put("$10", 0);
        cashFloat.put("$5", 0);
    }


    public Void makeSale(MenuItem parameter) {
        // TODO implement me
        return null;
    }


    public Void cookFood(MenuItem parameter) {
        // TODO implement me
        return null;
    }

}

