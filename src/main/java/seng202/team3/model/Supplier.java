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
    private String phone;

    /**
     * Supplier phone type
     */
    @XmlAttribute(name = "type")
    private PhoneType type;

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
        this.type = PhoneType.UNKNOWN;
    }

    /**
     * Constructor for Supplier class
     *
     * @param sid
     * @param name
     * @param address
     * @param type
     * @param phone
     * @param email
     * @param url
     */
    public Supplier(String sid, String name, String address, PhoneType type, String phone, String email, String url) {
        this.sid = sid;
        this.name = name;
        this.address = address;
        this.type = type;
        this.phone = phone;
        this.type = PhoneType.UNKNOWN;
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
    public String getPhone() {
        return phone;
    }

    /**
     * Getter for supplier phone type
     * @return type
     */
    public PhoneType getType() {
        return type;
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


}