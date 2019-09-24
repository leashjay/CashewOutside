package seng202.team3.controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seng202.team3.model.MenuItem;
import seng202.team3.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewOrderPopUp {

    public static void display(Order order) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Order: " + order.getOrderId());
        window.setMinWidth(200);

        Label itemsOrderedLabel = new Label("Items Ordered:");
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMinViewportHeight(200);
        scrollPane.setMinViewportWidth(200);

        VBox menuItemsVBox = new VBox(10);
        menuItemsVBox.setFillWidth(true);
        scrollPane.setContent(menuItemsVBox);

        populateVBoxMenuItems(menuItemsVBox, order.getOrderedItems());
        menuItemsVBox.setAlignment(Pos.CENTER);

        Button closeButton = new Button("Ok");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().setAll(itemsOrderedLabel, scrollPane, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * populates a given VBox with MenuItem Labels
     * @param vBox to populate
     * @param menuItems for population
     */
    private static void populateVBoxMenuItems(VBox vBox, ArrayList<MenuItem> menuItems) {
        HashMap<MenuItem, Integer> menuItemsWithQuantities = getHashMapOfMenuItems(menuItems);
        for (Map.Entry<MenuItem, Integer> entry : menuItemsWithQuantities.entrySet()) {
            MenuItem menuItemToUse = entry.getKey();
            Integer quantity = entry.getValue();
            Label newMenuItemLabel = new Label();
            newMenuItemLabel.setText(menuItemToUse.getName() + ": " + quantity + ", $" + quantity * menuItemToUse.getSalePrice());
            vBox.getChildren().add(newMenuItemLabel);
        }
    }

    /**
     * returns a HashMap with the MenuItems as keys and the values are the quantity
     * @param menuItems the ArrayList to iterate through
     * @return the HashMap
     */
    private static HashMap<MenuItem, Integer> getHashMapOfMenuItems(ArrayList<MenuItem> menuItems) {
        HashMap<MenuItem, Integer> hashMapOfMenuItems = new HashMap<>();
        for (MenuItem menuItem : menuItems) {
            if (!hashMapOfMenuItems.containsKey(menuItem)) {
                hashMapOfMenuItems.put(menuItem, 1);
            } else {
                hashMapOfMenuItems.put(menuItem, hashMapOfMenuItems.get(menuItem) + 1);
            }
        }
        return hashMapOfMenuItems;
    }
}
