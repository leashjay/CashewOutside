package seng202.team3.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seng202.team3.model.Supplier;
import seng202.team3.model.SupplierHandler;
import seng202.team3.util.InputValidationHelper;
import seng202.team3.util.PhoneType;
import seng202.team3.view.BusinessApp;

import javax.xml.bind.JAXBException;
import java.io.IOException;

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

    @FXML
    Text idErrorText;

    @FXML
    Text nameErrorText;

    @FXML
    Text addressErrorText;

    @FXML
    Text emailErrorText;

    @FXML
    Text phoneErrorText;

    @FXML
    Text urlErrorText;


    /**
     * Method called to setup intial GUI of the form.
     */
    public void initialize(){
        phoneTypeChoiceBox.getItems().add(PhoneType.HOME);
        phoneTypeChoiceBox.getItems().add(PhoneType.MOBILE);
        phoneTypeChoiceBox.getItems().add(PhoneType.WORK);
        phoneTypeChoiceBox.getItems().add(PhoneType.UNKNOWN);

        phoneTypeChoiceBox.setValue(PhoneType.UNKNOWN);

    }

    /**
     * Sets the values of the form to relate to the supplier we are editing
     * @param supplierToEdit the supplier we are editing
     */
    public void setParameters(Supplier supplierToEdit) {
        //Initalize parameters here
    }

    /**
     * Checks the validity of input entered into the form.
     * @return true of the form has errors, false if the form is error free.
     */
    private boolean checkForErrors(){
        boolean hasError = false;

        if (InputValidationHelper.checkEmpty(idTextField, idErrorText) || InputValidationHelper.checkSupplierValidId(idTextField, idErrorText) == false) {
            hasError = true;
        }
        if(InputValidationHelper.checkEmpty(nameTextField, nameErrorText)){
            hasError = true;
        }

        if(InputValidationHelper.checkEmpty(addressTextField, addressErrorText)){
            hasError = true;
        }

        if(!InputValidationHelper.isValidWithRegex(emailTextField, emailErrorText, InputValidationHelper.EMAIL_REGEX)){
            hasError = true;
        }

        if(!InputValidationHelper.isValidWithRegex(phoneTextField, phoneErrorText, InputValidationHelper.PHONE_NUMBER_REGEX)){
            hasError = true;
        }

        if(!InputValidationHelper.isValidWithRegex(urlTextField, urlErrorText, InputValidationHelper.URL_REGEX)){
            hasError = true;
        }

        return hasError;
    }

    /**
     * Method that adds a supplier using the data that the user has given
     */
    public void addSupplier() throws JAXBException, IOException {
        if(checkForErrors() == false) {
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
            BusinessApp.getBusiness().exportSupplierAsXML(BusinessApp.suppliersXML);
        }
    }

}
