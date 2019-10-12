package seng202.team3.controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seng202.team3.model.Ingredient;
import seng202.team3.model.MenuItem;
import java.util.HashMap;
import java.util.Map;

public class ViewIngredientsController {

    public static void display(MenuItem item) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Menu Item: " + item.getName());
        window.setMinWidth(200);

        Label itemsOrderedLabel = new Label("Ingredients:");
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMinViewportHeight(200);
        scrollPane.setMinViewportWidth(200);

        VBox menuItemsVBox = new VBox(10);
        menuItemsVBox.setFillWidth(true);
        scrollPane.setContent(menuItemsVBox);
        menuItemsVBox.setMinWidth(200);


        populateVBoxIngredients(menuItemsVBox, item.getIngredients());
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

    public static void populateVBoxIngredients(VBox vbox, HashMap<Ingredient, Float> ingredient) {
        for (Map.Entry<Ingredient, Float> entry : ingredient.entrySet()) {
            Label label = new Label();
            label.setText(entry.getKey().getCode());
            vbox.getChildren().add(label);
        }
    }
}
