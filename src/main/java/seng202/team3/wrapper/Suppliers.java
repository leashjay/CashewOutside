package seng202.team3.wrapper;

import seng202.team3.model.Supplier;

import javax.xml.bind.annotation.XmlElement;

/**
 * Wrapper class for JAXB to load suppliers XML file
 * which contains Array of objects of type Supplier
 */
public class Suppliers {

    @XmlElement(name = "supplier")
    public Supplier[] providers;
}
