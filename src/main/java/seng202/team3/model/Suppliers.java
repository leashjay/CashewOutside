package seng202.team3.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;

/**
 * Container for Supplier class
 */

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class Suppliers {

    /**
     * Description of the Supplier XML data
     */
    @XmlElement(name = "description")
    public String desc;

    /**
     * List of suppliers
     */
    @XmlElement(name = "supplier")
    private List<Supplier> suppliers;


    /**
     * Temporary constructor
     */
    public Suppliers() {
        ;
    }

    /**
     * Constructor for supplier class
     *
     * @param desc
     * @param suppliers
     */
    public Suppliers(String desc, List<Supplier> suppliers) {
        this.desc = desc;
        this.suppliers = suppliers;
    }

    /**
     * Getter for description of the Supplier XML data
     *
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Getter for list of suppliers
     *
     * @return suppliers
     */
    public List<Supplier> getSuppliers() {
        return suppliers;
    }


    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
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
     * Makes an order of the specificied ingredient from the given supplier
     * @param supplier
     * @param ingredient The ingredient and quantity to be ordered
     * @return A string showing details about the order
     */
    public String makeOrder(Supplier supplier, HashMap<Ingredient, Float> ingredient){
        return "function not implemented yet";
    }
}

