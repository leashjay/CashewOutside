package seng202.team3.model;


import seng202.team3.parsing.InventoryLoader;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


/**
 * Truck class holds truck inventory including stock and cash floats
 **/
@XmlRootElement(name = "truck")
@XmlAccessorType(XmlAccessType.NONE)
public class Truck {
    /**
     * cashFloat Key: denomination of type int (round to the nearest hundredth)
     * cashFloat Value: denomination count of type int
     */
    private static HashMap<Integer, Integer> cashFloat;
    /**
     * Inventory specific to a truck
     */
    private Inventory truckInventory;

    @XmlElement(name = "cashAccount")
    private float cashAccount;

    public Truck() {
    }

    /**
     * Constructor for Truck class
     * @param ingredientsXML path to ingredientsXML
     * @throws Exception
     */
    public Truck(String ingredientsXML) throws JAXBException {
        createTruckInventory(ingredientsXML);
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

        cashAccount += 500;
    }

    /**
     * Create truckInventory
     *
     * @param fileName path to ingredientsXML
     * @throws Exception
     */
    public void createTruckInventory(String fileName) throws JAXBException {
        InventoryLoader inventoryLoad = new InventoryLoader();
        truckInventory = inventoryLoad.loadIngredientsData(fileName);
    }

    /**
     * Getter for truck inventory
     *
     * @return truck inventory
     */
    public Inventory getInventory() {
        return truckInventory;
    }

    /**
     * Getter for cash float
     *
     * @return cashFloat
     */
    public HashMap<Integer, Integer> getCashFloat() {
        return cashFloat;
    }

    /** public static
     * Getter for cash account
     * @return cashAccount
     */
    public float getCashAccount() {
        return cashAccount;
    }


    /**
     * uses a greedy algorithm to increase the Truck's cashFloat
     * @param totalAmountOfIncrease the total amount of money in dollars.cents
     */
    public void increaseCashFloat(float totalAmountOfIncrease) {
        int increaseInCents = (int) (totalAmountOfIncrease * 1);
        ArrayList<Integer> denoms = new ArrayList<>(cashFloat.keySet());
        Collections.sort(denoms); // sorts the denoms from smallest to largest
        Collections.reverse(denoms);
        // takes the biggest of each denom until it no longer can.
        for (int denom : denoms) {
            while (denom >= increaseInCents) {
                cashFloat.put(denom, cashFloat.get(denom) + 1);
                increaseInCents -= denom;
            }
        }
        // always make the customer pay extra when using cash
        if (increaseInCents > 0 && increaseInCents < 10) {
            cashFloat.put(10, cashFloat.get(10) + 1);
        }
    }
}