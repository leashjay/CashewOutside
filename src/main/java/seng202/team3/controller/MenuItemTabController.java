package seng202.team3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import seng202.team3.model.MenuItem;
import seng202.team3.model.Supplier;


public class MenuItemTabController {

    @FXML
    private TableView menuItemsTable;

    @FXML
    private TableColumn<MenuItem, String> idCol;

    @FXML
    private TableColumn<MenuItem, String> nameCol;

    @FXML
    private TableColumn<MenuItem, String> typeCol;

    @FXML
    private TableColumn<MenuItem, String> priceCol;

    @FXML
    private TableColumn<MenuItem, String> servingsCol;

    @FXML
    private TableColumn<MenuItem, Button> viewIngredientsButtonCol;

    @FXML
    private TableColumn<MenuItem, Button> deleteButtonCol;

    
}
