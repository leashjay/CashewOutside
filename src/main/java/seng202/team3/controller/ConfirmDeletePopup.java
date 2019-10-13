package seng202.team3.controller;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class ConfirmDeletePopup {

    public static boolean delete = false;
    public static Text text;

    /**
     * Creates a window that asks the user to confirm whether they want to delete a certain object
     *
     * @param tab the tab in which an object is being deleted
     */
    public static void display(Character tab, String message) {

        delete = false;

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Confirm Delete");
        window.setMinWidth(170);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.prefHeightProperty().bind(window.heightProperty());
        scrollPane.prefWidthProperty().bind(window.widthProperty());
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setMinViewportHeight(100);
        scrollPane.setMinViewportWidth(170);

        VBox cashFloatVBox = new VBox(10);
        cashFloatVBox.prefHeightProperty();
        cashFloatVBox.prefWidthProperty();
        cashFloatVBox.setFillWidth(true);
        scrollPane.setContent(cashFloatVBox);

        TextFlow textflow = new TextFlow();
        text = new Text("\n\n    " + message);
        text.setStyle("-fx-font: 14 arial;-fx-font-weight: bold;");
        textflow.getChildren().add(text);
        textflow.setTextAlignment(TextAlignment.CENTER);

        cashFloatVBox.getChildren().add(textflow);
        cashFloatVBox.setAlignment(Pos.CENTER);

        Button closeButton = new Button("Cancel");
        closeButton.setStyle("-fx-background-radius: 10;-fx-border-color: #273746;-fx-border-radius: 10;" +
                "-fx-pref-width: 90;-fx-pref-height: 30;-fx-background-color: #1976D2;-fx-wrap-text: true;-fx-text-fill: #ffffff;");
        closeButton.setOnAction(e -> window.close());

        Button removeButton = new Button("Delete");
        removeButton.setStyle("-fx-background-radius: 10;-fx-border-color: #273746;-fx-border-radius: 10;" +
                "-fx-pref-width: 90;-fx-pref-height: 30;-fx-background-color: RED;-fx-wrap-text: true;-fx-text-fill: #ffffff;");
        removeButton.setOnAction(e -> {chooseTab(tab);window.close();});

        GridPane grid = new GridPane();
        VBox layout = new VBox(10);
        layout.getChildren().setAll(scrollPane, grid);
        setColumnConstraints(0, grid);
        setColumnConstraints(1, grid);
        grid.add(closeButton, 0, 0);
        grid.add(removeButton, 1, 0);
        layout.setAlignment(Pos.CENTER);
        grid.setHalignment(closeButton, HPos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * Sets up a column in the given GridPane
     *
     * @param column An integer representing the column that is to be set up
     * @param gridPane the GridPane that is being modified
     */
    public static void setColumnConstraints(int column, GridPane gridPane) {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setMinWidth(170);
        gridPane.getColumnConstraints().add(column, columnConstraints);
    }

    /**
     * A method to see what tab the item is being deleted from
     *
     * @param tab A character that indicates what tab the item is being deleted from
     */
    public static void chooseTab(Character tab) {
        if (tab == 'S') {
            SupplierTabController.delete = true;
        } else if (tab == 'M') {
            MenuItemTabController.delete = true;
        } else if (tab == 'I') {
            IngredientTabController.delete = true;
        }
    }

    public static void changeText(String message) {
        text = new Text("\n\n    " + message);
    }
}