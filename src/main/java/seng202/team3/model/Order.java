package seng202.team3.model;


import org.mockito.internal.matchers.Null;
import seng202.team3.util.OrderStatus;

import javax.lang.model.type.NullType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *
 * @generated
 */

public class Order {
    public LocalDate dateOrdered;
	public LocalTime timeOrdered;
	private int orderId; // should be unique across multiple orders
    public OrderStatus orderStatus;
    public float orderCost;
    public ArrayList<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
    public boolean isGFFlag;
    public boolean isVegFlag;
    public boolean isVeganFlag;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     */
    public Order() {
        super();
    }

    /**
     * Getter for totalCost
     *
     * @return totalCost
     */
    public float getTotalCost() {
        return orderCost;
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
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    public void addToOrder(MenuItem itemToAdd) {
        // TODO implement me
        this.itemsOrdered.add(itemToAdd);
        this.orderCost += itemToAdd.getCostPrice();
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
            this.orderCost -= itemToRemove.getCostPrice();
        }
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

