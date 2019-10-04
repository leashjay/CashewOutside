package seng202.team3.controller;


import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import seng202.team3.view.BusinessApp;

import java.io.IOException;

/**
 * Controller class for the management section of the GUI
 */
public class ManagementController{


    private Stage window;
    private Parent root;

    @FXML private Button addManuallyButton;

    @FXML
    private Button backButton;


        /**
         * sends the user back to the main screen.;
         * @throws IOException the exception thrown.
         */
    public void returnToMainScreen() throws IOException {
        BusinessApp.loadMainPage();
    }


}
