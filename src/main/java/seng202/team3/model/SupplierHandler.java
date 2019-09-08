package seng202.team3.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seng202.team3.parsing.SupplierAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Container for Supplier class
 */

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
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

    public ObservableList<Supplier> getSuppliersAsObservableList(){
        ObservableList<Supplier> listOfSuppliers = FXCollections.observableArrayList();
        for (Map.Entry<String, Supplier> entry : suppliers.entrySet()){
            listOfSuppliers.add(entry.getValue());
        }
        return listOfSuppliers;
    }


}

