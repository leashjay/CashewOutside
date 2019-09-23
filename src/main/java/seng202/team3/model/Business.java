package seng202.team3.model;

import seng202.team3.parsing.InventoryLoader;
import seng202.team3.parsing.MenuLoader;
import seng202.team3.parsing.SalesLoader;
import seng202.team3.parsing.SuppliersLoader;

import javax.xml.bind.JAXBException;
import java.io.IOException;

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
    public Business(String ingredientsXML, String menuXML, String suppliersXML) throws JAXBException {
        thisTruck = new Truck(ingredientsXML);
        createMenuManager(menuXML);
        createSupplierManager(suppliersXML);
        salesManager = new SalesHandler();
    }

    /**
     * Getter for truck instance
     * @return instance of truck
     */
    public Truck getTruck() {
        return thisTruck;
    }

    /**
     * Getter for supplier handler instance
     * @return instance of supplier handler
     */
    public SupplierHandler getSupplierHandler() {
        return supplierManager;
    }

    /**
     * Getter for menu instance
     * @return instance of menu
     */
    public Menu getMenuManager() {
        return menuManager;
    }

    /**
     * Getter for sales handler instance
     * @return instance of sales handler
     */
    public SalesHandler getSalesHandler() {
        return salesManager;
    }

    /**
     * Create supplierManager of type SupplierHandler class
     * @param fileName path to suppliersXML
     * @throws Exception
     */
    public void createSupplierManager(String fileName) throws JAXBException {
        SuppliersLoader supplierLoad = new SuppliersLoader();
        supplierManager = supplierLoad.loadSuppliersData(fileName);
    }

    /**
     * Create menuManager of type Menu class
     * @param fileName path to menuXML
     * @throws Exception
     */
    public void createMenuManager(String fileName) throws JAXBException {
        MenuLoader menuLoad = new MenuLoader();
        menuManager = menuLoad.loadMenuData(fileName);
    }

    /**
     * Export order history as XML file
     *
     * @param file path to order history XML file
     */
    public void exportOrdersAsXML(String file) throws JAXBException, IOException {
        SalesLoader salesLoader = new SalesLoader();
        salesLoader.exportSalesData(file, salesManager);
    }

    /**
     * Export inventory data as XML file
     *
     * @param file path to inventory XML file
     */
    public void exportInventoryAsXML(String file) throws JAXBException, IOException {
        InventoryLoader inventoryLoader = new InventoryLoader();
        inventoryLoader.exportIngredientsData(file, thisTruck.getInventory());
    }

    /**
     * Export supplier data as XML file
     *
     * @param file
     */
    public void exportSupplierAsXML(String file) throws JAXBException, IOException{
        SuppliersLoader suppliersLoader = new SuppliersLoader();
        suppliersLoader.exportSupplierData(file, supplierManager);
    }

    /**
     * Export menu data as XML file
     * @param file
     */
    public void exportMenuAsXML(String file) throws JAXBException, IOException {
        MenuLoader menuLoader = new MenuLoader();
        menuLoader.exportMenuData(file, menuManager);
    }


}