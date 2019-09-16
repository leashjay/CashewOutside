package seng202.team3.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import seng202.team3.model.MenuItem;
import seng202.team3.model.Order;
import seng202.team3.model.SalesHandler;
import seng202.team3.view.BusinessApp;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SalesController {

    @FXML
    private Button backButton;

    @FXML
    private GridPane foodItemGrid;

    @FXML
    private GridPane drinkItemGrid;

    private SalesHandler salesHandler = new SalesHandler();
    private Order curOrder = new Order();


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
        ArrayList<MenuItem> foodMenuItems = new ArrayList<>(); //getFoodItems();
        // Test Code
//        for (int i = 0; i < 100; i++) {
//            foodMenuItems.add(new MenuItem());
//        }
        // End Test Code
        ArrayList<MenuItem> drinkMenuItems = new ArrayList<>(); //getDrinkItems();
        addMenuItemButtonsToGridPane(foodMenuItems, foodItemGrid);
        addMenuItemButtonsToGridPane(drinkMenuItems, drinkItemGrid);
    }

    /**
     * will not overwrite any current buttons.
     * @param menuItems the MenuItems the buttons will add to the Order
     * @param gridPane the GridPane to add the buttons to
     */

    private void addMenuItemButtonsToGridPane(ArrayList<MenuItem> menuItems, GridPane gridPane) {

        final int numChildrenAtStart = gridPane.getChildren().size();
        final int numColumnsAtStart = gridPane.getColumnCount();

        int row = numChildrenAtStart / numColumnsAtStart; // Java Integer Division, so floors the result
        int column = numChildrenAtStart % numColumnsAtStart;

        for (MenuItem menuItem : menuItems) {
            Button newButton = new Button();
            // TODO Find out how to set this button's style to foodBtnStyle.css
            // TODO Have the buttons display their flags (gf, veg, vegan)
            // TODO Format GridPane properly.
            newButton.setStyle("");
            newButton.setText(menuItem.getName());
            newButton.setOnAction(e -> curOrder.addToOrder(menuItem)); // lambda function
            gridPane.add(newButton, column, row);

            column++;
            if (column == numColumnsAtStart) {
                column = 0;
                row++;
            }
        }
    }

}