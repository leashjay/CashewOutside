//package model;
//
//import seng202.team3.model.MenuItem;
//
//import java.util.ArrayList;
//
///**
// * <!-- begin-user-doc -->
// * <!--  end-user-doc  -->
// *
// * @generated
// */
//
//public class Order {
//    //	public date dateOrdered;
////	public time timeOrdered;
//    public String orderStatus;
//    public int changeNeeded;
//    public float totalCost;
//    private ArrayList<MenuItem> orderItems = new ArrayList<MenuItem>();
//
//    /**
//     * <!-- begin-user-doc -->
//     * <!--  end-user-doc  -->
//     *
//     * @generated
//     */
//    public Order() {
//        super();
//    }
//
//    /**
//     * For quick testing of functionability
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
//        seng202.team3.model.Order order = new seng202.team3.model.Order();
//        parser.MenuItem item = new parser.MenuItem();
//        order.addToOrder(item);
//        System.out.println("help");
//    }
//
//    /**
//     * <!-- begin-user-doc -->
//     * <!--  end-user-doc  -->
//     *
//     * @generated
//     * @ordered
//     */
//
//    public void addToOrder(MenuItem parameter) {
//        // TODO implement me
//        this.orderItems.add(parameter);
//    }
//
//    /**
//     * <!-- begin-user-doc -->
//     * <!--  end-user-doc  -->
//     *
//     * @generated
//     * @ordered
//     */
//
//    public void removeFromOrder(MenuItem parameter) {
//        // TODO implement me
//        this.orderItems.remove(parameter);
//    }
//
//    /**
//     * <!-- begin-user-doc -->
//     * <!--  end-user-doc  -->
//     *
//     * @generated
//     * @ordered
//     */
//
//    public void changeStatus(String parameter) {
//        // TODO implement me
//    }
//}
//