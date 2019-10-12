package seng202.team3.controller;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seng202.team3.model.MenuItem;
import seng202.team3.util.ThreeValueLogic;


/**
 * Creates a GridPane and populates it with cool formatted stuff based on a given MenuItem.
 */
public class MenuItemNode extends VBox {

    final int height = 175;
    private MenuItem menuItem;
    private int quantity;
    private Label quantityLabel;
    private Label costValue;
    private SalesController parent;

    public MenuItemNode(MenuItem menuItem, SalesController parent) {
        super();
        this.parent = parent;
        this.quantity = 1;
        this.menuItem = menuItem;
        this.parent.increaseCurOrderByOne(this.menuItem); // defaulting the quantity to one
        this.setPrefHeight(height);
        this.setPrefWidth(100);
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
        this.costValue.setText(String.format("$%.2f", (menuItem.calculateSalePrice() * quantity)));
        this.parent.updateLabels();
    }

    private void setUpButtons() {
        HBox buttonHBox = new HBox();
        Button decrButton = new Button("-");
        decrButton.setOnAction(e -> decreaseCountByOne());
        quantityLabel = new Label();
        Button incrButton = new Button("+");
        incrButton.setOnAction(e -> increaseCountByOne());
        buttonHBox.setPrefHeight(height * 0.3);
        buttonHBox.getChildren().addAll(decrButton, quantityLabel, incrButton);
        this.getChildren().add(3, buttonHBox);
    }

    private void setupCost() {
        HBox costHBox = new HBox();
        Label costLabel = new Label("Cost:");
        costValue = new Label();
//        costLabel.setPrefHeight(40);
        costHBox.setPrefHeight(height * 0.2);
        costHBox.getChildren().addAll(costLabel, costValue);
        this.getChildren().add(2, costHBox);
    }

    /**
     * Helper to set up the text area for flags, will only show if they are ThreeValueLogic.YES
     */
    private void makeFlags() {
        Label flagLabel = new Label();
        flagLabel.setPrefHeight(height * 0.3);
        flagLabel.setPrefWidth(100);
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
        itemNameLabel.setStyle("-fx-text-alignment: center; -fx-wrap-text: true;");
        itemNameLabel.setAlignment(Pos.TOP_CENTER);
        itemNameLabel.setPrefWidth(200);
        itemNameLabel.setPrefHeight(height * 0.2);
        this.getChildren().add(0,itemNameLabel);
    }

    /**
     * Adds one more occurrence of the MenuItemNode's MenuItem to the curOrder's items
     */
    public void increaseCountByOne() {
        this.quantity++;
        this.parent.increaseCurOrderByOne(this.menuItem);
        updateLabels();
    }

    /**
     *  Removes one occurrence of the MenuItemNode's MenuItem from the curOrder's items
     */
    public void decreaseCountByOne() {
        this.quantity--;
        this.parent.decreaseCurOrderByOne(this.menuItem);
        if (this.quantity <= 0) {
            this.parent.removeMenuItemNode(this.menuItem);
        }
        updateLabels();
    }

}
