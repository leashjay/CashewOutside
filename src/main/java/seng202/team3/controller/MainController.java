package seng202.team3.controller;

import seng202.team3.model.Business;
import seng202.team3.view.BusinessApp;

import java.io.IOException;

public class MainController {

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

}
