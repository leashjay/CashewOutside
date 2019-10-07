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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seng202.team3.model.Menu;
import seng202.team3.model.MenuItem;
import seng202.team3.parsing.MenuLoader;
import seng202.team3.util.ActionButtonTableCell;
import seng202.team3.util.ItemType;
import seng202.team3.view.BusinessApp;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for the Menu Item tab in the management section of the GUI
 */
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

    @FXML
    private AnchorPane menuTabAnchorPane;

    private static MenuItemTabController instance;

    private Menu menu = BusinessApp.getBusiness().getMenuManager();

    private FileChooser chooser;

    private MenuLoader menuLoader;

    /**
     * Holds an instance of the MenuItemTabController class so other controllers can call its methods
     */
    public MenuItemTabController() {
        instance = this;
    }

    public static boolean delete = false;

    /**
     * Returns an instance of the MenuItemTabController so other controller classes can use its methods
     * @return an instance of the MenuItemTabController class
     */
    public static MenuItemTabController getInstance() {
        return instance;
    }

    /**
     * Method called to initialize values relating to the TableView and GUI in general
     */
    public void initialize() {

        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        servingsCol.setCellValueFactory(new PropertyValueFactory<>("Servings"));
        costPriceCol.setCellValueFactory(new PropertyValueFactory<>("SalePrice"));
        salePriceCol.setCellValueFactory(new PropertyValueFactory<>("CostPrice"));

        ingredientsCol.setCellFactory(ActionButtonTableCell.forTableColumn("Ingredients ↓", "ingredients-button", MenuItem -> {
            //TODO implement logic for showing ingredients in menu item
        }));

        deleteButtonCol.setCellFactory(ActionButtonTableCell.forTableColumn("Delete", "delete-button", menuItem ->         {
            ConfirmDeletePopup.display('M');
            if (delete == true) {
                delete = false;
                menu.removeMenuItem(menuItem.getId());
            }
            updateMenuItemTable();
        }));


        List<MenuItem> menuItems = new ArrayList<MenuItem>(BusinessApp.getBusiness().getMenuManager().getMenuItem().values());
        menuItemsTable.setItems(FXCollections.observableArrayList(menuItems));

    }

    /**
     * Opens the screen to add a menu item from XML
     * @throws IOException
     */
    public void openAddMenuItemXMLScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addmenuitemxml.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Add supplier");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    /**
     * Updates the MenuItemTable with the most recent data in the menumanager.
     */
    public void updateMenuItemTable() {
        List<MenuItem> menuItems = new ArrayList<MenuItem>(BusinessApp.getBusiness().getMenuManager().getMenuItem().values());
        menuItemsTable.setItems(FXCollections.observableArrayList(menuItems));
    }

    /**
     * Create file chooser and set extension filter to only export XML file
     */
    private void initializeFileChooser() {
        chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Files (*.xml)|*.xml", "*.xml");
        chooser.getExtensionFilters().add(extFilter);
    }

    /**
     * Export menu data as xml in chosen directory
     */
    public void exportMenuXML() throws JAXBException, IOException {
        initializeFileChooser();
        Stage stage = (Stage) menuTabAnchorPane.getScene().getWindow();
        File file = chooser.showSaveDialog(stage);
        if (file != null) {
            menuLoader = new MenuLoader();
            menuLoader.exportMenuData(file.getPath(), menu);
        }
    }

}
