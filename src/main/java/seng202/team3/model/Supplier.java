package seng202.team3.model;


import seng202.team3.util.PhoneType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Basic info about supplier
 */

@XmlRootElement(name = "supplier")
public class Supplier {

    /**
     * Supplier id
     */
    @XmlElement(name = "sid")
    private String sid;

    /**
     * Supplier name
     */
    @XmlElement (name = "name")
    private String name;

    /**
     * Supplier address
     */
    @XmlElement (name = "address")
    private String address;

    /** Supplier phone number */
    @XmlElement (name = "phone")
    private String phoneNumber;

    /**
     * Supplier phone type
     */
    @XmlAttribute(name = "type")
    private PhoneType phoneType;

    /**
     * Suppler email
     */
    @XmlElement(name = "email")
    private String email = "Null";

    /**
     * Supplier business website
     */
    @XmlElement(name = "url")
    private String url = "Null";


    /**
     * Temporary constructor
     */
    public Supplier() {
        this.phoneType = PhoneType.UNKNOWN;
    }

    /**
     * Constructor for Supplier class
     *
     * @param sid The ID of the supplier
     * @param name The name of the supplier
     * @param address The street address of the supplier
     * @param phoneType The type of the suppliers phone can be(MOBILE, WORK, HOME, UNKNOWN)
     * @param phoneNumber The phone number of the supplier
     * @param email The email address of the supplier
     * @param url The url of the suppliers website
     */
    public Supplier(String sid, String name, String address, PhoneType phoneType, String phoneNumber, String email, String url) {
        this.sid = sid;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.phoneType = phoneType;
        this.email = email;
        this.url = url;
    }

    /**
     * Getter for supplier id
     * @return sid
     */
    public String getSid() {
        return sid;
    }

    /**
     * Getter for supplier name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for supplier address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter for supplier phone number
     * @return phone
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Getter for supplier phone type
     * @return type
     */
    public PhoneType getPhoneType() {
        return phoneType;
    }

    /**
     * Getter for supplier email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for supplier business website
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the suppliers name to the given string
     * @param newSupplierName the new name of the supplier
     */
    public void setName(String newSupplierName){
        name = newSupplierName;
    }

    /**
     * Sets the suppliers address to the given string
     * @param newAddress the new address of the supplier
     */
    public void setAddress(String newAddress){
        address = newAddress;
    }

    /**
     * Sets the suppliers phone number to the given string
     * @param newNumber the new phone number of the supplier
     */
    public void setPhoneNumber(String newNumber){
        phoneNumber = newNumber;
    }

    /**
     * Sets the suppliers phone type to the new type given
     * @param newPhoneType the new phone type of the supplier
     */
    public void setPhoneType(PhoneType newPhoneType){
        phoneType = newPhoneType;
    }

    /**
     * Sets the suppliers email to the new email given
     * @param newEmail the new email of the supplier
     */
    public void setEmail(String newEmail){
        email = newEmail;
    }


    /**
     * Sets the suppliers url to the new url given
     * @param newUrl the new url of the supplier
     */
    public void setUrl(String newUrl){
        url = newUrl;
    }

}