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
import java.io.IOException;
import java.nio.file.Paths;
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

    private Stage stage;

    @FXML
    private Button browseButton;

    @FXML
    private TextField pathString;

    @FXML
    private Button importSupplierXMLButton;

    @FXML
    private Button importIngredientXMLButton;

    @FXML
    private Button importMenuItemXMLButton;

    @FXML
    private Text feedbackMessage;





    /**
     * Called by importSuppplierXMLButton to load SupplierXML into database
     */
    public void addSupplierXML() throws IOException {
        try {
            Paths.get(fileString);
            supplierHandler.addSupplierFromXML(fileString);
            BusinessApp.getBusiness().exportSupplierAsXML(BusinessApp.suppliersXML);
        } catch (JAXBException jaxbe) {
            errorMessageList.add(jaxbe.getMessage());
        } catch (NullPointerException npe) {
            errorMessageList.add("Invalid path entered, please enter a proper path to file");
        }

        showErrorMessage(fileString);

        stage = (Stage) importSupplierXMLButton.getScene().getWindow();
        SupplierTabController.getInstance().updateSupplierTable();
    }

    /**
     * Called by importIngredientXMLButton to load Ingredient XML into database
     */
    public void addIngredientXML() throws IOException {
        try {
            Paths.get(fileString);
            inventory.addIngredientsFromXML(fileString);
            BusinessApp.getBusiness().exportInventoryAsXML(BusinessApp.ingredientsXML);
            BusinessApp.getBusiness().getMenuManager().calculateServingForMenuItems(inventory);
        } catch (JAXBException jaxbe) {
            errorMessageList.add(jaxbe.getMessage());
        } catch (NullPointerException npe) {
            errorMessageList.add("Invalid path entered, please enter a proper path to file");
        }

        showErrorMessage(fileString);

        stage = (Stage) importIngredientXMLButton.getScene().getWindow();
        IngredientTabController.getInstance().updateIngredientTable();
    }

    /**
     * Called by importMenuItemXMLButton to load menu items into database
     */
    public void addMenuItemXML() throws IOException {
        try {
            Paths.get(fileString);
            menu.addMenuItemFromXML(fileString);
            BusinessApp.getBusiness().exportMenuAsXML(BusinessApp.menuXML);
        } catch (JAXBException jaxbe) {
            errorMessageList.add(jaxbe.getMessage());
        } catch (NullPointerException npe) {
            errorMessageList.add("Invalid path entered, please enter a proper path to file");
        }

        showErrorMessage(fileString);

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

        if (errorMessage.length() == 0) {
            feedbackMessage.setText("Import from " + fileName + " is a success!");
            feedbackMessage.setVisible(true);
        } else if (errorMessage.contains("null")) {
            feedbackMessage.setText("File chosen violates dtd. Please choose an appropriate XML file");
            feedbackMessage.setVisible(true);
        } else {
            feedbackMessage.setText(errorMessage);
            errorMessageList.clear();
            feedbackMessage.setVisible(true);
        }
    }


    /**
     * Called by browseButton to open up a dialog for end user to choose file to import.
     */
    public void browseButtonPressed() {
        fileChooser.setTitle("Select XML files");
        selectedFile = fileChooser.showOpenDialog(feedbackMessage.getScene().getWindow());
        if (selectedFile != null) {
            pathString.setText("File selected: " + selectedFile.getName());
            fileString = selectedFile.getPath();
        } else {
            pathString.setText("File selection cancelled.");
        }

    }


}
