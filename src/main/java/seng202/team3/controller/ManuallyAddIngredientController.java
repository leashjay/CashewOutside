package seng202.team3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Inventory;
import seng202.team3.model.Supplier;
import seng202.team3.model.SupplierHandler;
import seng202.team3.util.PhoneType;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;
import seng202.team3.view.BusinessApp;

import java.io.IOException;

public class ManuallyAddIngredientController {

    Inventory truckInventory = BusinessApp.getBusiness().getTruck().getTruckInventory();

    @FXML
    private ChoiceBox<UnitType> unitTypeChoiceBox;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField costTextField;

    @FXML
    private CheckBox glutenFreeCheckBox;

    @FXML
    private CheckBox vegetarianCheckBox;

    @FXML
    private CheckBox veganCheckBox;

    @FXML
    private Button addIngredientButton;

    public void initialize(){



        unitTypeChoiceBox.getItems().add(UnitType.UNKNOWN);
        unitTypeChoiceBox.getItems().add(UnitType.COUNT);
        unitTypeChoiceBox.getItems().add(UnitType.GRAM);
        unitTypeChoiceBox.getItems().add(UnitType.ML);

        unitTypeChoiceBox.setValue(UnitType.UNKNOWN);

    }

    //TODO Add input validation on this form

    /**
     * Method to error check fields have been filled and values are correct
     * @return
     */
    public boolean errorCheckFields(){
        if(idTextField.getText().isEmpty()){
            System.out.println("error fill in ID field");
        }
        return  false;
    }

//    private boolean containsInput(){
//        return len()
//    }

    /**
     * Method that adds a supplier using the data that the user has given
     * @throws IOException
     */
    public void addIngredient() throws IOException {
        String id = idTextField.getText();
        String name = nameTextField.getText();
        UnitType unitType = unitTypeChoiceBox.getValue();
        float cost = Float.valueOf(costTextField.getText());

        ThreeValueLogic isGlutenFree;
        ThreeValueLogic isVegan;
        ThreeValueLogic isVegetarian;

        if(glutenFreeCheckBox.isSelected()){
             isGlutenFree = ThreeValueLogic.YES;
        } else {
             isGlutenFree = ThreeValueLogic.NO;
        }

        if(vegetarianCheckBox.isSelected()){
             isVegetarian = ThreeValueLogic.YES;
        } else {
             isVegetarian = ThreeValueLogic.NO;
        }

        if(veganCheckBox.isSelected()){
             isVegan = ThreeValueLogic.YES;
        } else {
             isVegan = ThreeValueLogic.NO;
        }


        Ingredient newIngredient = new Ingredient(id, name, unitType, isVegetarian, isVegan, isGlutenFree, cost);

        truckInventory.addIngredient(newIngredient);
        Stage stage = (Stage) addIngredientButton.getScene().getWindow();

        IngredientTabController.getInstance().updateIngredientTable();

        stage.close();


    }
}
