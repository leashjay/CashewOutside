package seng202.team3.controller;


import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seng202.team3.model.Business;
import seng202.team3.model.Supplier;
import seng202.team3.model.SupplierHandler;
import seng202.team3.util.PhoneType;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.view.BusinessApp;

import java.io.IOException;
import java.rmi.server.LoaderHandler;

public class ManuallyAddSupplierController {

    SupplierHandler supplierHandler = BusinessApp.getBusiness().getSupplierHandler();


    @FXML
    TextField idTextField;

    @FXML
    TextField nameTextField;

    @FXML
    TextField addressTextField;

    @FXML
    TextField emailTextField;

    @FXML
    TextField phoneTextField;

    @FXML
    TextField urlTextField;

    @FXML
    Button addSupplierButton;

    @FXML
    ChoiceBox<PhoneType> phoneTypeChoiceBox;

    public void initialize(){
        phoneTypeChoiceBox.getItems().add(PhoneType.HOME);
        phoneTypeChoiceBox.getItems().add(PhoneType.MOBILE);
        phoneTypeChoiceBox.getItems().add(PhoneType.WORK);
        phoneTypeChoiceBox.getItems().add(PhoneType.UNKNOWN);

        phoneTypeChoiceBox.setValue(PhoneType.UNKNOWN);

    }

    //TODO Add input validation on this form
    public void addSupplier() throws IOException {
        String id = idTextField.getText();
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        String email = emailTextField.getText();
        String phoneNumber = phoneTextField.getText();
        PhoneType phoneType = phoneTypeChoiceBox.getValue();
        String url = urlTextField.getText();

        Supplier newSupplier = new Supplier(id, name, address, phoneType, phoneNumber, email, url);
        supplierHandler.addSupplier(newSupplier);
        Stage stage = (Stage) addSupplierButton.getScene().getWindow();

        SupplierTabController.getInstance().updateSupplierTable();

        stage.close();


    }


//    private boolean isInt(TextField input, String message){
//        try{
//            int id = Integer.parseInt(input.getText());
//        } catch (NumberFormatException e){
//            p
//        }
//    }
}
