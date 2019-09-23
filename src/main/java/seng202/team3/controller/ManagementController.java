package seng202.team3.controller;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Supplier;
import seng202.team3.util.PhoneType;
import seng202.team3.view.BusinessApp;

import java.io.IOException;
import java.util.List;

public class ManagementController{


    private Stage window;
    private Parent root;

    @FXML private Button addManuallyButton;

    @FXML
    private Button backButton;


        /**
         * sends the user back to the main screen.;
         * Some kind of clean up / persistence should be implemented here.
         * @throws IOException idk?
         */
    public void returnToMainScreen() throws IOException {
        System.out.println("Back button pressed");
        BusinessApp.loadMainPage();
    }


}
