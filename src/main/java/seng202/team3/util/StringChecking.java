package seng202.team3.util;

/**
 * utility class for checking strings against set regular expressions.
 */
public final class StringChecking {

    /**
     * method to return whether a string is alpha-numeric
     * @param stringToCheck the string to check
     * @return true if the string is alpha-numeric
     */
    public static boolean isAlphaNumeric(String stringToCheck) {
        return !stringToCheck.matches("^.*[^a-zA-Z0-9 ].*$");
    }

    /**
     * method to check if the string is a valid positive float
     */
    public static boolean isFloat(String stringToCheck) {
        return stringToCheck.matches("[+]?([0-9]*[.])?[0-9]+");
    }

    /**
     * method to check if the string is a valid positive float up to two decimal places
     */
    public static boolean isTwoDPFloat(String stringToCheck) {
        return stringToCheck.matches("[+]?([0-9]*[.])?[0-9]?[0-9]?") && !stringToCheck.equals(".");
    }

    /**
     * method to check if an string could be a valid email
     * regular expression taken from https://www.regular-expressions.info/email.html in accordance with RFC1035
     * *IMPORTANT*
     * This method has not been tested (ripped straight from the internet),
     * tests should be written in jUnitTests/StringCheckingTests
     */
    public static boolean isEmail(String stringToCheck) {
        String emailRegex = "\\A(?=[a-z0-9@.!#$%&'*+/=?^_`{|}~-]{6,254}\\z)" +
                "(?=[a-z0-9.!#$%&'*+/=?^_`{|}~-]{1,64}@)" +
                "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*" +
                "@(?:(?=[a-z0-9-]{1,63}\\.)[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" +
                "(?=[a-z0-9-]{1,63}\\z)[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\z";
        return stringToCheck.matches(emailRegex);
    }
}
