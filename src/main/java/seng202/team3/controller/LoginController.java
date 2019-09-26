package seng202.team3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng202.team3.view.BusinessApp;

import java.io.IOException;

public class LoginController {

    @FXML
    Button loginButton;

    public void checkUserDetails() throws IOException {
        BusinessApp.loadManagementPage();
    }
}
