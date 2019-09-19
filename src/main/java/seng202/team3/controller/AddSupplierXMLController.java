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

    String errorMessage;


    public void addSupplierXML() throws Exception {
        String file = pathString.getText();
        supplierHandler.addSupplierFromXML(file);
        for (String message : SuppliersLoader.errorMessage) {
            errorMessage += message;
            errorMessage += "\n";
        }
//        if (errorMessage.length() == 0) {
//            feedbackMessage.setText("Import from " + "file" + " is a success!");
//        } else {
//            feedbackMessage.setText(errorMessage);
//        }
        Stage stage = (Stage) importFromXMLButton.getScene().getWindow();
        stage.close();
        SupplierTabController.getInstance().updateSupplierTable();

    }


}
