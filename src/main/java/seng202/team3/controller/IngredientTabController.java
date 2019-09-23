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
import seng202.team3.model.Ingredient;
import seng202.team3.model.Inventory;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;
import seng202.team3.view.BusinessApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IngredientTabController {

    @FXML
    private Button addManuallyButton;

    @FXML
    private Button addFromXMLButton;

    @FXML
    private TableView<Ingredient> ingredientTable;

    @FXML
    private TableColumn<Ingredient, String> idCol;

    @FXML
    private TableColumn<Ingredient, String> nameCol;

    @FXML
    private TableColumn<Ingredient, String> quantityCol;

    @FXML
    private TableColumn<Ingredient, UnitType> unitTypeCol;

    @FXML
    private TableColumn<Ingredient, String> costPerUnitCol;

    @FXML
    private TableColumn<Ingredient, Button> deleteButtonCol;

    @FXML
    private TableColumn<Ingredient, ThreeValueLogic> glutenFreeCol;

    @FXML
    private TableColumn<Ingredient, ThreeValueLogic> vegetarianCol;

    @FXML
    private TableColumn<Ingredient, ThreeValueLogic> veganCol;

    private static IngredientTabController instance;

    private Inventory inventory = BusinessApp.getBusiness().getTruck().getInventory();

    /**
     * Holds an instance of the IngredientTabController class so other controllers can call it's methods
     */
    public IngredientTabController(){
        instance = this;
    }

    /**
     * Returns an instance of the IngredientTabController so other controller classes can use its methods
     * @return an instance of the IngredientTabController class
     */
    public static IngredientTabController getInstance(){
        return instance;
    }

    public void initialize() {

        // PropertyValueFactory uses your getter, so you MUST have a getter matching getX, where X is whatever you put as the string in the object your table is on.
        idCol.setCellValueFactory(new PropertyValueFactory<>("Code"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        glutenFreeCol.setCellValueFactory(new PropertyValueFactory<>("isGlutenFree"));
        vegetarianCol.setCellValueFactory(new PropertyValueFactory<>("isVegetarian"));
        veganCol.setCellValueFactory(new PropertyValueFactory<>("isVegan"));
        unitTypeCol.setCellValueFactory(new PropertyValueFactory<>("Unit"));
        costPerUnitCol.setCellValueFactory(new PropertyValueFactory<>("Cost"));

        deleteButtonCol.setCellFactory(ActionButtonTableCell.forTableColumn("Delete", ingredient -> {
            inventory.removeIngredient(ingredient.getCode());
            updateIngredientTable();
            System.out.println("Delete BUTTON CLICKED");
        }));

        List<Ingredient> ingredients = new ArrayList<Ingredient>(BusinessApp.getBusiness().getTruck().getInventory().getIngredients().values());
        //List<Supplier> suppliers = createTestData(); // This would come from your real data however you access that.
        ingredientTable.setItems(FXCollections.observableArrayList(ingredients));
    }

    public void openAddIngredientScreen(){
        System.out.println("Add Ingredient button clicked");
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/view/addingredient.fxml"));
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

    public void openAddIngredientXMLScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addingredientxml.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Add supplier");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public void updateIngredientTable(){
        List<Ingredient> ingredients= new ArrayList<Ingredient>(BusinessApp.getBusiness().getTruck().getInventory().getIngredients().values());
        ingredientTable.setItems(FXCollections.observableArrayList(ingredients));
        System.out.println("Update ingredient table called");
    }



}
