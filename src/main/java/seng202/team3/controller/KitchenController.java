package seng202.team3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
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

    @FXML
    private Button backButton;

    @FXML
    private Button testButton;

    @FXML
    private ComboBox removeOrderCombo;

    @FXML
    private GridPane orderGridPane;

    @FXML
    private GridPane menuItemGridPane;

    public ArrayList<MenuItem> items = new ArrayList<>();

    public ArrayList<Order> testArray = new ArrayList<>();

    /**
     * sends the user back to the main screen.;
     * Some kind of clean up / persistence should be implemented here.
     * @throws IOException idk?
     */
    public void backButtonPressed() throws IOException {
        System.out.println("Back button pressed");
        BusinessApp.loadMainPage();
    }

    public void createMenuItemsArray() {
        items = new ArrayList<>();
        for (Order order: testArray) {
            for (MenuItem item: order.getOrderedItems()) {
                if (items.contains(item) == false) {
                    items.add(item);
                }
            }
        }
    }


    public void initialize() {
        testArray.addAll(getOrders());
        items = new ArrayList<>();
        addOrderToGridPane(testArray, orderGridPane);
        createMenuItemsArray();
        addMenuToGridPane(items, menuItemGridPane);
        for (Order order: testArray) {
            removeOrderCombo.getItems().add(order.getOrderId());
        }
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

    public void popFromOrderGrid() {
        Object checkForNull = removeOrderCombo.getValue();
        if (checkForNull != null) {
            int orderNum = (Integer) removeOrderCombo.getSelectionModel().getSelectedItem();
            List<Order> removed = new ArrayList<>();
            for (Order ordered : testArray) {
                if (ordered.getOrderId() == orderNum) {
                    removed.add(ordered);
                    int index = testArray.indexOf(ordered);
                    removeOrderCombo.getItems().remove(index);
                }
            }
            testArray.removeAll(removed);
            addOrderToGridPane(testArray, orderGridPane);
            createMenuItemsArray();
            addMenuToGridPane(items, menuItemGridPane);
        }
    }

    private void addMenuToGridPane(ArrayList<MenuItem> menuItems, GridPane gridPane) {
        menuItemGridPane.getChildren().clear();
        final int numColumnsAtStart = gridPane.getColumnCount();

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
            TextArea text = new TextArea();
            text.setEditable(false);
            text.setPrefHeight(200);
            text.setPrefWidth(150);
            text.setText(strings);
            gridPane.add(text, column, row);
            if (column > numColumnsAtStart) {
                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setPrefWidth(150);
                gridPane.getColumnConstraints().add(columnConstraints);
            }
            column++;
        }
    }

    private void addOrderToGridPane(ArrayList<Order> menuItems, GridPane gridPane) {
        orderGridPane.getChildren().clear();

        final int numColumnsAtStart = gridPane.getColumnCount();

        int row = 0;
        int column = 0;

        for (Order order : menuItems) {
            String strings = "  Order Num: " + order.getOrderId() + "\n    MenuItems:";
            for (MenuItem name: order.getOrderedItems()) {
                strings += "\n\n    " + name.getName();
            }
            TextArea text = new TextArea();
            text.setEditable(false);
            text.setPrefHeight(200);
            text.setPrefWidth(120);
            text.setText(strings);
            gridPane.add(text, column, row);
            if (column > numColumnsAtStart) {
                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setPrefWidth(120);
                gridPane.getColumnConstraints().add(columnConstraints);
            }
            column++;
        }
    }
}