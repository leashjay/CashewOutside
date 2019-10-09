package seng202.team3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Inventory;
import seng202.team3.util.InputValidationHelper;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;
import seng202.team3.view.BusinessApp;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Controller class for a user manually adding an ingredient.
 */
public class ManuallyAddIngredientController {

    Inventory truckInventory = BusinessApp.getBusiness().getTruck().getInventory();

    @FXML
    private ChoiceBox<UnitType> unitTypeChoiceBox;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    private Text quantityErrorText;

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

    @FXML
    private Text idErrorText;

    @FXML
    private Text nameErrorText;

    @FXML
    private Text costErrorText;

    boolean editing = false;

    Ingredient ingredientBeingEdited;

    /**
     * Sets the value of the form inputs to show the current values of the ingredient.
     * @param ingredient the ingredient we are showing information about
     */
    public void setParameters(Ingredient ingredient) {
        editing = true;
        ingredientBeingEdited = ingredient;
        idTextField.setDisable(true);
        nameTextField.setText(ingredient.getName());
        unitTypeChoiceBox.setValue(ingredient.getUnit());
        quantityTextField.setText(String.valueOf(ingredient.getQuantity()));
        costTextField.setText(String.valueOf(ingredient.getCost()));
        glutenFreeCheckBox.setSelected(ingredient.getIsGlutenFree() == ThreeValueLogic.YES);
        veganCheckBox.setSelected(ingredient.getIsVegan() == ThreeValueLogic.YES);
        vegetarianCheckBox.setSelected(ingredient.getIsVegetarian() == ThreeValueLogic.YES);
    }

    /**
     * Method called to set the initial values and the GUI for the form.
     */
    public void initialize(){
        unitTypeChoiceBox.getItems().add(UnitType.UNKNOWN);
        unitTypeChoiceBox.getItems().add(UnitType.COUNT);
        unitTypeChoiceBox.getItems().add(UnitType.GRAM);
        unitTypeChoiceBox.getItems().add(UnitType.ML);

        unitTypeChoiceBox.setValue(UnitType.UNKNOWN);

        if(editing){
            System.out.println("currently editing");
        }

    }



    /**
     * Method to error check fields have been filled and values are correct
     * @return a boolean which shows true if there are errors, false if no errors.
     */
    private boolean checkForErrors(){
        boolean hasError = false;


        if (InputValidationHelper.checkEmpty(idTextField, idErrorText) == true || (InputValidationHelper.checkIngredientValidId(idTextField, idErrorText) == false)) {
            hasError = true;
        }
        if(InputValidationHelper.checkEmpty(nameTextField, nameErrorText)){
            hasError = true;
        }

        if (InputValidationHelper.isValidFloat(quantityTextField, quantityErrorText) == false){
            hasError = true;
        }

        if(!InputValidationHelper.isValidFloat(costTextField, costErrorText)){
            hasError = true;
        }

        return hasError;
    }


    /**
     * Method that adds a supplier using the data that the user has given
     */
    public void addIngredient() throws JAXBException, IOException {
        if (checkForErrors() == false) {

            String id;
            if(editing){
                 id = ingredientBeingEdited.getCode();
            } else {
                 id = idTextField.getText();
            }

            String name = nameTextField.getText();
            UnitType unitType = unitTypeChoiceBox.getValue();
            float cost = Float.parseFloat(costTextField.getText());
            float quantity = Float.parseFloat(quantityTextField.getText());

            ThreeValueLogic isGlutenFree;
            ThreeValueLogic isVegan;
            ThreeValueLogic isVegetarian;

            if (glutenFreeCheckBox.isSelected()) {
                isGlutenFree = ThreeValueLogic.YES;
            } else {
                isGlutenFree = ThreeValueLogic.NO;
            }

            if (vegetarianCheckBox.isSelected()) {
                isVegetarian = ThreeValueLogic.YES;
            } else {
                isVegetarian = ThreeValueLogic.NO;
            }

            if (veganCheckBox.isSelected()) {
                isVegan = ThreeValueLogic.YES;
            } else {
                isVegan = ThreeValueLogic.NO;
            }


            Ingredient newIngredient = new Ingredient(id, name, unitType, isVegetarian, isVegan, isGlutenFree, cost, quantity);

            //TODO if editing don't add, edit
            truckInventory.addIngredient(newIngredient);


            Stage stage = (Stage) addIngredientButton.getScene().getWindow();

            IngredientTabController.getInstance().updateIngredientTable();

            stage.close();

            BusinessApp.getBusiness().exportInventoryAsXML(BusinessApp.ingredientsXML);


        }

    }
}

