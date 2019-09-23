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
import seng202.team3.model.MenuItem;
import seng202.team3.util.ItemType;
import seng202.team3.view.BusinessApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MenuItemTabController {

    @FXML
    private TableView menuItemsTable;

    @FXML
    private TableColumn<MenuItem, String> idCol;

    @FXML
    private TableColumn<MenuItem, String> nameCol;

    @FXML
    private TableColumn<ItemType, String> typeCol;

    @FXML
    private TableColumn<MenuItem, String> costPriceCol;

    @FXML
    private TableColumn<MenuItem, String> salePriceCol;

    @FXML
    private TableColumn<MenuItem, String> servingsCol;

    @FXML
    private TableColumn<MenuItem, Button> ingredientsCol;

    @FXML
    private TableColumn<MenuItem, Button> deleteButtonCol;

    private static MenuItemTabController instance;

    /**
     * Holds an instance of the MenuItemTabController class so other controllers can call its methods
     */

    public MenuItemTabController() {
        instance = this;
    }


    /**
     * Returns an instance of the MenuItemTabController so other controller classes can use its methods
     *
     * @return an instance of the MenuItemTabController class
     */
    public static MenuItemTabController getInstance() {
        return instance;
    }

    public void initialize() {

        // PropertyValueFactory uses your getter, so you MUST have a getter matching getX, where X is whatever you put as the string in the object your table is on.
        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        servingsCol.setCellValueFactory(new PropertyValueFactory<>("Servings"));
        costPriceCol.setCellValueFactory(new PropertyValueFactory<>("SalePrice"));
        salePriceCol.setCellValueFactory(new PropertyValueFactory<>("CostPrice"));

        ingredientsCol.setCellFactory(ActionButtonTableCell.forTableColumn("Ingredients ↓", "ingredients-button", MenuItem -> {
            // You can put whatever logic in here, or even open a new window.
            // For example here we'll just toggle the isGf
            //foodItem.setGlutenFree(!foodItem.isGlutenFree());
            //foodItemsTable.refresh(); // Have to trigger a table refresh to make it show up in the table

        }));

        deleteButtonCol.setCellFactory(ActionButtonTableCell.forTableColumn("Delete", "delete-button", MenuItem -> {
            //remove The menuitem
        }));

        List<MenuItem> menuItems = new ArrayList<MenuItem>(BusinessApp.getBusiness().getMenuManager().getMenuItem().values());
        //List<Supplier> suppliers = createTestData(); // This would come from your real data however you access that.
        menuItemsTable.setItems(FXCollections.observableArrayList(menuItems));
    }

    public void openAddMenuItemXMLScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addmenuitemxml.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Add supplier");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public void updateMenuItemTable() {
        List<MenuItem> menuItems = new ArrayList<MenuItem>(BusinessApp.getBusiness().getMenuManager().getMenuItem().values());
        menuItemsTable.setItems(FXCollections.observableArrayList(menuItems));
    }
}
