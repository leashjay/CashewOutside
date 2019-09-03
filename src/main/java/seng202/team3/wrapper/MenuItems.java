package seng202.team3.wrapper;

import seng202.team3.model.MenuItem;

import javax.xml.bind.annotation.XmlElement;

public class MenuItems {

    @XmlElement(name = "item")
    public MenuItem[] items;
}
