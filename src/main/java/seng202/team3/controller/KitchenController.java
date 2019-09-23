package seng202.team3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
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
import java.time.format.DateTimeFormatter;
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
            for (MenuItem item: order.getOrderedItems()) {
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
     * Removes a TextFlow containing an order from the orderGridPane. Called when the remove button is pressed
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
     * Adds a TextFlow (containing a menu item and ingredients) to the menu item grid pane
     */
    private void addMenuToGridPane() {
        menuItemGridPane.getChildren().clear();
        final int numColumnsAtStart = menuItemGridPane.getColumnCount();

        int row = 0;
        int column = 0;

        for (MenuItem item : menuItems) {
            TextFlow text = new TextFlow();
            text.setStyle("-fx-border-color: FloralWhite;-fx-background-color: SteelBlue;");
            text.setPrefHeight(200);
            text.setPrefWidth(150);
            ArrayList<Ingredient> ingredients = new ArrayList<>();

            Text text1 = new Text("    " + item.getName());
            text1.setStyle("-fx-font: 14 arial;-fx-font-weight: bold;");
            text1.setFill(Color.FLORALWHITE);
            text.getChildren().add(text1);

            Text text2 = new Text("\n\n          ITEM ");
            text2.setStyle("-fx-font: 14 arial;-fx-font-weight: bold;");
            text2.setFill(Color.FLORALWHITE);
            text.getChildren().add(text2);

            HashMap<Ingredient, Float> map = item.getIngredients();
            for (Map.Entry<Ingredient, Float> entry : map.entrySet()) {
                ingredients.add(entry.getKey());
                Text text3 = new Text();
                if (entry.getKey().getUnit() == UnitType.GRAM) {
                    text3 = new Text("\n\n  x" + entry.getValue() + "g   " + entry.getKey().getName());
                } else if (entry.getKey().getUnit() == UnitType.ML) {
                    text3 = new Text("\n\n  x" + entry.getValue() + "mL   " + entry.getKey().getName());
                } else {
                    text3 = new Text("\n\n  x" + entry.getValue() + "   " + entry.getKey().getName());
                }
                text3.setFill(Color.FLORALWHITE);
                text.getChildren().add(text3);
            }
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
     * Adds a TextFlow containing the current orders to the orders grid pane
     */
    private void addOrderToGridPane() {
        orderGridPane.getChildren().clear();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

        final int numColumnsAtStart = orderGridPane.getColumnCount();

        int row = 0;
        int column = 0;

        for (Order order : orders) {

            TextFlow text = new TextFlow();
            text.setStyle("-fx-border-color: FloralWhite;-fx-background-color: SteelBlue;");
            text.setPrefHeight(200);
            text.setPrefWidth(150);

            Text text1 = new Text("        Order: " + order.getOrderId());
            text1.setStyle("-fx-font: 14 arial;-fx-font-weight: bold;");
            text1.setFill(Color.FLORALWHITE);
            text.getChildren().add(text1);

            Text text4 = new Text("\n               Order Taken " + order.getTime().format(dtf));
            text4.setStyle("-fx-font: 10 arial;");
            text4.setFill(Color.FLORALWHITE);
            text.getChildren().add(text4);

            Text text2 = new Text("\n\n          ITEM ");
            text2.setStyle("-fx-font: 14 arial;-fx-font-weight: bold;");
            text2.setFill(Color.FLORALWHITE);
            text.getChildren().add(text2);


            HashMap<MenuItem, Float> menuItems = new HashMap<>();
            for (MenuItem testIngredient: order.getOrderedItems()) {
                if (menuItems.containsKey(testIngredient)) {
                    menuItems.put(testIngredient, menuItems.get(testIngredient) + 1);
                } else {
                    menuItems.put(testIngredient, (float) 1);
                }
            }

            for (Map.Entry<MenuItem, Float> entry : menuItems.entrySet()) {
                Text text3 = new Text("\n\n  x" + entry.getValue() + "   " + entry.getKey().getName());
                text3.setFill(Color.FLORALWHITE);
                text.getChildren().add(text3);
            }

            orderGridPane.add(text, column, row);
            if (column > numColumnsAtStart) {
                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setPrefWidth(150);
                orderGridPane.getColumnConstraints().add(columnConstraints);
            }
            column++;
        }

    }
}