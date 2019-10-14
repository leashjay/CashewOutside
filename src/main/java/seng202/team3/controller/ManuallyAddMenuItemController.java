package seng202.team3.controller;

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
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

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import java.io.IOException;
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
    private Button addMenuItemButton;

    @FXML
    private Text unitText;

    @FXML
    private Text isGlutenFreeText;
    private boolean isGF;
    private int noGF = 0;

    @FXML
    private Text isVegetarianText;
    private boolean isVege;
    private int noVege = 0;

    @FXML
    private Text isVeganText;
    private boolean isVegan;
    private int noVegan = 0;

    @FXML
    private Text priceString;

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
     * @param cost is cost of the menu item
     * @param price is the price the customer pays for the menu item
     */
    private float cost;
    private float price;

    public float getMarkup() {
        return Float.parseFloat(markupPercent.getText());
    }

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
        idTextField.setDisable(true);
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
        itemTypeCheckBox.setValue(ItemType.OTHER);

        markupPercent.setText("1.1");
        if (editing) {
            cost = menuItem.getCostPriceFromIngredients();
            updatePriceString();
            ingredients = menuItem.getIngredients();
            for (Ingredient ingredient: ingredients.keySet()){
                addIngredientNode(ingredient, this);
            }
        }
    }

    public int calculateServings() {
        int minServings = 999999999;
        for (Ingredient ingredient: ingredients.keySet()){
            if(ingredient.getQuantity() / ingredients.get(ingredient) < minServings) {
                minServings = Math.round(ingredient.getQuantity() / ingredients.get(ingredient));
            }
        }
        return minServings;
    }

    public void updateUnitText() {
        if(stock.containsKey(ingredientKey.getText())) {
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
        }
    }

    public void updatePriceString() {
        price = cost * getMarkup();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String numberAsString = decimalFormat.format(price);
        priceString.setText("$" + numberAsString);
    }

    public void removeIngredient(IngredientNode node) {
        checkFlags(node);
        cost -= node.getQuantity() * node.getIngredient().getCost();
        updatePriceString();
        ingredients.remove(node.getIngredient());
        this.scrollHBox.getChildren().remove(node);
    }

    /**
     * checks if any flags can be put up by decrementing the counts of the applicable flags and
     * if the count is zero put up the flags and sets boolean value
     * double checks that flags are not negative for error prevention
     */
    public void checkFlags(IngredientNode node) {
        if (node.getIngredient().getIsGlutenFree() == ThreeValueLogic.NO) {
            noGF -= 1;
            if (noGF == 0) {
                isGF = true;
                isGlutenFreeText.setVisible(true);
            } else if(noGF < 0) {
                noGF = 0;
            }
        }
        if (node.getIngredient().getIsVegan() == ThreeValueLogic.NO) {
            noVegan -= 1;
            if (noVegan == 0) {
                isVegan = true;
                isVeganText.setVisible(true);
            } else if(noVegan < 0) {
                noVegan = 0;
            }
        }
        if (node.getIngredient().getIsVegetarian() == ThreeValueLogic.NO) {
            noVege -= 1;
            if (noVege == 0) {
                isVege = true;
                isVegetarianText.setVisible(true);
            } else if(noVege < 0) {
                noVege = 0;
            }
        }
    }

    /**
     * adds ingredient node with delete button to the scroll pane
     */
    public void addIngredientNode(Ingredient ingredient, ManuallyAddMenuItemController parent) {
        getScrollHBox().getChildren().add(new IngredientNode(ingredient, parent));
    }

    /**
     * adds ingredient to a scroll pane and to the ingredients hashmap for the menuitem
     * shows the error texts and aborts if invalid inputs are given
     */
    public void addIngredient() {
        //get and check ingredient and quantity values
        if (InputValidationHelper.checkEmpty(ingredientKey, ingredientKeyErrorText)) {
            return;
        }
        if (!InputValidationHelper.isValidFloat(ingredientQuantity, ingredientQuantityErrorText)) {
            return;
        }
        float quantity = getQuantityText();
        String id = ingredientKey.getText();
        Ingredient ingredient;
        //if id is valid
        if (stock.containsKey(id)) {
            ingredient = stock.get(id);

            setFlagsOnAdd(ingredient);
            addIngredientNode(ingredient, this);

            //setting cost string
            cost += quantity * ingredient.getCost();
            updatePriceString();


        } else {
            ingredientKeyErrorText.setVisible(true);
            return;
        }

        //reset text fields
        ingredientKey.setText("");
        ingredientQuantity.setText("");
    }

    /**
     * sets the flags for the menuitem when a new ingredient is added and removes the flags if needed
     * and increases the counts for items with the flags that apply
     */
    public void setFlagsOnAdd(Ingredient ingredient) {
        if (ingredient.getIsGlutenFree() == ThreeValueLogic.NO) {
            noGF += 1;
            isGF = false;
            isGlutenFreeText.setVisible(false);
        }
        if (ingredient.getIsVegan() == ThreeValueLogic.NO) {
            noVegan += 1;
            isVegan = false;
            isVeganText.setVisible(false);
        }
        if (ingredient.getIsVegetarian() == ThreeValueLogic.NO) {
            noVege += 1;
            isVege = false;
            isVegetarianText.setVisible(false);
        }
    }

    /**
     * checks for errors on the attributes that apply to the individual ingredients
     * @return if menuitem feilds have errors
     */
    private boolean checkForErrorsIngredient() {
        boolean hasError = false;
        if (InputValidationHelper.checkEmpty(ingredientKey, ingredientKeyErrorText)) {
            hasError = true;
        }
        if (InputValidationHelper.isValidFloat(ingredientQuantity, ingredientQuantityErrorText)) {
            hasError = true;
        }

        return hasError;
    }

    /**
     * checks for errors on the attributes that apply to the menuitem as opposed to individual ingredients
     * @return if menuitem feilds have errors
     */
    private boolean checkForErrorsMenu() {
        boolean hasError = false;

        if(!editing){
            if (InputValidationHelper.checkEmpty(idTextField, idErrorText) || InputValidationHelper.checkMenuItemValidId(idTextField, idErrorText) == false) {
                hasError = true;
            }
        }
        
        if (InputValidationHelper.checkEmpty(menuItemNameTextField, menuItemNameErrorText)) {
            hasError = true;
        }
        if (!InputValidationHelper.isValidFloat(markupPercent, markupPercentErrorText)) {
            hasError = true;
        }

        return hasError;
    }

    /**
     * adds the menuitem from the data that the user has given
     * closes window and updates the table
     */
    public void addMenuItem() throws JAXBException, IOException {
        if (checkForErrorsMenu() == false) {
            String id = idTextField.getText();
            String name = menuItemNameTextField.getText();
            ItemType itemType = itemTypeCheckBox.getValue();
            MenuItem menuItem = new MenuItem(id, name, ingredients, itemType);
            if (editing) {
                currentMenu.filterMenuItems().replace(id, menuItem);
            } else {
                currentMenu.filterMenuItems().put(id, menuItem);
            }
            idTextField.setText("");
            menuItemNameTextField.setText("");
            ingredientQuantity.setText("");
            ingredientKey.setText("");

            Stage stage = (Stage) addMenuItemButton.getScene().getWindow();

            MenuItemTabController.getInstance().updateMenuItemTable();

            stage.close();

            BusinessApp.getBusiness().exportMenuAsXML(BusinessApp.menuXML);

        }

    }

}
