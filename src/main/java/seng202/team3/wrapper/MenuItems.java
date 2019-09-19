package seng202.team3.wrapper;

import seng202.team3.model.MenuItem;

import javax.xml.bind.annotation.XmlElement;

/**
 * Wrapper class for JAXB to load menu XML file
 * which contains Array of objects of type MenuItem
 */
public class MenuItems {

    @XmlElement(name = "item")
    public MenuItem[] items;
}
