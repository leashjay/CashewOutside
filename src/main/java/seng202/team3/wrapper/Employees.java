package seng202.team3.wrapper;

import seng202.team3.model.Employee;

import javax.xml.bind.annotation.XmlElement;

public class Employees {

    @XmlElement(name = "employee")
    public Employee[] employees;
}
