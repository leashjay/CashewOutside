package seng202.team3.model;


import java.util.ArrayList;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *
 * @generated
 */

public class Order {
    //	public date dateOrdered;
//	public time timeOrdered;
    public String orderStatus;
    public int changeNeeded;
    public float totalCost;
    private ArrayList<MenuItem> orderItems = new ArrayList<MenuItem>();

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
        return totalCost;
    }

    /**
     * For quick testing of functionability
     *
     * @param args
     */
    public static void main(String[] args) {
        Order order = new Order();
        MenuItem item = new MenuItem();
        order.addToOrder(item);
        System.out.println("help");
    }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    public void addToOrder(MenuItem parameter) {
        // TODO implement me
        this.orderItems.add(parameter);
    }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    public void removeFromOrder(MenuItem parameter) {
        // TODO implement me
        this.orderItems.remove(parameter);
    }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    public void changeStatus(String parameter) {
        // TODO implement me
    }
}

