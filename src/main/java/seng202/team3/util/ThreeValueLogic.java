package seng202.team3.util;

/**
 * Simple enum to illustrate three value logic for attributes
 */
public enum ThreeValueLogic {
    YES("Yes"),
    NO("No"),
    UNKNOWN("Unknown");

    /**
     * Name of ThreeValueLogic
     **/
    private final String name;

    /**
     * Return ThreeValueLogic string
     * @param s ThreeValueLogic String
     */
    ThreeValueLogic(String s) {
        name = s;
    }
}