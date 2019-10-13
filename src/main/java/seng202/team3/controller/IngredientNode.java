package seng202.team3.controller;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seng202.team3.model.Ingredient;
import seng202.team3.model.MenuItem;
import seng202.team3.util.ThreeValueLogic;

import java.text.DecimalFormat;

public class IngredientNode extends VBox {
    final int height = 90;
    private Ingredient ingredient;
    private float quantity;
    private Label quantityLabel;
    private ManuallyAddMenuItemController parent;

    public IngredientNode(Ingredient ingredient, ManuallyAddMenuItemController parent) {
        super();
        this.parent = parent;
        this.quantity = this.parent.getQuantityText();
        this.ingredient = ingredient;
        this.setPrefHeight(height);
        this.setPrefWidth(100);
        makeLabels(ingredient.getName(), this.quantity);
        setUpDelButton();
    }

    private void makeLabels(String name, float quantity) {
        Label itemNameLabel = new Label();
        itemNameLabel.setText(name);
        itemNameLabel.setStyle("-fx-text-alignment: center; -fx-wrap-text: true;");
        itemNameLabel.setAlignment(Pos.TOP_CENTER);
        itemNameLabel.setPrefWidth(100);
        itemNameLabel.setPrefHeight(height * 0.3);
        this.getChildren().add(0,itemNameLabel);

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String quantityString = decimalFormat.format(quantity);
        Label quantityLabel = new Label();
        quantityLabel.setText(quantityString);
        quantityLabel.setStyle("-fx-text-alignment: center; -fx-wrap-text: true;");
        quantityLabel.setAlignment(Pos.TOP_CENTER);
        quantityLabel.setPrefWidth(100);
        quantityLabel.setPrefHeight(height * 0.3);
        this.getChildren().add(1,quantityLabel);
    }

    private void setUpDelButton() {
        Button delButton = new Button("Delete");
        delButton.setOnAction(e -> rmvIngredient());
        quantityLabel.setAlignment(Pos.BOTTOM_CENTER);
        quantityLabel.setPrefWidth(100);
        delButton.setPrefHeight(height * 0.33);
        this.getChildren().add(2, delButton);
    }

    private void rmvIngredient() {
        parent.removeIngredient();
        this.getChildren().remove(parent.getScrollHBox());
    }
}
