package seng202.team3.model;


import seng202.team3.util.OrderStatus;
import seng202.team3.util.ThreeValueLogic;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *
 * @generated
 */

@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.NONE)
public class Order {
    // TODO make Order Observable so SalesGUI is dynamically updated when MenuItems are added etc.
    private LocalDate dateOrdered;

	private LocalTime timeOrdered;

    @XmlElement(name = "orderId")
    private int orderId; // should be unique across multiple orders

    @XmlElement(name = "orderStatus")
    private OrderStatus orderStatus;

    @XmlElement(name = "orderCost")
    private float orderCost;

    @XmlElement(name = "itemsOrdered")
    private ArrayList<MenuItem> itemsOrdered = new ArrayList<>();

    @XmlAttribute(name = "isGF")
    private ThreeValueLogic isGFFlag = ThreeValueLogic.YES;

    @XmlAttribute(name = "isVeg")
    private ThreeValueLogic isVegFlag = ThreeValueLogic.YES;

    @XmlAttribute(name = "isVegan")
    private ThreeValueLogic isVeganFlag = ThreeValueLogic.YES;

    @XmlElement(name = "flagsChecked")
    private boolean flagsChecked = true;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     */
    public Order() {
        // TODO change timeOrdered to be set when order is ordered.
        super();
        timeOrdered = LocalTime.now();
        dateOrdered = LocalDate.now();
    }

    /**
     * Getter for totalCost
     *
     * @return totalCost
     */
    public float getTotalCost() {
        return orderCost;
    }

    /**
     * Getter for DateOrdered of type string
     *
     * @return dateOrdered
     */
    @XmlAttribute(name = "dataOrdered")
    public String getDateOrdered() {
        return dateOrdered.toString();
    }

    /**
     * Getter for timeOrdered of type string
     *
     * @return timeOrdered
     */
    @XmlAttribute(name = "timeOrdered")
    public String getTimeOrdered() {
        return timeOrdered.toString();
    }

    public int getOrderId() {
        // TODO throw an error if no orderId
        return this.orderId;
    }

    public void setOrderId(int newOrderId) {
        this.orderId = newOrderId;
    }


    /**
     * <!-- begin-user-doc -->
     * Adds a given MenuItem to the itemsOrdered and it's cost to the orderCost.
     * The flags for isGF, isVeg and isVegan are also updated accordingly.
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    public void addToOrder(MenuItem itemToAdd) {
        // TODO implement me
        this.isGFFlag = getFlagAdding(this.isGFFlag, itemToAdd.isGlutenFree());
        this.isVegFlag = getFlagAdding(this.isVegFlag, itemToAdd.isVegetarian());
        this.isVeganFlag = getFlagAdding(this.isVeganFlag, itemToAdd.isVegan());
        this.itemsOrdered.add(itemToAdd);
        this.orderCost += itemToAdd.getCostPrice();
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

    private ThreeValueLogic getFlagRemoving(ThreeValueLogic flagFromOrder, ThreeValueLogic flagFromMenu) {
        if (flagFromMenu == ThreeValueLogic.YES) {
            return flagFromOrder;
        } else {
            this.flagsChecked = false;
            return ThreeValueLogic.UNKNOWN;
        }
    }

    /**
     * <!-- begin-user-doc -->
     * Removes a given item from the itemsOrdered and removes it's cost, if it was present.
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
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
            this.orderCost -= itemToRemove.getCostPrice();
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
     * <!-- begin-user-doc -->
     * changes an order's status to the new status,
     * does not perform any error checking.
     * i.e. shouldn't go from COMPLETE to QUEUED
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */
    public void changeStatus(OrderStatus newStatus) {
        // TODO implement me
        this.orderStatus = newStatus;
    }

    public static float calculateOrder(ArrayList<MenuItem> itemsToCalculate) {
        float cost = 0;
        for (MenuItem item : itemsToCalculate) {
            cost += item.getCostPrice();
        }
        return cost;
    }

    public OrderStatus getStatus() {
        return this.orderStatus;
    }
}

