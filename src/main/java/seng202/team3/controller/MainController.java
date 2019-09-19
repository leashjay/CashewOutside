package seng202.team3.controller;

import seng202.team3.model.Business;
import seng202.team3.view.BusinessApp;

import java.io.IOException;

public class MainController {
    private String ingredientsXML = "./resources/data/Ingredients.xml";
    private String menuXML = "./resources/data/SampleMenu.xml";
    private String suppliersXML = "./resources/data/Suppliers.xml";
    private Business business;


    public void initialize() throws Exception {
        this.business = new Business(ingredientsXML, menuXML, suppliersXML);
    }

    public void salesButtonPressed() throws IOException {
        System.out.println("Sales button pressed");
        BusinessApp.loadSalesPage();
    }

    public void managementButtonPressed() throws IOException {
        System.out.println("ManagementController button pressed");
        BusinessApp.loadManagementPage();
    }

    public void kitchenButtonPressed() throws IOException {
        System.out.println("Kitchen button pressed");
        BusinessApp.loadKitchenPage();
    }

    public Business getBusiness() {
        return business;
    }
}
