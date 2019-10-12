package seng202.team3.model;


import seng202.team3.parsing.InventoryLoader;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Truck class holds truck inventory including stock and cash floats
 **/
@XmlRootElement(name = "truck")
@XmlAccessorType(XmlAccessType.NONE)
public class Truck {

    /**
     * Inventory specific to a truck
     */
    private Inventory truckInventory;

    /** Cash float in business */
    @XmlElement(name = "cashAccount")
    private float cashAccount;

    /**
     * No-arg constructor for JAXB
     */
    public Truck() { }


    public Truck(String xmlFile){

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


    /** public static
     * Getter for cash account, cash account is the money held
     * by each (the) truck and used for daily transactions
     * @return cashAccount
     */
    public float getCashAccount() {
        return cashAccount;
    }


    /**
     * setting the value in the cash account held by the truck for
     * daily operations
     *
     * @param newValue passed value to set account dollar value
     */
    public void setCashAccount(float newValue) {
        cashAccount = newValue;
    }


    /**
     * Increase Truck's cash Float
     * @param totalAmountOfIncrease the total amount of money in dollars.cents
     */
    public void increaseCashFloat(float totalAmountOfIncrease) {
        cashAccount += totalAmountOfIncrease;
    }

    /**
     * decreases the cashAccount by the given amount if possible
     * @param decreaseAmount the amount to decrease the cashAccount by
     */
    public void decreaseCashFloat(float decreaseAmount) {
        if (this.hasEnoughCash(decreaseAmount)) {
            cashAccount -= decreaseAmount;
        } else {
            throw new Error("Not enough cash in truck");
        }
    }

    /**
     * Check if cash float is capable to decrease a certain amount of cash
     * @param decreaseAmount certain amount of cash to be decreased
     * @return true if cash float is enough, false otherwise
     */
    public boolean hasEnoughCash(float decreaseAmount) {
        return decreaseAmount <= cashAccount;
    }
}