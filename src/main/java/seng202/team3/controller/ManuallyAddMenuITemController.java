package seng202.team3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import seng202.team3.util.InputValidationHelper;
import seng202.team3.util.ItemType;

public class ManuallyAddMenuITemController {

    @FXML
    private TextField idTextField;

    @FXML
    private Text idErrorText;

    @FXML
    private TextField menuItemNameTextField;

    @FXML
    private Text menuItemNameErrorText;

    /**
     * AutoCompleteTextField
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

    public void initialize() {
        itemTypeCheckBox.getItems().add(ItemType.BEVERAGE);
        itemTypeCheckBox.getItems().add(ItemType.MAIN);
        itemTypeCheckBox.getItems().add(ItemType.ASIAN);
        itemTypeCheckBox.getItems().add(ItemType.GRILL);
        itemTypeCheckBox.getItems().add(ItemType.COCKTAIL);
        itemTypeCheckBox.getItems().add(ItemType.OTHER);
        itemTypeCheckBox.getItems().add(ItemType.SNACK);
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
