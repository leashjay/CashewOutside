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
    public TextFlow dailyTake;



    @FXML
    public Button subtractButton;

    private Truck truck = BusinessApp.getBusiness().getTruck();

    /**
     * Initialises all buttons etc. in the cashTab. It is called automatically y the fxml loader
     */
    public void initialize() {
        startButton.setDisable(truck.getStartDay());
        endButton.setDisable(!truck.getStartDay());
        plusButton.setDisable(!truck.getStartDay());
        subtractButton.setDisable(!truck.getStartDay());
        addFloatField.setDisable(!truck.getStartDay());
        withdrawFloatField.setDisable(!truck.getStartDay());
        currentFloatField.setStyle("-fx-border-color: lightgrey;-fx-background-color: White;");
        withdrawFloatField.setStyle("-fx-border-color: lightgrey;-fx-background-color: White;");
        Text text = new Text(Float.toString(truck.getCashAccount()));
        currentFloatField.getChildren().add(text);
        truck.setStartDay(truck.getStartDay());
    }

    /**
     * Activates when the startButton is pressed, and disables/enables all the relevant fields/buttons
     */
    public void startButtonPushed() {
        truck.setStartFloat(truck.getCashAccount());
        plusButton.setDisable(truck.getStartDay());
        subtractButton.setDisable(truck.getStartDay());
        startButton.setDisable(!truck.getStartDay());
        endButton.setDisable(truck.getStartDay());
        addFloatField.setDisable(truck.getStartDay());
        withdrawFloatField.setDisable(truck.getStartDay());
        currentFloatField.getChildren().clear();
        Text text = new Text(Float.toString(truck.getCashAccount()));
        currentFloatField.getChildren().add(text);
        dailyTake.getChildren().clear();
        truck.setStartDay(!truck.getStartDay());
    }

    /**
     * Activates when the endButton is pressed, and disables/enables all the relevant fields/buttons
     */
    public void endButtonPushed() {
        truck.setEndFloat(truck.getCashAccount());
        addText.setVisible(false);
        subtractText.setVisible(false);
        endButton.setDisable(truck.getStartDay());
        plusButton.setDisable(truck.getStartDay());
        subtractButton.setDisable(truck.getStartDay());
        startButton.setDisable(!truck.getStartDay());
        addFloatField.setDisable(truck.getStartDay());
        withdrawFloatField.setDisable(truck.getStartDay());
        Text text = new Text(Float.toString(truck.getEndFloat() - truck.getStartFloat()));
        dailyTake.getChildren().clear();
        dailyTake.getChildren().add(text);
        truck.setStartDay(!truck.getStartDay());
    }

    /**
     * Adds the float in the addFloatField to the cashAccount when the addButton is pushed
     */
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

    /**
     * Subtracts the float in the withdrawFloatField from the cashAccount when the subtractButton is pushed
     */
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

    /**
     * Checks the number passed into the withdrawFloatField to see if it causes the cashAccount to fall below 0
     *
     * @param num The float that is being subtracted
     * @return a boolean indicating if the cashAccount will fall below 0
     */
    public boolean checkZero(float num) {
        if ((truck.getCashAccount() - num) < 0) {
            subtractText.setText("ERROR: Cash Account cannot be negative");
            subtractText.setVisible(true);
            return true;
        }
        subtractText.setVisible(false);
        return false;
    }

    /**
     * Checks the given text field to see if its null
     *
     * @param textfield The textfield that is being checked
     * @param text The text where the error message that will be displayed
     * @return a boolean indicating whether the text field is null
     */
    public boolean checkEmpty(TextField textfield, Text text) {
        if (textfield.getText().isEmpty()) {
            text.setText("ERROR: Cannot be null");
            text.setVisible(true);
            return true;
        }
        text.setVisible(false);
        return false;
    }

    /**
     * checks whether the number in the given text field is negative
     *
     * @param textfield The textfield that is being checked
     * @param text The text where the error message that will be displayed
     * @return A boolean indicating whether the the number in the text field is negative
     */
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
