package seng202.team3.controller;

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.controlsfx.control.textfield.TextFields;
import javafx.scene.text.Text;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Inventory;
import seng202.team3.model.Menu;
import seng202.team3.model.MenuItem;
import seng202.team3.util.InputValidationHelper;
import seng202.team3.util.ItemType;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;
import seng202.team3.view.BusinessApp;

import javax.xml.bind.annotation.XmlElement;
import java.text.DecimalFormat;
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

    @FXML
    private Button addIngredientButton;

    @FXML
    private Text unitText;

    @FXML
    private Text isGlutenFreeText;

    @FXML
    private Text isVegetarianText;

    @FXML
    private Text isVeganText;

    @FXML
    private Text costString;

    @FXML
    private HBox scrollHBox;

    private MenuItem menuItem;

    private boolean editing = false;

    /**
     * current inventory of ingredients for the business and its corresponding
     * hash map storing it all
     */
    Inventory truckInventory = BusinessApp.getBusiness().getTruck().getInventory();
    HashMap<String, Ingredient> stock = truckInventory.getIngredients();

    /**
     * current menu
     */
    Menu currentMenu = BusinessApp.getBusiness().getMenuManager();

    /**
     * List of ingredients and their quantities needed to make the menu item
     */
    private HashMap<Ingredient, Float> ingredients = new HashMap<>();

    /**
     * cost of the menu item
     */
    private float cost;

    public HBox getScrollHBox() {
        return scrollHBox;
    }

    public float getQuantityText() {
        return Float.parseFloat(ingredientQuantity.getText());
    }

    public void setParameters(MenuItem menuItemToEdit){
        editing = true;
        menuItem = menuItemToEdit;
        menuItemNameTextField.setText(menuItemToEdit.getName());
        idTextField.setText(menuItemToEdit.getId());
        itemTypeCheckBox.setValue(menuItemToEdit.getType());
        markupPercent.setText(String.valueOf(menuItemToEdit.getMarkup()));

    }

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

        markupPercent.setText("1.1");
        if (editing) {
            cost = menuItem.getCostPriceFromIngredients();
            updateCostString();
        }
    }

    public void calculateServings() {

    }

    public void updateUnitText() {
        System.out.println("in");
        if(stock.containsKey(ingredientKey.getId())) {
            Ingredient ingredient = stock.get(ingredientKey.getText());
            UnitType unit = ingredient.getUnit();
            switch (unit) {
                case GRAM:
                    unitText.setText("grams");
                    break;
                case ML:
                    unitText.setText("mL");
                    break;
                case COUNT:
                    unitText.setText("units");
                    break;
                case UNKNOWN:
                    unitText.setText("");
                    break;
            }
        } else {
            unitText.setText("");
            System.out.println("not reading");
        }
    }

    public void updateCostString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String numberAsString = decimalFormat.format(cost);
        costString.setText("$" + numberAsString);
    }

    public void removeIngredient() {
        return;
    }


    public void addIngredient() {
        //get and check ingredient and quantity values
        if (InputValidationHelper.checkEmpty(ingredientKey, ingredientKeyErrorText)) {
            return;
        }
        if (InputValidationHelper.isValidFloat(ingredientQuantity, ingredientQuantityErrorText)) {
            return;
        }
        float quantity = getQuantityText();
        String id = ingredientKey.getText();
        Ingredient ingredient;
        //if id is valid
        if (stock.containsKey(id)) {
            ingredient = stock.get(id);

            //set flags
            if (ingredient.getIsGlutenFree() == ThreeValueLogic.UNKNOWN || ingredient.getIsGlutenFree() == ThreeValueLogic.NO) {
                isGlutenFreeText.setVisible(false);
            }
            if (ingredient.getIsVegan() == ThreeValueLogic.UNKNOWN || ingredient.getIsVegan() == ThreeValueLogic.NO) {
                isVeganText.setVisible(false);
            }
            if (ingredient.getIsVegetarian() == ThreeValueLogic.UNKNOWN || ingredient.getIsVegetarian() == ThreeValueLogic.NO) {
                isVegetarianText.setVisible(false);
            }

            //setting cost string
            cost += quantity * ingredient.getCost();
            updateCostString();

            ingredients.put(ingredient, quantity);

        } else {
            ingredientKeyErrorText.setVisible(true);
            return;
        }

        //reset text fields
        ingredientKey.setText("");
        ingredientQuantity.setText("");
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
