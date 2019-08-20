package model;

import seng202.team3.model.Menu;
import seng202.team3.model.Truck;

/**
 * Serves as a main class, holds GUI launcher and XML parser methods
 **/

public class Business {
    private SupplierHandler supplierManager;
    private seng202.team3.model.Menu menuManager;
    private OrderHandler orderManager;
    private seng202.team3.model.Truck thisTruck;


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

