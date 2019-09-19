package seng202.team3.model;

import seng202.team3.parsing.MenuLoader;
import seng202.team3.parsing.SuppliersLoader;

/**
 * Business class holds the instances of handler classes, and acts as a major access point to database in memory
 */
public class Business {
    /**
     * Food truck that holds cash float
     */
    private Truck thisTruck;

    /** List of Suppliers in the database */
    private SupplierHandler supplierManager;

    /** List of MenuItem in the database */
    private Menu menuManager;

    /** List of order in the database */
    private SalesHandler salesManager;

    /**
     * Constructor of Business class
     *
     * @param ingredientsXML path to ingredientsXML
     * @param menuXML        path to menuXML
     * @param suppliersXML   path to suppliersXML
     */
    public Business(String ingredientsXML, String menuXML, String suppliersXML) throws Exception {
        thisTruck = new Truck(ingredientsXML);
        createMenuManager(menuXML);
        createSupplierManager(suppliersXML);
    }


    /**
     * Create supplierManager of type SupplierHandler class
     * @param fileName path to suppliersXML
     * @throws Exception
     */
    public void createSupplierManager(String fileName) throws Exception {
        SuppliersLoader supplierLoad = new SuppliersLoader();
        supplierManager = supplierLoad.loadSuppliersData(fileName);
    }

    /**
     * Create menuManager of type Menu class
     * @param fileName path to menuXML
     * @throws Exception
     */
    public void createMenuManager(String fileName) throws Exception {
        MenuLoader menuLoad = new MenuLoader();
        menuManager = menuLoad.loadMenuData(fileName);
    }

    public Truck getTruck() {
        return thisTruck;
    }

    public SupplierHandler getSupplierHandler() {
        return supplierManager;
    }

    public Menu getMenuManager() {
        return menuManager;
    }

    public SalesHandler getSalesHandler() {
        return salesManager;
    }



}