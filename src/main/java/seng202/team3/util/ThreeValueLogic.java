package seng202.team3.util;

/**
 * Simple enum to illustrate three value logic for attributes
 */
public enum ThreeValueLogic {
    YES("Yes"),
    NO("No"),
    UNKNOWN("Unknown");

    private final String name;

    ThreeValueLogic(String s) {
        name = s;
    }
}