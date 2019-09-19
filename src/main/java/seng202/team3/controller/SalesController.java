package seng202.team3.controller;

import com.sun.javafx.fxml.builder.JavaFXSceneBuilder;
import javafx.collections.ObservableList;
import javafx.css.CssParser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import seng202.team3.model.Business;
import seng202.team3.model.MenuItem;
import seng202.team3.model.Order;
import seng202.team3.model.SalesHandler;
import seng202.team3.view.BusinessApp;

import javax.lang.model.type.NullType;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SalesController {

    @FXML
    private Button backButton;

    @FXML
    private HBox currentOrderHBox;

    @FXML
    private GridPane foodItemGrid;

    @FXML
    private GridPane drinkItemGrid;

    private final Insets gridPadding = new Insets(50, 50, 50, 50);
    private SalesHandler salesManager;
    private Order curOrder;
    private Business business;
    private final float rowHeight = 150;


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

        HashMap<String, MenuItem> foodMenuItems = business.getMenuManager().getMenuItem();

        ArrayList<MenuItem> drinkMenuItems = new ArrayList<>(); //getDrinkItems();
        setUpGridPane(foodItemGrid);
        setUpGridPane(drinkItemGrid);
        addMenuItemButtonsToGridPane(foodMenuItems, foodItemGrid);
//        addMenuItemButtonsToGridPane(drinkMenuItems, drinkItemGrid);
    }

    /**
     * sets the gridPane to fill the width of the window.
     * Removed - Deprecated
     * @param gridPane
     */
    private void setUpGridPane(GridPane gridPane) {

        gridPane.setPrefWidth(950);
        ObservableList<ColumnConstraints> columnConstraintsList = gridPane.getColumnConstraints();
        float percWidth = 100 / columnConstraintsList.size();

        for (ColumnConstraints columnConstraints : columnConstraintsList) {
            columnConstraints.setPercentWidth(percWidth);
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

        int row = numChildrenAtStart / numColumnsAtStart; // Java Integer Division, so floors the result
        int column = numChildrenAtStart % numColumnsAtStart;

        for (MenuItem menuItem : menuItems.values()) {
            Button newButton = new Button();
            // TODO Find out how to set this button's style to combinedStyle.css #foodButton
            // TODO Have the buttons display their flags (gf, veg, vegan)
            // TODO Format GridPane properly.

            newButton.setStyle("-fx-background-radius: 10;-fx-border-color: #273746;-fx-border-radius: 10;" +
                    "-fx-pref-width: 100;-fx-pref-height: 100;-fx-background-color: #00bcd4;-fx-wrap-text: true;");

            newButton.setPadding(gridPadding); // This line maybe irrelevant, unsure right now.
            GridPane.setConstraints(newButton, column, row, 1, 1, HPos.CENTER, VPos.CENTER);
            newButton.setText(menuItem.getName());
            newButton.setOnAction(e -> {
                addToCurrentOrder(menuItem);
            }); // lambda function
            gridPane.add(newButton, column, row);

            column++;
            if (column == numColumnsAtStart) {
                column = 0;
                row++;
                setUpNewRow(row, gridPane);
            }
        }
    }

    private void addToCurrentOrder(MenuItem menuItem) {
        curOrder.addToOrder(menuItem);
        currentOrderHBox.getChildren().add(new MenuItemNode(menuItem));
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

}