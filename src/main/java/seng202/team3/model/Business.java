package seng202.team3.model;

import seng202.team3.parsing.InventoryLoader;
import seng202.team3.parsing.MenuLoader;
import seng202.team3.parsing.SalesLoader;
import seng202.team3.parsing.SuppliersLoader;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Set;

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

    /**List of employees in the database*/
    private EmployeeHandler employeeManager;

    private int lastOrderID;

    /**
     * Constructor of Business class
     *
     * @param ingredientsXML path to ingredientsXML
     * @param menuXML        path to menuXML
     * @param suppliersXML   path to suppliersXML
     */
    public Business(String ingredientsXML, String menuXML, String suppliersXML, String salesXML) throws JAXBException {
        thisTruck = new Truck(ingredientsXML);
        createMenuManager(menuXML);
        createSupplierManager(suppliersXML);
        createSalesManager(salesXML);
        //TODO Make this load from XML
        createEmployeeManager("filename");
        lastOrderID = calculateLastOrderID();

    }


    private int calculateLastOrderID() {
        Set<Integer> orderIDs = this.salesManager.getOrdersHashMap().keySet();
        if (orderIDs.size() <= 0) {
            return 0; // default value
        }
        int maxOrderID = (int) orderIDs.toArray()[0];
        for (Integer orderID : orderIDs) {
            if (orderID > maxOrderID) {
                maxOrderID = orderID;
            }
        }
        return maxOrderID;
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
     * Getter for employee handler instance
     * @return instance of employee handler
     */
    public EmployeeHandler getEmployeeHandler() {return employeeManager; }

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

    public void createSalesManager(String filename) throws JAXBException {
        SalesLoader salesLoader = new SalesLoader();
        salesManager = salesLoader.loadSalesData(filename);
    }

    private void createEmployeeManager(String filename){
        employeeManager = new EmployeeHandler();
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

    /**
     * gets the lastOrderID
     * @return the lastOrderID
     */
    public int getLastOrderID() {
        return this.lastOrderID;
    }

    /**
     * increases the last OrderID
     * @return the new OrderID;
     */
    public int makeNextOrderID() {
        this.lastOrderID++;
        return this.lastOrderID;
    }

}