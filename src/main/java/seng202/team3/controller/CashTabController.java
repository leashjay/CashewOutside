package seng202.team3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seng202.team3.model.Truck;
import seng202.team3.view.BusinessApp;

public class CashTabController {

    @FXML
    public TextFlow endFloatField;

    @FXML
    public TextField addFloatField;

    @FXML
    public TextFlow currentFloatField;

    @FXML
    public Button startButton;

    @FXML
    public Button endButton;

    @FXML
    public Button plusButton;

    @FXML
    public Button subtractButton;

    public float startFloat;

    private Truck truck = BusinessApp.getBusiness().getTruck();


    public void initialize() {
        endButton.setDisable(true);
        plusButton.setDisable(true);
        subtractButton.setDisable(true);
        currentFloatField.setStyle("-fx-border-color: lightgrey;-fx-background-color: White;");
        endFloatField.setStyle("-fx-border-color: lightgrey;-fx-background-color: White;");
        Text text = new Text(Float.toString(truck.currentFloat));
        currentFloatField.getChildren().add(text);
    }

    public void startButtonPushed() {
        plusButton.setDisable(false);
        subtractButton.setDisable(false);
        startButton.setDisable(true);
        endButton.setDisable(false);
        truck.currentFloat += startFloat;
        currentFloatField.getChildren().clear();
        Text text = new Text(Float.toString(truck.currentFloat));
        currentFloatField.getChildren().add(text);
    }

    public void endButtonPushed() {
        endButton.setDisable(true);
        Text text = new Text(Float.toString(truck.currentFloat));
        endFloatField.getChildren().clear();
        endFloatField.getChildren().add(text);
        plusButton.setDisable(true);
        subtractButton.setDisable(true);
        startButton.setDisable(false);

    }

    public void plusButtonPushed() {
        truck.currentFloat += Float.parseFloat(addFloatField.getText());
        Text text = new Text(Float.toString(truck.currentFloat));
        currentFloatField.getChildren().clear();
        addFloatField.clear();
        currentFloatField.getChildren().add(text);
    }

    public void subtractButtonPushed() {
        truck.currentFloat -= Float.parseFloat(addFloatField.getText());
        Text text = new Text(Float.toString((truck.currentFloat)));
        currentFloatField.getChildren().clear();
        addFloatField.clear();
        currentFloatField.getChildren().add(text);

    }
}
