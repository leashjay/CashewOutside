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
}
