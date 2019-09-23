package seng202.team3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seng202.team3.model.Inventory;
import seng202.team3.model.Menu;
import seng202.team3.model.SupplierHandler;
import seng202.team3.view.BusinessApp;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.ArrayList;

public class AddXMLController {

    private SupplierHandler supplierHandler = BusinessApp.getBusiness().getSupplierHandler();

    private Inventory inventory = BusinessApp.getBusiness().getTruck().getInventory();

    private Menu menu = BusinessApp.getBusiness().getMenuManager();

    private FileChooser fileChooser = new FileChooser();

    private File selectedFile;

    private String fileString;

    public static ArrayList<String> errorMessageList = new ArrayList<>();

    private String errorMessage;

    Stage stage;

    @FXML
    Button browseButton;

    @FXML
    TextField pathString;

    @FXML
    Button importSupplierXMLButton;

    @FXML
    Button importIngredientXMLButton;

    @FXML
    Button importMenuItemXMLButton;

    @FXML
    Text feedbackMessage;





    /**
     * Called by importSuppplierXMLButton to load SupplierXML into database
     */
    public void addSupplierXML(){
        try {
            supplierHandler.addSupplierFromXML(fileString);
        } catch (JAXBException jaxbe) {
            errorMessageList.add(jaxbe.getMessage());
        }

        showErrorMessage(fileString);

        stage = (Stage) importSupplierXMLButton.getScene().getWindow();
        SupplierTabController.getInstance().updateSupplierTable();
    }

    /**
     * Called by importIngredientXMLButton to load Ingredient XML into database
     */
    public void addIngredientXML() {
        try {
            inventory.addIngredientsFromXML(fileString);
        } catch (JAXBException jaxbe) {
            errorMessageList.add(jaxbe.getMessage());
        }

        showErrorMessage(fileString);

        stage = (Stage) importIngredientXMLButton.getScene().getWindow();
        IngredientTabController.getInstance().updateIngredientTable();
    }

    /**
     * Called by importMenuItemXMLButton to load menu items into database
     */
    public void addMenuItemXML() {
        try {
            menu.addMenuItemFromXML(fileString);
        } catch (JAXBException jaxbe) {
            errorMessageList.add(jaxbe.getMessage());
        }

        showErrorMessage(fileString);

        //TODO: link menu item tab with menuitemxml screen
        stage = (Stage) importMenuItemXMLButton.getScene().getWindow();
        MenuItemTabController.getInstance().updateMenuItemTable();
    }


    /**
     * Compiles error message caught from exception handling in loader classes and return to end user.
     * Clears the list of error message caught after the message is being compiled/ returned
     *
     * @param fileName path to XML file
     */
    public void showErrorMessage(String fileName) {
        errorMessage = "";
        for (String message : errorMessageList) {
            errorMessage += message;
            errorMessage += " \n";
        }

        if (errorMessage != null && errorMessage.length() == 0) {
            feedbackMessage.setText("Import from " + fileName + " is a success!");
        } else {
            feedbackMessage.setText(errorMessage);
            errorMessageList.clear();
        }
    }


    /**
     * Called by browseButton to open up a dialog for end user to choose file to import.
     */
    public void browseButtonPressed() {
        fileChooser.setTitle("Select XML files");
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            pathString.setText("File selected: " + selectedFile.getName());
        } else {
            pathString.setText("File selection cancelled.");
        }

        fileString = selectedFile.getPath();
    }


}
