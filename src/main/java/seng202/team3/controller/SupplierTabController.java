package seng202.team3.controller;

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
import seng202.team3.model.Supplier;
import seng202.team3.model.SupplierHandler;
import seng202.team3.util.ActionButtonTableCell;
import seng202.team3.util.PhoneType;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.view.BusinessApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for the suppliertab in the management section of the GUI
 */
public class SupplierTabController

{

    private SupplierHandler supplierHandler = BusinessApp.getBusiness().getSupplierHandler();
    private static SupplierTabController instance;

    /**
     * Holds an instance of the SupplierTabController class so other controllers can call it's methods
     */
    public SupplierTabController(){
        instance = this;
    }

    /**
     * Returns an instance of the SupplierTabController so other controller classes can use its methods
     * @return an instance of the SupplierTabController class
     */
    public static SupplierTabController getInstance(){
        return instance;
    }

    @FXML
    private TableView<Supplier> supplierTable;

    @FXML
    private TableColumn<Supplier, String> idCol;

    @FXML
    private TableColumn<Supplier, String> nameCol;

    @FXML
    private TableColumn<Supplier, String> addressCol;

    @FXML
    private TableColumn<Supplier, String> emailCol;

    @FXML
    private TableColumn<Supplier, String> phoneNumberCol;

    @FXML
    private TableColumn<Supplier, ThreeValueLogic> phoneTypeCol;

    @FXML
    private TableColumn<Supplier, String> urlCol;

    @FXML
    private TableColumn<Supplier, Button> deleteButtonCol;

    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("Sid"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        urlCol.setCellValueFactory(new PropertyValueFactory<>("Url"));
        phoneTypeCol.setCellValueFactory(new PropertyValueFactory<>("phoneType"));

        deleteButtonCol.setCellFactory(ActionButtonTableCell.forTableColumn("Delete", "delete-button", supplier -> {
            supplierHandler.removeSupplier(supplier.getSid());
            updateSupplierTable();
        }));

        List<Supplier> suppliers = new ArrayList<Supplier>(BusinessApp.getBusiness().getSupplierHandler().getSuppliers().values());
        supplierTable.setItems(FXCollections.observableArrayList(suppliers));
    }

    /**
     * Opens the screen to manually add a supplier.
     */
    public void openAddSupplierScreen(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/view/addsupplier.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Add supplier");
            stage.setScene(new Scene(root, 350, 600));
            stage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Opens the screen to add a supplier from XML
     * @throws IOException the exception the method could throw.
     */
    public void openAddSupplierXMLScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addsupplierxml.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Add supplier");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }


    /**
     * Adds the Supplier the user has inputted data for to the suppliers list and closes the table.
     */
    public void updateSupplierTable(){
        List<Supplier> suppliers = new ArrayList<Supplier>(BusinessApp.getBusiness().getSupplierHandler().getSuppliers().values());
        supplierTable.setItems(FXCollections.observableArrayList(suppliers));
        System.out.println("Update supplier table called");
    }

}
