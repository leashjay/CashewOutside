package seng202.team3.model;

import seng202.team3.parsing.SupplierAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashMap;

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
    @XmlElement(name = "providers")
    @XmlJavaTypeAdapter(SupplierAdapter.class)
    private HashMap<String, Supplier> providers;


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
    public Suppliers(String desc, HashMap<String, Supplier> suppliers) {
        this.desc = desc;
        this.providers = suppliers;
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
    public HashMap<String, Supplier> getSuppliers() {
        return providers;
    }


    public void setSuppliers(HashMap<String, Supplier> suppliers) {
        this.providers = suppliers;
    }
}

