package seng202.team3.model;

import seng202.team3.parsing.*;

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

    /**The ID of the last order the business has made*/
    private int lastOrderID;


    /**
     * Constructor of Business class
     *
     * @param ingredientsXML path to ingredientsXML
     * @param menuXML        path to menuXML
     * @param suppliersXML   path to suppliersXML
     */
    public Business(String ingredientsXML, String menuXML, String suppliersXML, String salesXML, String employeeXML, String truckXML) throws JAXBException {
        createTruck(truckXML);
        thisTruck.createTruckInventory(ingredientsXML);
        createMenuManager(menuXML);
        createSupplierManager(suppliersXML);
        createSalesManager(salesXML);
        createEmployeeManager(employeeXML);
        lastOrderID = calculateLastOrderID();

    }


    /**
     * Calculates the ID of the most recent order
     * @return an integer showing the ID of the most recent order.
     */
    public int calculateLastOrderID() {
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
     * Creates the instance of the supplier handler associated with the business
     * @param fileName the name of the XML file we create the supplier manager with
     * @throws JAXBException the exception thrown if there is a marshalling error with JAXB
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
        System.out.println(menuManager == null);
        menuManager.calculateServingForMenuItems(thisTruck.getInventory());
    }

    /**
     * Creates the sales manager associated with the business
     * @param filename The name of the XML file we use to create the sales manager
     * @throws JAXBException the exception thrown by JAXB if an error is encountered
     */
    public void createSalesManager(String filename) throws JAXBException {
        SalesLoader salesLoader = new SalesLoader();
        salesManager = salesLoader.loadSalesData(filename);
    }

    /**
     * Creates the employee manager associated with the business
     * @param filename the filename of the XML we are loading the employeemanagerfrom
     * @throws JAXBException the exception thrown by JAXB if there is an error marshalling
     */
    private void createEmployeeManager(String filename) throws JAXBException {
        EmployeeLoader employeeLoader = new EmployeeLoader();
        employeeManager = employeeLoader.loadEmployeeData(filename);
    }

    /**
     * Creates the truck instance associated with the business
     * @param filename the filename of the XML we are loading the truck from
     * @throws JAXBException the exception thrown by JAXB if there is an error marshalling.
     */
    private void createTruck(String filename) throws JAXBException {
        TruckLoader truckLoader = new TruckLoader();
        thisTruck = truckLoader.loadTruckData(filename);
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
     * @param file the name of the XML file we export the supplier data to
     */
    public void exportSupplierAsXML(String file) throws JAXBException, IOException{
        SuppliersLoader suppliersLoader = new SuppliersLoader();
        suppliersLoader.exportSupplierData(file, supplierManager);
    }

    /**
     * Export menu data as XML file
     * @param file the name of the XML file we export the menu data to
     */
    public void exportMenuAsXML(String file) throws JAXBException, IOException {
        MenuLoader menuLoader = new MenuLoader();
        menuLoader.exportMenuData(file, menuManager);
    }

    /**
     * Exports the Employee data as an XML file
     * @param file the name of the XML file we export the Employee data to
     * @throws JAXBException the exception thrown by JAXB if an error is encountered
     * @throws IOException the exception thrown if an error is encountered
     */
    public void exportEmployeeAsXML(String file) throws JAXBException, IOException {
        EmployeeLoader employeeLoader = new EmployeeLoader();
        employeeLoader.exportEmployeeData(file, employeeManager);
    }

    /**
     * Exports the Truck data as an XML file
     * @param file the name of the XML file we export the truck data to
     * @throws JAXBException the exception thrown by JAXB if an error is encountered
     * @throws IOException the exception thrown if an error is encountered
     */
    public void exportTruckAsXML(String file) throws JAXBException, IOException {
        TruckLoader truckLoader = new TruckLoader();
        truckLoader.exportTruckData(file, thisTruck);
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