package seng202.team3.controller;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seng202.team3.model.Ingredient;
import seng202.team3.model.MenuItem;
import seng202.team3.util.ThreeValueLogic;

public class IngredientNode extends Vbox {
    final int height = 90;
    private Ingredient menuItem;
    private int quantity;
    private Label quantityLabel;
    private ManuallyAddMenuItemController parent;
}
