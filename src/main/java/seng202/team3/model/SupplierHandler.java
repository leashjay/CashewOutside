package seng202.team3.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * Container for Supplier class
 */

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierHandler {

    /**
     * Holds the history of the businesses orders
     */
    private ArrayList orderHistory = new ArrayList<SupplierOrder>();

    /**
     * Description of the SupplierS XML data
     */
    @XmlElement(name = "description")
    public String descriptionOfXMLData;

    /**
     * List of suppliers
     */
    @XmlElement(name = "supplier")
    private List<Supplier> suppliers;


    /**
     * Temporary constructor
     */
    public SupplierHandler() {
        ;
    }

    /**
     * Constructor for supplier class
     *
     * @param descriptionOfXMLData A description of the XML data loaded
     * @param suppliers
     */
    public SupplierHandler(String descriptionOfXMLData, List<Supplier> suppliers) {
        this.descriptionOfXMLData = descriptionOfXMLData;
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
     * @return suppliers the list of suppliers the business has
     */
    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    /**
     * Replaces the list of suppliers the business has with a new list
     * @param newSuppliers the suppliers the business now has
     */
    public void setSuppliers(List<Supplier> newSuppliers) {
        this.suppliers = newSuppliers;
    }

    /**
     * Adds a new supplier to the current list of suppliers
     * @param supplierToBeAdded the supplier to be added to the list
     */
    public void addSupplier(Supplier supplierToBeAdded){
        suppliers.add(supplierToBeAdded);
    }

    /**
     * Removes a supplier currently in the list of suppliers
     * @param supplierToBeRemoved
     */
    public void removeSupplier(Supplier supplierToBeRemoved){
        suppliers.remove(supplierToBeRemoved);
    }

    /**
     *
     * @param order
     * @param inventory
     * @return
     */
    public String orderFromSupplier(SupplierOrder order, Inventory inventory){
        Iterator it = order.getOrderItems().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Ingredient ingredient = (Ingredient) pair.getKey();
            System.out.println(ingredient.getName() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
        orderHistory.add(order);
        return "Order has been made from supplier";
    }
}

