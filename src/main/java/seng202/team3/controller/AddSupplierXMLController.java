package seng202.team3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seng202.team3.model.SupplierHandler;
import seng202.team3.parsing.SuppliersLoader;
import seng202.team3.view.BusinessApp;

public class AddSupplierXMLController {

    SupplierHandler supplierHandler = BusinessApp.getBusiness().getSupplierHandler();

    @FXML
    TextField pathString;

    @FXML
    Button importFromXMLButton;

    @FXML
    Text feedbackMessage;

    @FXML
    Button closeScreen;

    String errorMessage;

    Stage stage;


    /**
     * Called by importFromSuppplierXML Button to load SupplierXML into database
     */
    public void addSupplierXML() throws Exception {
        errorMessage = "";
        String file = pathString.getText();
        try {
            supplierHandler.addSupplierFromXML(file);
        } catch (NullPointerException npe) {
            errorMessage += npe.getMessage();
        }

        for (String message : SuppliersLoader.errorMessage) {
            errorMessage += message;
            errorMessage += "\n";
        }

        if (errorMessage.length() == 0 && feedbackMessage != null) {
            feedbackMessage.setText("Import from " + file + " is a success!");
        } else {
            feedbackMessage.setText(errorMessage);
        }
        stage = (Stage) importFromXMLButton.getScene().getWindow();
        SupplierTabController.getInstance().updateSupplierTable();
    }


    /**
     * Go back to Suppliers tab in Management screen
     */
    public void closeAddSupplierXMLScreen() {
        stage.close();
    }



}
