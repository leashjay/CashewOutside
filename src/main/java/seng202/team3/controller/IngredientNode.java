package seng202.team3.controller;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seng202.team3.model.Ingredient;
import seng202.team3.model.MenuItem;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;

import java.text.DecimalFormat;

public class IngredientNode extends VBox {
    final int height = 90;
    private Ingredient ingredient;
    private float quantity;
    private ManuallyAddMenuItemController parent;

    public Ingredient getIngredient() {
        return ingredient;
    }

    public float getQuantity() {
        return quantity;
    }


    public IngredientNode(Ingredient ingredient, ManuallyAddMenuItemController parent) {
        super();
        this.parent = parent;
        this.quantity = this.parent.getQuantityText();
        this.ingredient = ingredient;
        this.setPrefHeight(height);
        this.setPrefWidth(100);
        makeLabels(ingredient.getCode(), this.quantity);
        setUpDelButton();
    }

    public IngredientNode(Ingredient ingredient, float quantity, ManuallyAddMenuItemController parent) {
        super();
        this.parent = parent;
        this.quantity = quantity;
        this.ingredient = ingredient;
        this.setPrefHeight(height);
        this.setPrefWidth(100);
        makeLabels(ingredient.getCode(), quantity);
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

        DecimalFormat decimalFormat = new DecimalFormat("#");
        String unitText = "";
        UnitType unit = ingredient.getUnit();
        switch (unit) {
            case GRAM:
                unitText = "g";
                break;
            case ML:
                unitText = "mL";
                break;
            case COUNT:
                unitText = " units";
                break;
            case UNKNOWN:
                unitText = "";
                break;
        }
        String quantityString = decimalFormat.format(quantity);
        Label quantityLabel = new Label();
        quantityLabel.setText(quantityString + unitText);
        quantityLabel.setStyle("-fx-text-alignment: center; -fx-wrap-text: true;");
        quantityLabel.setAlignment(Pos.TOP_CENTER);
        quantityLabel.setPrefWidth(100);
        quantityLabel.setPrefHeight(height * 0.3);
        this.getChildren().add(1,quantityLabel);
    }

    private void setUpDelButton() {
        Button delButton = new Button("Delete");
        delButton.setOnAction(e -> this.parent.removeIngredient(this));
        delButton.setAlignment(Pos.BOTTOM_CENTER);
        delButton.setPrefWidth(100);
        delButton.setPrefHeight(height * 0.33);
        this.getChildren().add(2, delButton);
    }

}
