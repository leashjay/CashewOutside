package seng202.team3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seng202.team3.model.Ingredient;
import seng202.team3.model.MenuItem;
import seng202.team3.model.Order;
import seng202.team3.util.ItemType;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;
import seng202.team3.view.BusinessApp;

import java.io.IOException;
import java.util.*;

public class KitchenController {

    /**
     * A button to return to the main screen
     */
    @FXML
    private Button backButton;

    /**
     * A button that removes the the given order
     */
    @FXML
    private Button removeButton;

    /**
     * A combo box that selects the order to be removed
     */
    @FXML
    private ComboBox removeOrderCombo;

    /**
     * A grid pane to hold the text areas that contain the orders
     */
    @FXML
    private GridPane orderGridPane;

    /**
     * A grid pane to hold the text areas that contain the menu items and ingredients
     */
    @FXML
    private GridPane menuItemGridPane;

    /**
     * An ArrayList to store the menu items used in the current orders
     */
    public ArrayList<MenuItem> menuItems = new ArrayList<>();

    /**
     * An ArrayList to store the current orders
     */
    public ArrayList<Order> orders = new ArrayList<>();

    /**
     * sends the user back to the main screen.;
     * Some kind of clean up / persistence should be implemented here.
     * @throws IOException idk?
     */
    public void backButtonPressed() throws IOException {
        BusinessApp.loadMainPage();
    }

    /**
     * Populates the menuItems ArrayList with the menu items used in the current orders
     */
    public void createMenuItemsArray() {
        menuItems = new ArrayList<>();
        for (Order order: orders) {
            for (MenuItem item: order.getItemsOrdered()) {
                if (menuItems.contains(item) == false) {
                    menuItems.add(item);
                }
            }
        }
    }

    /**
     * Puts the order numbers into the combo box
     */
    public void createOrderComboBox() {
        for (Order order: orders) {
            removeOrderCombo.getItems().add(order.getOrderId());
        }
    }

    /**
     * used to setup the buttons and etc in the scene.
     * *IMPORTANT*
     * This method is called automatically by the FXMLLoader
     */
    public void initialize() {
        orders.addAll(getOrders());
        menuItems = new ArrayList<>();
        addOrderToGridPane();
        createMenuItemsArray();
        addMenuToGridPane();
        createOrderComboBox();
    }

    //Just for testing
    private ArrayList<Order> getOrders() {
        Ingredient rice = new Ingredient("1", "Rice", UnitType.GRAM, ThreeValueLogic.YES, ThreeValueLogic.YES,
                ThreeValueLogic.YES, 0.001f);
        Ingredient carrot = new Ingredient("2", "Carrot", UnitType.COUNT, ThreeValueLogic.YES, ThreeValueLogic.YES,
                ThreeValueLogic.YES, 0.01f);
        Ingredient peas = new Ingredient("3", "Peas", UnitType.GRAM, ThreeValueLogic.YES, ThreeValueLogic.YES,
                ThreeValueLogic.YES, 0.01f);
        Ingredient egg = new Ingredient("4", "Rice", UnitType.GRAM, ThreeValueLogic.NO, ThreeValueLogic.YES,
                ThreeValueLogic.YES, 1f);

        HashMap<Ingredient, Float>friedRiceIngredients = new HashMap<>();
        friedRiceIngredients.put(rice, 200f);
        friedRiceIngredients.put(carrot, 50f);
        friedRiceIngredients.put(peas, 50f);
        MenuItem friedRice = new MenuItem("1", "Fried rice", friedRiceIngredients, ItemType.MAIN);
        MenuItem testFood = new MenuItem("2", "Test Food", friedRiceIngredients, ItemType.MAIN);
        ArrayList<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.addToOrder(friedRice);
        order1.setOrderId(2);
        Order order2 = new Order();
        order2.addToOrder(friedRice);
        order2.addToOrder(friedRice);
        order2.setOrderId(3);
        orders.add(order1);
        Order order3 = new Order();
        order3.addToOrder(friedRice);
        order3.addToOrder(friedRice);
        order3.setOrderId(4);
        orders.add(order3);
        Order order4 = new Order();
        order4.addToOrder(friedRice);
        order4.addToOrder(friedRice);
        order4.setOrderId(5);
        orders.add(order4);
        Order order5 = new Order();
        order5.addToOrder(friedRice);
        order5.addToOrder(friedRice);
        order5.setOrderId(7);
        orders.add(order5);
        Order order7 = new Order();
        order7.addToOrder(friedRice);
        order7.addToOrder(testFood);
        order7.setOrderId(6);
        orders.add(order7);
        orders.add(order2);
        return orders;
    }

    /**
     * Removes a text area containing an order from the orderGridPane. Called when the remove button is pressed
     */
    public void popFromOrderGrid() {
        Object checkForNull = removeOrderCombo.getValue();
        if (checkForNull != null) {
            int orderNum = (Integer) removeOrderCombo.getSelectionModel().getSelectedItem();
            List<Order> removed = new ArrayList<>();
            for (Order ordered : orders) {
                if (ordered.getOrderId() == orderNum) {
                    removed.add(ordered);
                    int index = orders.indexOf(ordered);
                    removeOrderCombo.getItems().remove(index);
                }
            }
            orders.removeAll(removed);
            addOrderToGridPane();
            createMenuItemsArray();
            addMenuToGridPane();
        }
    }

    /**
     * Adds a text area (containing a menu item and ingredients) to the menu item grid pane
     */
    private void addMenuToGridPane() {
        menuItemGridPane.getChildren().clear();
        final int numColumnsAtStart = menuItemGridPane.getColumnCount();

        int row = 0;
        int column = 0;

        for (MenuItem item : menuItems) {
            ArrayList<Ingredient> ingredients = new ArrayList<>();

            String strings = "    " + item.getName() + "\n\n    Ingredients:\n";
            HashMap<Ingredient, Float> map = item.getIngredients();
            for (Map.Entry<Ingredient, Float> entry : map.entrySet()) {
                ingredients.add(entry.getKey());
                strings += "\n\n    " + entry.getKey().getName() + "\n    Quantity: " + entry.getValue();
            }
            TextFlow text = new TextFlow();
            text.setPrefHeight(200);
            text.setPrefWidth(150);
            text.getChildren().add(new Text(strings));
            menuItemGridPane.add(text, column, row);
            if (column > numColumnsAtStart) {
                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setPrefWidth(150);
                menuItemGridPane.getColumnConstraints().add(columnConstraints);
            }
            column++;
        }
    }

    /**
     * Adds a text area containing the current orders to the orders grid pane
     */
    private void addOrderToGridPane() {
        orderGridPane.getChildren().clear();

        final int numColumnsAtStart = orderGridPane.getColumnCount();

        int row = 0;
        int column = 0;

        for (Order order : orders) {
            TextFlow text = new TextFlow();
            text.setPrefHeight(200);
            text.setPrefWidth(120);

            Text string = new Text("  Order Num: " + order.getOrderId());
            string.setStyle("-fx-font-weight: bold");
            text.getChildren().add(string);

            Text string2 = new Text( "\n    MenuItems:");
            text.getChildren().add(string2);

            for (MenuItem name: order.getItemsOrdered()) {
                Text string3 = new Text("\n\n    " + name.getName());
                text.getChildren().add(string3);
            }

            orderGridPane.add(text, column, row);
            if (column > numColumnsAtStart) {
                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setPrefWidth(120);
                orderGridPane.getColumnConstraints().add(columnConstraints);
            }
            column++;
        }
    }
}