package seng202.team3.model;

import seng202.team3.parsing.SupplierAdapter;
import seng202.team3.parsing.SuppliersLoader;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Container for Supplier class
 */

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.NONE)
public class SupplierHandler {

    /**
     * Description of the Suppliers XML data
     */
    @XmlElement(name = "description")
    private String descriptionOfXMLData;

    /**
     * Holds the history of the businesses orders
     */
    private ArrayList orderHistory = new ArrayList<SupplierOrder>();

    /**
     * List of suppliers
     */
    @XmlElement(name = "providers")
    @XmlJavaTypeAdapter(SupplierAdapter.class)
    private HashMap<String, Supplier> suppliers;

    /**
     * SuppliersLoader object to call import and export method
     */
    private SuppliersLoader suppliersLoader;


    /**
     * No arg constructor for JAXB
     */
    public SupplierHandler() {

    }

    /**
     * Constructor for supplier class
     *
     * @param descriptionOfXMLData A description of the XML data loaded
     * @param suppliers A list of the suppliers the business has
     */
    public SupplierHandler(String descriptionOfXMLData, HashMap<String, Supplier> suppliers) {
        this.descriptionOfXMLData = descriptionOfXMLData;
        this.suppliers = suppliers;
    }

    /**
     * Constructor without describing xml data
     * @param suppliers A list of the suppliers the business has
     */
    public SupplierHandler(HashMap<String, Supplier> suppliers){
        this.suppliers = suppliers;
    }

    /**
     * Getter for description of the Supplier XML data
     *
     * @return descriptionOfXMLData a description of the xml data used to create the suppliers
     */
    public String getDescriptionOfXMLData() {
        return descriptionOfXMLData;
    }

    /**
     * Getter for SuppliersLoader instance
     *
     * @return suppliersLoader
     */
    public SuppliersLoader getSuppliersLoader() {
        return suppliersLoader; }

    /**
     * Getter for list of suppliers
     *
     * @return suppliers the list of suppliers the business has
     */
    public HashMap<String, Supplier> getSuppliers() {
        return suppliers;
    }

    /**
     * Replaces the list of suppliers the business has with a new list
     *
     * @param newSuppliers the suppliers the business now has
     */
    public void setSuppliers(HashMap<String, Supplier> newSuppliers) {
        this.suppliers = newSuppliers;
    }

    /**
     * Adds a new supplier to the current list of suppliers
     *
     * @param supplierToBeAdded the supplier to be added to the list
     */
    public void addSupplier(Supplier supplierToBeAdded) {
        suppliers.put(supplierToBeAdded.getSid(), supplierToBeAdded);
    }

    /**
     * Adds suppliers from an XML file to the suppliers HashMap
     *
     * @param file path to supplier XML file
     * @throws Exception
     */
    public void addSupplierFromXML(String file) throws JAXBException {
        suppliersLoader = new SuppliersLoader();
        SupplierHandler supplierHandler = suppliersLoader.loadSuppliersData(file);
        if (supplierHandler != null) {
            HashMap<String, Supplier> newSuppliers = supplierHandler.getSuppliers();
            suppliers.putAll(newSuppliers);
        }
    }

    /**
     * Removes a supplier currently in the list of suppliers
     *
     * @param supplierID The ID of the supplier to be removed
     */
    public void removeSupplier(String supplierID) {
        suppliers.remove(supplierID);
    }

    /**
     * Orders ingredients from a supplier
     *
     * @param order     Hashset of items and quantities in the order
     */
    public void orderFromSupplier(SupplierOrder order) {
        orderHistory.add(order);
    }


}

