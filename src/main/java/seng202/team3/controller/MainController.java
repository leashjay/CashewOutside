package seng202.team3.controller;

import seng202.team3.view.BusinessApp;

import java.io.IOException;

/**
 * Main controller for business landing page
 */

public class MainController {

    /**
     * Navigate to sales page
     */
    public void salesButtonPressed() throws IOException {
        BusinessApp.loadSalesPage();
    }

    /**
     * Navigate to management page
     */
    public void managementButtonPressed() throws IOException {
        BusinessApp.loadLoginPage();
    }

    /**
     * Navigate to kitchen page
     */
    public void kitchenButtonPressed() throws IOException {
        BusinessApp.loadKitchenPage();
    }

}
