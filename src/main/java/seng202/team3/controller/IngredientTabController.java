package seng202.team3.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Supplier;
import seng202.team3.util.ItemType;
import seng202.team3.util.PhoneType;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class IngredientTabController {

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
    private TableColumn<Ingredient, Button> editButtonCol;

    @FXML
    private TableColumn<Ingredient, ThreeValueLogic> glutenFreeCol;

    @FXML
    private TableColumn<Ingredient, ThreeValueLogic> vegetarianCol;

    @FXML
    private TableColumn<Ingredient, ThreeValueLogic> veganCol;

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


        editButtonCol.setCellFactory(ActionButtonTableCell.forTableColumn("Edit", Ingredient -> {
            // You can put whatever logic in here, or even open a new window.
            // For example here we'll just toggle the isGf
            //foodItem.setGlutenFree(!foodItem.isGlutenFree());
            //foodItemsTable.refresh(); // Have to trigger a table refresh to make it show up in the table
            System.out.println("BUTTON CLICKED");
        }));

        List<Ingredient> ingredients = createTestData(); // This would come from your real data however you access that.
        ingredientTable.setItems(FXCollections.observableArrayList(ingredients));


    }

    private List<Ingredient> createTestData() {
        return List.of(
                new Ingredient("1", "Beans", 500f, UnitType.GRAM, 10f),
                new Ingredient("2", "Greens", 100f, UnitType.ML, 20f),
                new Ingredient("3", "Tomatoes", 30f, UnitType.COUNT, 30f )
        );
    }


}
