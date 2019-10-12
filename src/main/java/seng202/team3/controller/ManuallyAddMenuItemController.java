package seng202.team3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;
import javafx.scene.text.Text;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Inventory;
import seng202.team3.model.Menu;
import seng202.team3.util.InputValidationHelper;
import seng202.team3.util.ItemType;
import seng202.team3.view.BusinessApp;

import javax.xml.bind.annotation.XmlElement;
import java.util.HashMap;
import java.util.Set;

public class ManuallyAddMenuItemController {

    @FXML
    private TextField idTextField;

    @FXML
    private Text idErrorText;

    @FXML
    private TextField menuItemNameTextField;

    @FXML
    private Text menuItemNameErrorText;

    /**
     * AutoCompleteTextField (search by key)
     **/
    @FXML
    private TextField ingredientKey;

    @FXML
    private Text ingredientKeyErrorText;

    @FXML
    private TextField ingredientQuantity;

    @FXML
    private Text ingredientQuantityErrorText;

    @FXML
    private TextField markupPercent;

    @FXML
    private Text markupPercentErrorText;

    @FXML
    private ChoiceBox<ItemType> itemTypeCheckBox;

    private

    /**
     * current inventory of ingredients for the business and its corresponding
     * hash map storing it all
     */
    Inventory truckInventory = BusinessApp.getBusiness().getTruck().getInventory();
    HashMap<String, Ingredient> stock;

    /**
     * current menu
     */
    Menu currentMenu = BusinessApp.getBusiness().getMenuManager();

    /**
     * List of ingredients and their quantities needed to make the menu item
     */
    private HashMap<Ingredient, Float> ingredients = new HashMap<>();


    public void initialize() {
        Set<String> possibleKeys = truckInventory.getIngredients().keySet();
        TextFields.bindAutoCompletion(ingredientKey, possibleKeys);

        itemTypeCheckBox.getItems().add(ItemType.BEVERAGE);
        itemTypeCheckBox.getItems().add(ItemType.MAIN);
        itemTypeCheckBox.getItems().add(ItemType.ASIAN);
        itemTypeCheckBox.getItems().add(ItemType.GRILL);
        itemTypeCheckBox.getItems().add(ItemType.COCKTAIL);
        itemTypeCheckBox.getItems().add(ItemType.OTHER);
        itemTypeCheckBox.getItems().add(ItemType.SNACK);

        markupPercent.setText("120");
    }

    public void updateUnitText() {

    }

    public void addIngredient() {
        try {
            float quantity = Float.parseFloat(ingredientQuantity.getText());
        }
        catch (NumberFormatException nfe)
        {
            ingredientQuantityErrorText.setVisible(true);
        }

        String id = ingredientKey.getText();
        Ingredient ingredient;
        if (stock.containsKey(id)) {
            ingredient = stock.get(id);
            // TODO: either seperate the ui scroll pane of ingredient and hash map or have them seperated
        } else {
            ingredientKeyErrorText.setVisible(true);
        }
    }


    private boolean checkForErrors() {
        boolean hasError = false;

        if (InputValidationHelper.checkEmpty(idTextField, idErrorText) || InputValidationHelper.checkMenuItemValidId(idTextField, idErrorText) == false) {
            hasError = true;
        }
        if (InputValidationHelper.checkEmpty(menuItemNameTextField, menuItemNameErrorText)) {
            hasError = true;
        }
        if (InputValidationHelper.checkEmpty(ingredientKey, ingredientKeyErrorText)) {
            hasError = true;
        }
        if (InputValidationHelper.isValidFloat(ingredientQuantity, ingredientQuantityErrorText)) {
            hasError = true;
        }
        if (InputValidationHelper.isValidFloat(markupPercent, markupPercentErrorText)) {
            hasError = true;
        }

        return hasError;
    }

    public void addMenuItem() {
        if (checkForErrors() == false) {
            String id = idTextField.getText();
            String name = menuItemNameTextField.getText();
            ItemType itemType = itemTypeCheckBox.getValue();
        }

    }

}
