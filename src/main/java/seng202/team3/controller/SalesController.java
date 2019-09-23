package seng202.team3.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import seng202.team3.model.Business;
import seng202.team3.model.MenuItem;
import seng202.team3.model.Order;
import seng202.team3.model.SalesHandler;
import seng202.team3.util.ItemType;
import seng202.team3.util.StringChecking;
import seng202.team3.view.BusinessApp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class SalesController {

    @FXML
    private Button backButton;

    @FXML
    private HBox currentOrderHBox;

    @FXML
    private GridPane foodItemGrid;

    @FXML
    private GridPane drinkItemGrid;

    @FXML
    private Label costLabel;

    @FXML
    private TextField currentOrderNameTextField;

    @FXML
    private Label orderIDValueLabel;

    @FXML
    private Label numOfItemsValueLabel;

    @FXML
    private Label priceValueLabel;

    @FXML
    private Label nameErrorLabel;

    @FXML
    private Label numItemsErrorLabel;

    @FXML
    private Label bigBoy;

    private final Insets gridPadding = new Insets(50, 50, 50, 50);
    private SalesHandler salesManager;
    private Order curOrder;
    private Business business;
    private final float rowHeight = 150;
    private HashMap<MenuItem, MenuItemNode> currentOrderHBoxMenuItems = new HashMap<>();


    /**
     * sends the user back to the main screen.
     * Some kind of clean up / persistence should be implemented here.
     * @throws IOException idk?
     */
    public void backButtonPressed() throws IOException {
        System.out.println("Back button pressed");
        BusinessApp.loadMainPage();
    }

    /**
     * used to setup the buttons and etc in the scene.
     * *IMPORTANT*
     * This method is called automatically by the FXMLLoader
     */
    public void initialize() {

        business = BusinessApp.getBusiness();
        curOrder = new Order();
        salesManager = business.getSalesHandler();
        // declare what ItemTypes are assigned to which GridPane
        Set<ItemType> drinkMenuItemTypes = Set.of(ItemType.BEVERAGE, ItemType.COCKTAIL);
        Set<ItemType> foodMenuItemTypes = Set.of(ItemType.MAIN, ItemType.ASIAN, ItemType.GRILL, ItemType.OTHER, ItemType.SNACK);

        // retrieve HashMaps of MenuItems to populate GridPanes
        HashMap<String, MenuItem> foodMenuItems = business.getMenuManager().getMenuItem(foodMenuItemTypes);
        HashMap<String, MenuItem> drinkMenuItems = business.getMenuManager().getMenuItem(drinkMenuItemTypes);
        setUpGridPane(foodItemGrid);
        setUpGridPane(drinkItemGrid);
        addMenuItemButtonsToGridPane(foodMenuItems, foodItemGrid);
        updateLabels();
        addMenuItemButtonsToGridPane(drinkMenuItems, drinkItemGrid);
    }

    /**
     * sets the gridPane to fill the width of the window.
     * @param gridPane
     */
    private void setUpGridPane(GridPane gridPane) {

        gridPane.setPrefWidth(950);
        ObservableList<ColumnConstraints> columnConstraintsList = gridPane.getColumnConstraints();
        float percentageWidth = 100f / columnConstraintsList.size(); // 100f is 100 as a float.

        for (ColumnConstraints columnConstraints : columnConstraintsList) {
            columnConstraints.setPercentWidth(percentageWidth);
        }

        ObservableList<RowConstraints> rowConstraintsList = gridPane.getRowConstraints();
        for (RowConstraints rowConstraints: rowConstraintsList) {
            rowConstraints.setPrefHeight(this.rowHeight);
        }
    }

    /**
     * will not overwrite any current buttons.
     * @param menuItems the MenuItems the buttons will add to the Order
     * @param gridPane the GridPane to add the buttons to
     */

    private void addMenuItemButtonsToGridPane(HashMap<String, MenuItem> menuItems, GridPane gridPane) {

        final int numChildrenAtStart = gridPane.getChildren().size();
        final int numColumnsAtStart = gridPane.getColumnCount();
        final int numRowsAtStart = gridPane.getRowCount();

        int row = numChildrenAtStart / numColumnsAtStart; // Java Integer Division, so floors the result
        int column = numChildrenAtStart % numColumnsAtStart;

        for (MenuItem menuItem : menuItems.values()) {
            Button newButton = new Button();
            // TODO Find out how to set this button's style to styles.css #foodButton
            // TODO Have the buttons display their flags (gf, veg, vegan)
            // TODO Format GridPane properly.

            newButton.setStyle("-fx-background-radius: 10;-fx-border-color: #273746;-fx-border-radius: 10;" +
                    "-fx-pref-width: 100;-fx-pref-height: 100;-fx-background-color: #00bcd4;-fx-wrap-text: true;");

            newButton.setPadding(gridPadding); // This line maybe irrelevant, unsure right now.
            GridPane.setConstraints(newButton, column, row, 1, 1, HPos.CENTER, VPos.CENTER);
            newButton.setText(menuItem.getName());
            newButton.setOnAction(e -> addToCurrentOrder(menuItem)); // lambda function
            gridPane.add(newButton, column, row);

            column++;
            if (column == numColumnsAtStart) {
                column = 0;
                row++;
                if (row > numRowsAtStart) {
                    setUpNewRow(row, gridPane);
                }
            }
        }
    }

    /**
     * adds a new menuItem to the cur Order and creates a new MenuItemNode to modify this if not present.
     * @param menuItem the item to add to the order
     */
    private void addToCurrentOrder(MenuItem menuItem) {
        // if the MenuItemNode already exists for a given menuItem then just increase it's count by one
        if (currentOrderHBoxMenuItems.containsKey(menuItem)) {
            currentOrderHBoxMenuItems.get(menuItem).increaseCountByOne();
        } else {
            //make a new MenuItemNode with quantity 1 (default)
            MenuItemNode newMenuItemNode = new MenuItemNode(menuItem, this);
            currentOrderHBoxMenuItems.put(menuItem, newMenuItemNode);
            currentOrderHBox.getChildren().add(newMenuItemNode);
        }
    }

    /**
     * adds a new row at index to the gridPane;
     * @param row index of row to add
     * @param gridPane GridPane to add the row to
     */
    private void setUpNewRow(int row, GridPane gridPane) {
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPrefHeight(this.rowHeight);
        gridPane.getRowConstraints().add(row, rowConstraints);
    }

    /**
     * helper of MenuItemNode method increaseCountByOne
     * @param itemToIncrease MenuItem to add an occurrence of
     */
    public void increaseCurOrderByOne(MenuItem itemToIncrease) {
        this.curOrder.addToOrder(itemToIncrease);
    }

    /**
     * helper of MenuItemNode method decreaseCountByOne
     * @param itemToDecrease MenuItem to remove an occurrence of
     */
    public void decreaseCurOrderByOne(MenuItem itemToDecrease) {
        this.curOrder.removeFromOrder(itemToDecrease);
    }

    /**
     * remove a MenuItemNode from the currentOrderHBox from a given menuItem
     * @param menuItem the menuItem that will remove a given MenuItemNode
     */
    public void removeMenuItemNode(MenuItem menuItem) {
        MenuItemNode menuItemNodeToRemove = this.currentOrderHBoxMenuItems.get(menuItem);
        this.currentOrderHBox.getChildren().remove(menuItemNodeToRemove);
        this.currentOrderHBoxMenuItems.remove(menuItem);
    }

    public void updateLabels() {
        float currentCost = this.curOrder.getTotalCost();
        this.costLabel.setText("$" + currentCost);
        this.numOfItemsValueLabel.setText("" + this.curOrder.getOrderedItems().size());
        this.orderIDValueLabel.setText("" + this.curOrder.getOrderId());
        this.priceValueLabel.setText("$" + currentCost);
        this.nameErrorLabel.setVisible(false);
        this.numItemsErrorLabel.setVisible(false);
    }

    public void confirmCurrentOrder() {
        updateLabels(); // ensures info is up to date for the user
        String curOrderName = this.currentOrderNameTextField.getText();
        boolean successfulOrder = true; // true if the Order is valid;

        // checking the order has items
        if (this.curOrder.getOrderedItems().size() <= 0) {
            successfulOrder = false;
            this.numItemsErrorLabel.setVisible(true);
        }

        // checking the name is valid
        if ((!curOrderName.equals("")) && StringChecking.isAlphaNumeric(curOrderName)) {
            nameErrorLabel.setVisible(false);
        } else {
            successfulOrder = false;
            nameErrorLabel.setVisible(true);
        }

        // must be at end of method
        if (successfulOrder) {
            curOrder.setName(curOrderName);
            this.salesManager.addOrder(curOrder);
            newCurrentOrder();
        }

    }


    public void deleteCurrentOrder() {
        newCurrentOrder();
    }

    /**
     * overwrites the old current order with a new current order and updates visual elements
     */
    private void newCurrentOrder() {

        this.currentOrderHBoxMenuItems.clear();
        this.currentOrderHBox.getChildren().removeAll(this.currentOrderHBox.getChildren());
        this.curOrder = new Order();
        updateLabels();
    }
}