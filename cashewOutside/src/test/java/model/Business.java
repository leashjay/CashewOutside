package model;

/**
 * Serves as a main class, holds GUI launcher and XML parser methods
 **/

public class Business {
    private SupplierHandler supplierManager;
    private Menu menuManager;
    private OrderHandler orderManager;
    private Truck thisTruck;


    /**
     * Constructor of Business class
     */
    public Business() {
        supplierManager = new SupplierHandler();
        menuManager = new Menu();
        orderManager = new OrderHandler();
        thisTruck = new Truck();
    }

}

