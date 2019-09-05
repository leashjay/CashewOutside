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
        cashFloat.put(1000, 0);
        cashFloat.put(500, 0);
        cashFloat.put(200, 0);
        cashFloat.put(100, 0);
        cashFloat.put(50, 0);
        cashFloat.put(20, 0);
        cashFloat.put(10, 0);
        cashFloat.put(5, 0);
        cashFloat.put(2, 0);
        cashFloat.put(1, 0);
    }


    /**
     * Calculate the difference of amountTendered and saleOrder
     * and returns the exact denomination for change
     *
     * @param amountTendered total amount paid by customer
     * @param saleOrder      sales ordered by customer
     * @return denomReqd of type HashMap<String, Integer> which records the exact amount of denomination for change
     */
//    public HashMap<String, Integer> makeSale(double amountTendered, Order saleOrder) {
//        float totalCost = saleOrder.getTotalCost();
//        if (amountTendered > totalCost) {
//            double change = amountTendered - totalCost;
//            int changeInt = (int) change;
//            changeDenom(changeInt);
//        }
//        return denomReqd;
//    }

    /**
     * Called from Sales Screen to add denomination paid by customer accordingly
     *
     * @param denomStr denomination string eg: "NZD 100"
     */
    public void addDenom(String denomStr) {
        int denom;
        switch (denomStr) {
            case "NZD 100":
                denom = 1000;
                break;
            case "NZD 50":
                denom = 500;
                break;
            case "NZD 20":
                denom = 200;
                break;
            case "NZD 10":
                denom = 100;
                break;
            case "NZD 5":
                denom = 50;
                break;
            case "NZD 2":
                denom = 20;
                break;
            case "NZD 1":
                denom = 10;
                break;
            case "NZD 0.5":
                denom = 5;
                break;
            case "NZD 0.2":
                denom = 2;
                break;
            case "NZD 0.1":
                denom = 1;
                break;
            default:
                denom = 0;
        }
        cashFloat.put(denom, cashFloat.get(denom) + 1);
    }


    /**
     * Utilise dynamic programming to approach denomination changing problem
     *
     * @param change    amount needed to return to the customer
     * @param denomReqd HashMap<String, integer> storing the denomination for change
     * @return denomReqd which records the denomination for change
     */
//    public static HashMap<String, Integer> changeDenom(Integer change) {
//
//        ArrayList<Integer> coinage = new ArrayList<Integer>(new TreeSet(cashFloat.keySet()));
//
//        System.out.println(coinage);
//
//        long[] table = new long[change+1];
//        Arrays.fill(table, 0);
//
//        table[0] = 1;
//
//        for (int i=0; i<coinage.size(); i++) {
//            for (int j=coinage.get(i); j<change; j++ ) {
//                table[j] += table[j - coinage.get(i)];
//            }
//        }
//
//
//        System.out.println(Arrays.toString(table));
//
//        return null;
//    }


//    public static void main(String args[]) throws JAXBException{
//        Truck newTruck = new Truck();
//        HashMap<String, Integer> sth = changeDenom(325);
//
//
//    }

    public Void cookFood(MenuItem parameter) {
        // TODO implement me
        return null;
    }

}

