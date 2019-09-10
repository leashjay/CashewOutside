package seng202.team3.model;


import seng202.team3.parsing.InventoryLoader;

import javax.xml.bind.JAXBException;
import java.util.HashMap;

/**
 * Joda Money information:
 * https://github.com/JodaOrg/joda-money
 */

/**
 * Truck class holds truck inventory including stock and cash floats
 **/

public class Truck {
    /**
     * Inventory specific to a truck
     */
    private Inventory truckInventory;

    /* cashFloat Key: denomination of type string ("$<denomination")
     *  cashFloat Value: denomination count of type int
     */
    private static HashMap<Integer, Integer> cashFloat;

    /**
     * Constructor for Truck class
     */
    public Truck() throws JAXBException {
        String fName = "./resources/data/Ingredients.xml";
        InventoryLoader inventoryLoad = new InventoryLoader();
        truckInventory = inventoryLoad.loadIngredientsData(fName);
        cashFloat = new HashMap<Integer, Integer>();

        // Add denominations (to nearest cent) into cash float map
        cashFloat.put(10000, 10);
        cashFloat.put(5000, 10);
        cashFloat.put(2000, 10);
        cashFloat.put(1000, 10);
        cashFloat.put(500, 10);
        cashFloat.put(200, 10);
        cashFloat.put(100, 10);
        cashFloat.put(50, 10);
        cashFloat.put(20, 10);
        cashFloat.put(10, 10);
    }

    /**
     * Called from Sales Screen to add denomination paid by customer accordingly
     *
     * @param denomStr denomination string eg: "NZD 100"
     */
    public void addDenom(String denomStr) {
        int denom;
        switch (denomStr) {
            case "NZD 100":
                denom = 10000;
                break;
            case "NZD 50":
                denom = 5000;
                break;
            case "NZD 20":
                denom = 2000;
                break;
            case "NZD 10":
                denom = 1000;
                break;
            case "NZD 5":
                denom = 500;
                break;
            case "NZD 2":
                denom = 200;
                break;
            case "NZD 1":
                denom = 100;
                break;
            case "NZD 0.5":
                denom = 50;
                break;
            case "NZD 0.2":
                denom = 20;
                break;
            case "NZD 0.1":
                denom = 10;
                break;
            default:
                denom = 00;
        }
        cashFloat.put(denom, cashFloat.get(denom) + 1);
    }
    
}

