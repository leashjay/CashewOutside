package seng202.team3.model;


import seng202.team3.parsing.DateAdapter;
import seng202.team3.parsing.TimeAdapter;
import seng202.team3.util.OrderStatus;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.view.BusinessApp;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Order class to store details of order
 */

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.NONE)
public class Order {
    // TODO make Order Observable so Sales is dynamically updated when MenuItems are added etc.
    /**
     * Order status of the order (uses OrderStatus enum)
     **/
    @XmlElement(name = "orderStatus")
    public OrderStatus orderStatus;
    /**
     * Date of order made
     **/
    @XmlAttribute(name = "dateOrdered")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate dateOrdered;
    /** Time of order made **/
    @XmlAttribute(name = "timeOrdered")
    @XmlJavaTypeAdapter(TimeAdapter.class)
    private LocalTime timeOrdered;
    /** Order id **/
    @XmlElement(name = "orderId")
    private int orderId; // should be unique across multiple orders
    /** Name of customer who made the order **/
    @XmlElement(name = "customerName")
    private String name = "";
    /** Total order cost **/
    @XmlElement(name = "orderCost")
    private float orderCost;

    /** Total order cost at time of payment **/
    @XmlElement(name = "costAtTimeOfPayment")
    private float costAtTimeOfPayment = -1;

    /** Menu items ordered **/
    @XmlElement(name = "itemsOrdered")
    private ArrayList<MenuItem> itemsOrdered = new ArrayList<>();

    /** isGFFlag for order **/
    @XmlAttribute(name = "isGFFlag")
    private ThreeValueLogic isGFFlag = ThreeValueLogic.YES;

    /** isVegFlag for order **/
    @XmlAttribute(name = "isVegFlag")
    private ThreeValueLogic isVegFlag = ThreeValueLogic.YES;

    /** isVehanFlag for order **/
    @XmlAttribute(name = "isVeganFlag")
    private ThreeValueLogic isVeganFlag = ThreeValueLogic.YES;

    /** Boolean to indicate if dietary flag of the order has been checked **/
    @XmlElement(name = "flagsChecked")
    private boolean flagsChecked = true;


    /**
     * No-arg constructor for JAXBN
     */
    public Order() {
        super();
    }



    /**
     * Secreases the stock level according to the items in the Order
     */
    public void decreaseStock() {
        if (!enoughStock()) {
            throw new Error("Not enough stock");
        }
        for (MenuItem menuItem : this.itemsOrdered) {
            menuItem.decreaseStock();
        }
    }

    /**
     * helper of decreaseStock, returns true if there is enough stock to place the order
     * @return has enough stock
     */
    public boolean enoughStock() {
        for (MenuItem menuItem : this.itemsOrdered) {
            if (!menuItem.hasEnoughStock()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Set newly made order id to the next number to prevent overwriting past orders
     */
    public void setToNextID() {
        this.orderId = BusinessApp.getBusiness().makeNextOrderID();
    }

    /**
     * Get time of order made
     * @return timeOrdered
     */
    public LocalTime getTime() {
        return this.timeOrdered;
    }

    /**
     * Set time of order made
     * @param time of order made
     */
    public void setTime(LocalTime time) {
        this.timeOrdered = time;
    }

    /**
     * calculates the cost of an order from a given list of MenuItems
     *
     * @param itemsToCalculate the MenuItems
     * @return the total cost            if (stockDecreasedSuccessfully) {
     */
    public static float calculateOrder(ArrayList<MenuItem> itemsToCalculate) {
        float cost = 0;
        for (MenuItem item : itemsToCalculate) {
            cost += item.calculateSalePrice();
        }
        return cost;
    }

    /**
     * Getter for totalCost, if the order has been paid for then
     * returns the cost of the order at the time of payment
     *
     * @return totalCostcostA
     */
    public float getTotalCost() {
        if (this.costAtTimeOfPayment != -1) {
            return (float) (Math.round(this.costAtTimeOfPayment * 100.0) / 100.0);
        }
        return (float) (Math.round(orderCost * 100.0) / 100.0);
    }


    /**
     * Getter for order id
     * @return orderId
     */
    public int getOrderId() {
        // TODO throw an error if no orderId
        return this.orderId;
    }

    /**
     * Setter for order Id
     * @param newOrderId to be set
     */
    public void setOrderId(int newOrderId) {
        this.orderId = newOrderId;
    }

    /**
     * Getter for costAtTimeOfPayment
     *
     * @return costAtTimeOfPayment
     */
    public float getCostAtTimeOfPayment() {
        return costAtTimeOfPayment;
    }

    /**
     * Getter for itemsOrdered size
     *
     * @return size of items ordered
     */
    public int getNumItems() {
        return this.itemsOrdered.size();
    }



    /**
     * Getter for list of menu items ordered
     *
     * @return itemsOrdered
     */
    public ArrayList<MenuItem> getOrderedItems() {
        return this.itemsOrdered;
    }

    /**
     * Getter for order status
     *
     * @return orderStatus
     */
    public OrderStatus getStatus() {
        return this.orderStatus;
    }

    /**
     * Getter for customer's name
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for customer's name
     *
     * @param name customer's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for date of order made
     *
     * @return dateOrdered
     */
    public LocalDate getDate() {
        return this.dateOrdered;
    }


    /**
     * Setter for date of order made
     *
     * @param newDate date of order made
     */
    public void setDate(LocalDate newDate) {
        this.dateOrdered = newDate; }



    /**
     * Adds a given MenuItem to the itemsOrdered and it's cost to the orderCost.
     * The flags for isGF, isVeg and isVegan are also updated accordingly.
     * @param itemToAdd menu item to be added
     */
    public void addToOrder(MenuItem itemToAdd) {
        // TODO implement me
        this.isGFFlag = getFlagAdding(this.isGFFlag, itemToAdd.isGlutenFree());
        this.isVegFlag = getFlagAdding(this.isVegFlag, itemToAdd.isVegetarian());
        this.isVeganFlag = getFlagAdding(this.isVeganFlag, itemToAdd.isVegan());
        this.itemsOrdered.add(itemToAdd);
        this.orderCost += itemToAdd.calculateSalePrice();
    }

    /**
     * Helper of addToOrder and ***,
     * Updates the flagFromOrder based on flagFromMenu,
     * it cannot make a flag that wasn't YES: YES nor a flag that is NO not NO
     * @param flagFromOrder the flag that will be updated from the Order
     * @param flagFromMenu the flag from the MenuItem to check against
     */
    private ThreeValueLogic getFlagAdding(ThreeValueLogic flagFromOrder, ThreeValueLogic flagFromMenu) {
        if (flagFromMenu != ThreeValueLogic.YES && flagFromOrder != ThreeValueLogic.NO) {
            return flagFromMenu;
        } else {
            return flagFromOrder;
        }
    }

    /**
     * Helper of removeFromOrder
     * Updates the flagFromOrder based on flagFromMenu
     * @param flagFromOrder the flag that will be updated from the Order
     * @param flagFromMenu the flag from the MenuItem to check against
     * @return
     */
    private ThreeValueLogic getFlagRemoving(ThreeValueLogic flagFromOrder, ThreeValueLogic flagFromMenu) {
        if (flagFromMenu == ThreeValueLogic.YES) {
            return flagFromOrder;
        } else {
            this.flagsChecked = false;
            return ThreeValueLogic.UNKNOWN;
        }
    }


    /**
     * Removes a given item from the itemsOrdered and removes it's cost, if it was present.
     * @param itemToRemove item to be removed
     */
    public void removeFromOrder(MenuItem itemToRemove) {
        // TODO implement me
        boolean removalSuccess = this.itemsOrdered.remove(itemToRemove);
        if (removalSuccess) {
            this.isGFFlag = getFlagRemoving(this.isGFFlag, itemToRemove.isGlutenFree());
            this.isVegFlag = getFlagRemoving(this.isVegFlag, itemToRemove.isVegetarian());
            this.isVeganFlag = getFlagRemoving(this.isVeganFlag, itemToRemove.isVegan());
            if (!flagsChecked) {
                updateFlags();
            }
            this.orderCost -= itemToRemove.calculateSalePrice();
        }
    }

    /**
     * ensures all of the gluten free, vegetarian and vegan flags are correct
     */
    public void updateFlags() {
        this.isGFFlag = ThreeValueLogic.YES;
        this.isVegFlag = ThreeValueLogic.YES;
        this.isVeganFlag = ThreeValueLogic.YES;
        for (MenuItem menuItem : this.itemsOrdered) {
            this.isGFFlag = getFlagAdding(this.isGFFlag, menuItem.isGlutenFree());
            this.isVegFlag = getFlagAdding(this.isVegFlag, menuItem.isVegetarian());
            this.isVeganFlag = getFlagAdding(this.isVeganFlag, menuItem.isVegan());
        }
        flagsChecked = true;
    }

    /**
     * changes an order's status to the new status,
     * does not perform any error checking.
     * i.e. shouldn't go from COMPLETE to QUEUED
     * @param newStatus
     */
    public void changeStatus(OrderStatus newStatus) {
        // TODO implement me
        this.orderStatus = newStatus;
    }

    public void refund() {}


    /**
     * Confirms the Order, setting relevant values for right now.
     * And decreases the stock.
     */
    public void confirmOrder() {
        this.setTime(LocalTime.now());
        this.setDate(LocalDate.now());
        this.changeStatus(OrderStatus.QUEUED);
        this.setPaidCost(this.orderCost);
        this.decreaseStock();
    }

    /**
     * Used for refunding, and displaying the amount the customer paid for the order at time of payment.
     * It is important to have this value separate from the calculated cost according to its menuItems.
     * I.e. if the price paid was discounted or the cost of anything has changed between now and then.
     * @param costAtTimeOfPayment
     */
    public void setPaidCost(float costAtTimeOfPayment) {
        this.costAtTimeOfPayment = costAtTimeOfPayment;
    }

    /**
     * Check if order can be refunded
     * @return true if order can be refunded, false otherwise
     */
    public boolean canBeRefunded() {
        return this.orderStatus != OrderStatus.REFUNDED;
    }
}

