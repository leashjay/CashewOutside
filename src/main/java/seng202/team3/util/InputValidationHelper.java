package seng202.team3.util;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Helper class created to help with input validating forms the user inputs data into
 */
public class InputValidationHelper {

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
     * @return true if the text is a valid email.
     */
    public static boolean isValidEmail(TextField textField, Text text){
        boolean isValid = true;
        String input = textField.getText();
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
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


}
