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
import seng202.team3.util.PhoneType;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.view.BusinessApp;

import java.io.IOException;
import java.util.List;

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

    /**
     * Each TableColumn first has the class of the column (FoodItem in this case),
     * and secondly the class of what will be shown in that column.
     *
     * Most of the time the second arg will just be String.
     */
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

    /**
     * Note here that the second arg is Button rather than String
     */
    @FXML
    private TableColumn<Supplier, Button> editButtonCol;

    @FXML
    private TableColumn<Supplier, Button> deleteButtonCol;

    public void initialize() {

        // PropertyValueFactory uses your getter, so you MUST have a getter matching getX, where X is whatever you put as the string in the object your table is on.
        idCol.setCellValueFactory(new PropertyValueFactory<>("Sid"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        urlCol.setCellValueFactory(new PropertyValueFactory<>("Url"));
        phoneTypeCol.setCellValueFactory(new PropertyValueFactory<>("phoneType"));


        editButtonCol.setCellFactory(ActionButtonTableCell.forTableColumn("Edit", Ingredient -> {
            // You can put whatever logic in here, or even open a new window.
            // For example here we'll just toggle the isGf
            //foodItem.setGlutenFree(!foodItem.isGlutenFree());
            //foodItemsTable.refresh(); // Have to trigger a table refresh to make it show up in the table
            System.out.println("BUTTON CLICKED");
        }));

        deleteButtonCol.setCellFactory(ActionButtonTableCell.forTableColumn("Delete", supplier -> {
            supplierHandler.removeSupplier(supplier.getSid());
            updateSupplierTable();
            System.out.println("Delete BUTTON CLICKED");
        }));

        List<Supplier> suppliers = BusinessApp.getBusiness().getSupplierHandler().getSuppliersAsObservableList();
        //List<Supplier> suppliers = createTestData(); // This would come from your real data however you access that.
        supplierTable.setItems(FXCollections.observableArrayList(suppliers));
        updateSupplierTable();


    }

    private List<Supplier> createTestData() {

        return List.of(
                new Supplier("1", "Countdown", "52 Church Corner", PhoneType.WORK, "9593145", "countdown@gmail.com", "www.countdown.com"),
                new Supplier("2", "Pak n Save", "20 Riccarton Road", PhoneType.WORK, "89137841", "paknsave@gmail.com", "www.paknsave.com"),
                new Supplier("3", "New World", "66 Ilam Road", PhoneType.WORK, "1324903", "newworld@gmail.com", "www.newworld.com")
        );
    }

    public void openAddSupplierScreen(){
        System.out.println("Add supplier button clicked");
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/view/addsupplier.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Add supplier");
            stage.setScene(new Scene(root, 350, 500));
            stage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Adds the Supplier the user has inputted data for to the suppliers list and closes the table.
     */
    public void updateSupplierTable(){
        List<Supplier> suppliers = BusinessApp.getBusiness().getSupplierHandler().getSuppliersAsObservableList();
        supplierTable.setItems(FXCollections.observableArrayList(suppliers));
        System.out.println("Update supplier table called");
    }

}
