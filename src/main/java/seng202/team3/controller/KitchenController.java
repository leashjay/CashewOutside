package seng202.team3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng202.team3.view.BusinessApp;

import java.io.IOException;

public class KitchenController {

    @FXML
    private Button backButton;

    /**
     * sends the user back to the main screen.;
     * Some kind of clean up / persistence should be implemented here.
     * @throws IOException idk?
     */
    public void backButtonPressed() throws IOException {
        System.out.println("Back button pressed");
        BusinessApp.loadMainPage();
    }
}
