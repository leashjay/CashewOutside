package seng202.team3.wrapper;

import seng202.team3.model.Supplier;

import javax.xml.bind.annotation.XmlElement;

public class Providers {

    @XmlElement(name = "supplier")
    public Supplier[] suppliers;
}
