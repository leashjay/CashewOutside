package seng202.team3.controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * most of this class comes from the YouTuber 'thenewboston'
 */
public class CustomerChangeAlert {

    /**
     * create a new alert that displays the amount of change and will wait until closed.
     * @param change the amount of change the employee should give the customer
     */
    public static void display(Float change) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Change");
        window.setMinWidth(200);

        Label label = new Label();
        label.setText(String.format("Change to give: $%.2f", change));
        Button closeButton = new Button("Ok");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}
