package seng202.team3.controller;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seng202.team3.model.MenuItem;
import seng202.team3.util.ThreeValueLogic;


/**
 * Creates a GridPane and populates it with cool formatted stuff based on a given MenuItem.
 */
public class MenuItemNode extends VBox {
    private MenuItem menuItem;
    private int quantity;
    private Label quantityLabel;
    private Label costValue;

    public MenuItemNode(MenuItem menuItem) {
        super();
        this.quantity = 1;
        this.menuItem = menuItem;
        this.setPrefHeight(200);
        this.setPrefWidth(200);
        makeNameArea(menuItem.getName());
        makeFlags();
        setupCost();
        setUpButtons();
        updateLabels();


    }
    /**
     * Updates the quantity Label and the costValue Label
     */
    private void updateLabels() {
        this.quantityLabel.setText(String.valueOf(quantity));
        this.costValue.setText("$" + (menuItem.getCostPrice() * quantity));
    }

    private void setUpButtons() {
        HBox buttonHBox = new HBox();
        Button decrButton = new Button("-");
        quantityLabel = new Label();
        Button incrButton = new Button("+");
        buttonHBox.setPrefHeight(60);
        buttonHBox.getChildren().addAll(decrButton, quantityLabel, incrButton);
        this.getChildren().add(3, buttonHBox);
    }

    private void setupCost() {
        HBox costHBox = new HBox();
        Label costLabel = new Label("Cost:");
        costValue = new Label();
        costLabel.setPrefHeight(40);
        costHBox.setPrefHeight(40);
        costHBox.getChildren().addAll(costLabel, costValue);
        this.getChildren().add(2, costHBox);
    }

    /**
     * Helper to set up the text area for flags, will only show if they are ThreeValueLogic.YES
     */
    private void makeFlags() {
        Label flagLabel = new Label();
        flagLabel.setPrefHeight(60);
        flagLabel.setPrefWidth(200);
        String flagText = "";
        if (menuItem.isGlutenFree() == ThreeValueLogic.YES) {
            flagText += "GF\n";
        }
        if (menuItem.isVegetarian() == ThreeValueLogic.YES) {
            flagText += "Vege\n";
        }
        if (menuItem.isVegan() == ThreeValueLogic.YES) {
            flagText += "Vegan\n";
        }
        System.out.println("Flags: " + flagText);
        flagLabel.setText(flagText);
        this.getChildren().add(1, flagLabel);
    }

    /**
     * Helper to set up the Name of the GUI
     * @param name the name to set
     */
    private void makeNameArea(String name) {
        Label itemNameLabel = new Label();
        itemNameLabel.setText(name);
        itemNameLabel.setStyle("-fx-text-alignment: center");
        itemNameLabel.setPrefWidth(200);
        itemNameLabel.setPrefHeight(40);
        this.getChildren().add(0,itemNameLabel);
    }

    public void increaseCountByOne() {

    }
}
