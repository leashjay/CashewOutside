package seng202.team3.controller;

import seng202.team3.model.Business;
import seng202.team3.view.BusinessApp;

import java.io.IOException;

public class MainController {

    public void salesButtonPressed() throws IOException {
        BusinessApp.loadSalesPage();
    }

    public void managementButtonPressed() throws IOException {
        BusinessApp.loadManagementPage();
    }

    public void kitchenButtonPressed() throws IOException {
        BusinessApp.loadKitchenPage();
    }

}
