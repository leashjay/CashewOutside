package seng202.team3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seng202.team3.model.Truck;
import seng202.team3.util.InputValidationHelper;
import seng202.team3.view.BusinessApp;

public class CashTabController {

    @FXML
    public TextField addFloatField;

    @FXML
    public TextField withdrawFloatField;

    @FXML
    public TextFlow currentFloatField;

    @FXML
    public Button startButton;

    @FXML
    public Text addText;

    @FXML
    public Text subtractText;

    @FXML
    public Button endButton;

    @FXML
    public Button plusButton;



    @FXML
    public Button subtractButton;

    private Truck truck = BusinessApp.getBusiness().getTruck();


    public void initialize() {
        endButton.setDisable(true);
        plusButton.setDisable(true);
        subtractButton.setDisable(true);
        addFloatField.setDisable(true);
        withdrawFloatField.setDisable(true);
        currentFloatField.setStyle("-fx-border-color: lightgrey;-fx-background-color: White;");
        withdrawFloatField.setStyle("-fx-border-color: lightgrey;-fx-background-color: White;");
        Text text = new Text(Float.toString(truck.getCashAccount()));
        currentFloatField.getChildren().add(text);
    }

    public void startButtonPushed() {
        plusButton.setDisable(false);
        subtractButton.setDisable(false);
        startButton.setDisable(true);
        endButton.setDisable(false);
        addFloatField.setDisable(false);
        withdrawFloatField.setDisable(false);
        currentFloatField.getChildren().clear();
        Text text = new Text(Float.toString(truck.getCashAccount()));
        currentFloatField.getChildren().add(text);

    }

    public void endButtonPushed() {
        addText.setVisible(false);
        subtractText.setVisible(false);
        endButton.setDisable(true);
        plusButton.setDisable(true);
        subtractButton.setDisable(true);
        startButton.setDisable(false);
        addFloatField.setDisable(true);
        withdrawFloatField.setDisable(true);
    }

    public void plusButtonPushed() {
        subtractText.setVisible(false);
        addText.setText("ERROR: Needs to be a float");
        if (checkEmpty(addFloatField, addText) == false) {
            if (InputValidationHelper.isValidFloat(addFloatField, addText) == true) {
                if (checkNegative(addFloatField, addText) == false) {
                    truck.setCashAccount(truck.getCashAccount() + Float.parseFloat(addFloatField.getText()));
                }
            }
        }
        Text text = new Text(Float.toString(truck.getCashAccount()));
        currentFloatField.getChildren().clear();
        addFloatField.clear();
        currentFloatField.getChildren().add(text);
    }

    public void subtractButtonPushed() {
        addText.setVisible(false);
        subtractText.setText("ERROR: Needs to be a float");
        if (checkEmpty(withdrawFloatField, subtractText) == false) {
            if (InputValidationHelper.isValidFloat(withdrawFloatField, subtractText) == true) {
                if (checkNegative(withdrawFloatField, subtractText) == false) {
                    if (checkZero(Float.parseFloat(withdrawFloatField.getText())) == false) {
                        truck.setCashAccount(truck.getCashAccount() - Float.parseFloat(withdrawFloatField.getText()));
                    }
                }
            }
        }
        Text text = new Text(Float.toString((truck.getCashAccount())));
        currentFloatField.getChildren().clear();
        withdrawFloatField.clear();
        currentFloatField.getChildren().add(text);
    }

    public boolean checkZero(float num) {
        if ((truck.getCashAccount() - num) < 0) {
            subtractText.setText("ERROR: Cash Account cannot be negative");
            subtractText.setVisible(true);
            return true;
        }
        subtractText.setVisible(false);
        return false;
    }

    public boolean checkEmpty(TextField textfield, Text text) {
        if (textfield.getText().isEmpty()) {
            text.setText("ERROR: Cannot be null");
            text.setVisible(true);
            return true;
        }
        text.setVisible(false);
        return false;
    }

    public boolean checkNegative(TextField textfield, Text text) {
        if (Float.parseFloat(textfield.getText()) < 0) {
            text.setText("ERROR: cannot be below zero");
            text.setVisible(true);
            return true;
        }
        text.setVisible(false);
        return false;
    }
}
