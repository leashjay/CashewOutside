package seng202.team3.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seng202.team3.model.Menu;
import seng202.team3.model.MenuItem;
import seng202.team3.model.Order;
import seng202.team3.parsing.MenuLoader;
import seng202.team3.util.ActionButtonTableCell;
import seng202.team3.util.ItemType;
import seng202.team3.util.OrderStatus;
import seng202.team3.view.BusinessApp;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller class for the Menu Item tab in the management section of the GUI
 */
public class MenuItemTabController {

    @FXML
    private Button addManuallyButton;

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
    private TableColumn<MenuItem, Button> editButtonCol;

    @FXML
    private TableColumn<MenuItem, Button> deleteButtonCol;

    @FXML
    private BorderPane menuTabBorderPane;

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

        salePriceCol.setCellValueFactory(cell -> {
            MenuItem item = cell.getValue();
            float salePrice = item.calculateSalePrice();
            DecimalFormat twoDForm = new DecimalFormat("#.##");
            return new SimpleStringProperty("$" + Double.valueOf(twoDForm.format(salePrice)));
        });

        costPriceCol.setCellValueFactory(cell -> {
            MenuItem item = cell.getValue();
            float costPrice = item.getCostPriceFromIngredients();
            DecimalFormat twoDForm = new DecimalFormat("#.##");
            return new SimpleStringProperty("$" + Double.valueOf(twoDForm.format(costPrice)));
        });

        ingredientsCol.setCellFactory(ActionButtonTableCell.forTableColumn("Ingredients", "ingredients-button", MenuItem -> {
            ViewIngredientsController.display(MenuItem);
        }));

        editButtonCol.setCellFactory(ActionButtonTableCell.forTableColumn("Edit", "button", menuItem -> {
            loadAddOrEditMenuItemScreen("Edit Menu Item", menuItem);

        }));

        deleteButtonCol.setCellFactory(ActionButtonTableCell.forTableColumn("Delete", "delete-button", menuItem ->         {
            ConfirmDeletePopup.display('M', "Are you sure you want to delete this?");
            if (delete == true) {
                Boolean canDelete = true;
                HashMap<Integer, Order> listOfOrders = BusinessApp.getBusiness().getSalesHandler().getOrderHashMap();
                for (Map.Entry<Integer, Order> orderEntry : listOfOrders.entrySet()) {
                    if (orderEntry.getValue().getStatus() == OrderStatus.QUEUED) {
                        for (MenuItem item : orderEntry.getValue().getOrderedItems()) {
                            if (item.getId().equals(menuItem.getId())) {
                                canDelete = false;
                                break;
                            }
                        }
                        if (canDelete == false) {
                            break;
                        }
                    }
                }
                if (canDelete) {
                    menu.removeMenuItem(menuItem.getId());
                    delete = false;
                } else {
                    ConfirmDeletePopup.display('M', "Menu item queued for order\n cannot be deleted");
                }
            }
            updateMenuItemTable();
        }));


        List<MenuItem> menuItems = new ArrayList<MenuItem>(BusinessApp.getBusiness().getMenuManager().filterMenuItems().values());
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
        stage.setTitle("Add Menu Item");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public void openAddMenuItemScreen(){
        loadAddOrEditMenuItemScreen("Add Menu Item", null);
    }

    private void loadAddOrEditMenuItemScreen(String title, MenuItem menuItemToEdit) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addmenuitem.fxml"));
            Parent root = loader.load();
            ManuallyAddMenuItemController controller = loader.getController();
            if(menuItemToEdit != null){
                controller.setParameters(menuItemToEdit);
            }
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle(title);
            stage.setScene(new Scene(root, 350, 600));
            stage.showAndWait();

        } catch (IOException e){
            e.printStackTrace();
        }

    }



    /**
     * Updates the MenuItemTable with the most recent data in the menumanager.
     */
    public void updateMenuItemTable() {
        List<MenuItem> menuItems = new ArrayList<MenuItem>(BusinessApp.getBusiness().getMenuManager().filterMenuItems().values());
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
        Stage stage = (Stage) menuTabBorderPane.getScene().getWindow();
        File file = chooser.showSaveDialog(stage);
        if (file != null) {
            menuLoader = new MenuLoader();
            menuLoader.exportMenuData(file.getPath(), menu);
        }
    }

}
