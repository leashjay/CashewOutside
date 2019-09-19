package seng202.team3.model;

import seng202.team3.parsing.MenuLoader;
import seng202.team3.parsing.SuppliersLoader;

/**
 * Main class for the business. Keeps track of the model classes (suppliers
 * etc.) that we have as well as performing major functions.
 */
public class Business {
    private Truck thisTruck;
    private SupplierHandler supplierManager;
    private Menu menuManager;
    private SalesHandler salesManager;

    public void Business(String ingredientsData, String menuData, String supplierData) throws Exception {
        thisTruck = new Truck();
        salesManager = new SalesHandler();
    }


    public void createSupplierManager(String fileName) throws Exception {
        SuppliersLoader supplierLoad = new SuppliersLoader();
        supplierManager = supplierLoad.loadSuppliersData(fileName);
    }

    public void createMenuManager(String fileName) throws Exception {
        MenuLoader menuLoad = new MenuLoader();
        menuManager = menuLoad.loadMenuData(fileName);
    }


    public SalesHandler getSalesManager() {
        return salesManager;
    }
}