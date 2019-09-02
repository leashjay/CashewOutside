package seng202.team3.model;


import org.joda.money.Money;
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
    private HashMap<Money, Integer> cashFloat;

    /**
     * Constructor for Truck class
     */
    public Truck() throws JAXBException {
        String fName = "./resources/data/Ingredients.xml";
        InventoryLoader inventoryLoad = new InventoryLoader();
        truckInventory = inventoryLoad.loadIngredientsData(fName);
        cashFloat = new HashMap<Money, Integer>();

        // Add denominations into cash float map
        cashFloat.put(Money.parse("NZD 100"), 0);
        cashFloat.put(Money.parse("NZD 50"), 0);
        cashFloat.put(Money.parse("NZD 20"), 0);
        cashFloat.put(Money.parse("NZD 10"), 0);
        cashFloat.put(Money.parse("NZD 5"), 0);
        cashFloat.put(Money.parse("NZD 2"), 0);
        cashFloat.put(Money.parse("NZD 1"), 0);
        cashFloat.put(Money.parse("NZD 0.5"), 0);
        cashFloat.put(Money.parse("NZD 0.2"), 0);
        cashFloat.put(Money.parse("NZD 0.1"), 0);
    }


    /**
     * Calculate the difference of amountTendered and saleOrder
     * and returns the exact denomination for change
     *
     * @param amountTendered total amount paid by customer
     * @param saleOrder      sales ordered by customer
     * @return denomReqd of type HashMap<String, Integer> which records the exact amount of denomination for change
     */
    public HashMap<String, Integer> makeSale(double amountTendered, Order saleOrder) {
        float totalCost = saleOrder.getTotalCost();
        HashMap<String, Integer> denomReqd = new HashMap<String, Integer>();
        if (amountTendered > totalCost) {
            double change = amountTendered - totalCost;
            changeDenom(change, denomReqd);
        }
        return denomReqd;
    }

    /**
     * Called from Sales Screen to add denomination paid by customer accordingly
     *
     * @param denomStr denomination string eg: "NZD 100"
     */
    public void addDenom(String denomStr) {
        Money denomMoney = Money.parse(denomStr);
        cashFloat.put(denomMoney, cashFloat.get(denomMoney) + 1);
    }


    /**
     * Utilise dynamic programming to approach denomination changing problem
     *
     * @param change    amount needed to return to the customer
     * @param denomReqd HashMap<String, integer> storing the denomination for change
     * @return denomReqd which records the denomination for change
     */
    public HashMap<String, Integer> changeDenom(double change, HashMap<String, Integer> denomReqd) {
        //To implement
        return denomReqd;
    }


    public Void cookFood(MenuItem parameter) {
        // TODO implement me
        return null;
    }

}

