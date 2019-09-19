package seng202.team3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import seng202.team3.model.Inventory;
import seng202.team3.view.BusinessApp;

public class AddIngredientXMLController {


    Inventory inventory = BusinessApp.getBusiness().getTruck().getInventory();

    @FXML
    TextField pathString;

    @FXML
    Button closeScreen;

    @FXML
    Button importFromXMLButton;

    String errorMessage;

    
}
