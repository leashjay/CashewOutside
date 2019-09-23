package seng202.team3.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.team3.model.Menu;
import seng202.team3.model.MenuItem;
import seng202.team3.model.Supplier;
import seng202.team3.util.ItemType;
import seng202.team3.view.BusinessApp;

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

    public void initialize() {

        // PropertyValueFactory uses your getter, so you MUST have a getter matching getX, where X is whatever you put as the string in the object your table is on.
        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        servingsCol.setCellValueFactory(new PropertyValueFactory<>("Servings"));
        costPriceCol.setCellValueFactory(new PropertyValueFactory<>("SalePrice"));
        salePriceCol.setCellValueFactory(new PropertyValueFactory<>("CostPrice"));

        ingredientsCol.setCellFactory(ActionButtonTableCell.forTableColumn("Ingredients ↓", MenuItem -> {
            // You can put whatever logic in here, or even open a new window.
            // For example here we'll just toggle the isGf
            //foodItem.setGlutenFree(!foodItem.isGlutenFree());
            //foodItemsTable.refresh(); // Have to trigger a table refresh to make it show up in the table
            System.out.println("BUTTON CLICKED");
        }));

        deleteButtonCol.setCellFactory(ActionButtonTableCell.forTableColumn("Delete", MenuItem -> {
           //remove The menuitem
        }));

        List<MenuItem> menuItems = new ArrayList<MenuItem>(BusinessApp.getBusiness().getMenuManager().getMenuItem().values());
        //List<Supplier> suppliers = createTestData(); // This would come from your real data however you access that.
        menuItemsTable.setItems(FXCollections.observableArrayList(menuItems));
    }
}
