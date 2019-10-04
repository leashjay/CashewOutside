package seng202.team3.util;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Helper class created to help with input validating forms the user inputs data into
 */
public class InputValidationHelper {

    public static final String URL_REGEX = "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)([).!';/?:,][[:blank:]])?$";
    public static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    public static final String PHONE_NUMBER_REGEX = "\\d{10}|\\d{9}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";

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


}
