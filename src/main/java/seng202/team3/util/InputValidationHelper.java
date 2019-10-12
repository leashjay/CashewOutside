package seng202.team3.util;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import seng202.team3.view.BusinessApp;

/**
 * Helper class created to help with input validating forms the user inputs data into
 */
public class InputValidationHelper {

    public static final String URL_REGEX = "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)([).!';/?:,][[:blank:]])?$";
    public static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    public static final String PHONE_NUMBER_REGEX = "\\d{10}|\\d{9}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
    public static final String camelCasePattern = "([A-Z]+[a-z]+\\w+)+";
    public static final String supplierIdRegex = "([s][0-9]+)";

    /**
     * Method that true empty if a textfield contains no text
     * @param textField the textfield to be checked
     * @param text the error text to be displayed if the textfield is empty
     * @return whether or not the textfield is empty
     */
    public static boolean checkEmpty(TextField textField, Text text){
        boolean hasError = false;
        if(textField.getText().isEmpty()){
            text.setVisible(true);
            hasError = true;
        } else {
            text.setVisible(false);
        }
        return hasError;
    }

    /**
     * Method that returns true if an email address entered into a textfield is valid
     * @param textField the textfield that is being checked
     * @param text the error text displayed if string is not an email
     * @param regex the regular expression the input is being matched with
     * @return true if the text is a valid email.
     */
    public static boolean isValidWithRegex(TextField textField, Text text, String regex){
        boolean isValid = true;
        String input = textField.getText();
        if(input.matches(regex)){
            text.setVisible(false);
        } else {
            text.setVisible(true);
            isValid = false;
        }
        return isValid;
    }


    /**
     * Checks whether a textfield contains a valid float
     * @param textField the textfield that is being checked
     * @param errorText the error text to be displayed if the textfield does not contain a valid float
     * @return a boolean showing whether or not the textfield has a valid float.
     */
    public static boolean isValidFloat(TextField textField, Text errorText){
        float temp = 0 ;
        boolean isFloat = true;
        try {
            temp = Float.parseFloat(textField.getText());
            errorText.setVisible(false);
        } catch (NumberFormatException ex) {
            isFloat = false;
            errorText.setVisible(true);
        }

        return isFloat;
    }


    /**
     * Checks whether an ingredient id entered is not equal to primary key of ingredient database
     * and if the id follows camel case pattern
     *
     * @param textField ingredient id text field
     * @param errorText ingredient error text to be displayed if the id is invalid
     * @return boolean showing whether or not the id is valid
     */
    public static boolean checkIngredientValidId(TextField textField, Text errorText) {
        String inputId = textField.getText();
        //ID not valid
        if (!inputId.matches(camelCasePattern)) {
            errorText.setText("Field must contain data entered in a camel case pattern!");
            errorText.setVisible(true);
            return false;

        } else if (BusinessApp.getBusiness().getTruck().getInventory().getIngredients().keySet().contains(inputId)) {
            errorText.setText("Id entered is already registered in database.\n Try another id");
            errorText.setVisible(true);
            return false;
        } else {
            errorText.setVisible(false);
            return true;
        }
    }

    /**
     * Check whether a supplier id entered is not equal to primary key of supplier database
     * and if the id follows supplieridregex pattern
     * @param textField supplier id text field
     * @param errorText supplier error text to be displayed if the id is invalid
     * @return boolean showing whether or not the id is valid
     */
    public static boolean checkSupplierValidId(TextField textField, Text errorText) {
        String inputId = textField.getText();
        //ID not valid
        if (!inputId.matches(supplierIdRegex)) {
            errorText.setText("Field must contain data entered in \n a \"s\" followed by digits pattern!");
            errorText.setVisible(true);
            return false;
        } else if (BusinessApp.getBusiness().getSupplierHandler().getSuppliers().containsKey(inputId)) {
            errorText.setText("Id entered is already registered in database.\n Try another id");
            errorText.setVisible(true);
            return false;
        } else {
            errorText.setVisible(false);
            return true;
        }
    }


    public static boolean checkMenuItemValidId(TextField textField, Text errorText) {
        String inputId = textField.getText();

        if (BusinessApp.getBusiness().getMenuManager().filterMenuItems().containsKey(inputId)) {
            errorText.setText("Id entered is already registered in database. \n Try another id");
            errorText.setVisible(true);
            return false;
        } else {
            errorText.setVisible(false);
            return true;
        }

    }


}
