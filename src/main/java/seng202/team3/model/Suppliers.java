package seng202.team3.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
}

