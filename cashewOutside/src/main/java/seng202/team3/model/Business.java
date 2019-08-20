package seng202.team3.model;

import java.util.List;

/**
 * Main class for the business. Keeps track of the model classes (suppliers
 * etc.) that we have as well as performing major functions.
 */
public class Business {
    List<Supplier> suppliers;

    public void setSuppliers(List<Supplier> s) {
        suppliers = s;
    }
}