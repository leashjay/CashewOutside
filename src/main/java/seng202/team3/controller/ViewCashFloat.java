package seng202.team3.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import seng202.team3.model.Ingredient;
import seng202.team3.model.Truck;
import seng202.team3.view.BusinessApp;

import java.util.HashMap;
import java.util.Map;

public class ViewCashFloat {

    public static void display() {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Cash Float");
        window.setMinWidth(200);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMinViewportHeight(200);
        scrollPane.setMinViewportWidth(200);

        VBox menuItemsVBox = new VBox(10);
        menuItemsVBox.setFillWidth(true);
        scrollPane.setContent(menuItemsVBox);

        populateVBoxMenuItems(menuItemsVBox);
        menuItemsVBox.setAlignment(Pos.CENTER);

        Button closeButton = new Button("Ok");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().setAll(scrollPane, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * populates a given VBox with MenuItem Labels
     * @param vBox to populate
     */
    private static void populateVBoxMenuItems(VBox vBox) {
        Truck truck = BusinessApp.getBusiness().getTruck();
        HashMap<Integer, Integer> cash = truck.getCashFloat();
        vBox.getChildren().add(makeTableView(cash));
    }

    private static TableView<Map.Entry<Double, Integer>> makeTableView(HashMap<Integer, Integer> cash) {

        HashMap<Double, Integer> newCash = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : cash.entrySet()) {
            newCash.put(entry.getKey()*0.01, entry.getValue());
        }

        TableColumn<Map.Entry<Double, Integer>, Double> column1 = new TableColumn<>("Value (NZD)");
        column1.setCellValueFactory(value -> new SimpleDoubleProperty(value.getValue().getKey()).asObject());

        TableColumn<Map.Entry<Double, Integer>, Integer> column2 = new TableColumn<>("Amount");
        column2.setCellValueFactory(value -> new SimpleIntegerProperty(value.getValue().getValue()).asObject());

        ObservableList<Map.Entry<Double, Integer>> items = FXCollections.observableArrayList(newCash.entrySet());
        final TableView<Map.Entry<Double,Integer>> table = new TableView<>(items);

        table.getColumns().setAll(column1, column2);
        table.setColumnResizePolicy(table.CONSTRAINED_RESIZE_POLICY);
        table.setFixedCellSize(25);
        table.prefHeightProperty().bind(Bindings.size(table.getItems()).multiply(table.getFixedCellSize()).add(30));
        return table;
    }
}