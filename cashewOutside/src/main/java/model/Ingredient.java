package model;

import util.ThreeValueLogic;
import util.UnitType;

/**
 * Holds information about the ingredients
 */
public class Ingredient {

    private String name;
    private int quantity;
    private boolean isGlutenFree;
    private boolean isVegan;
    private boolean isVegetarian;

    /**
     * A constructor for the Ingredient class
     *
     * @param name          The name of the ingredient
     * @param quantity      The quantity of the ingredient
     * @param isGlutenFree If the ingredient is gluten free or not
     * @param isVegan       If the ingredient is vegan or not
     * @param isVegetarian  If the ingredient is vegetarian or not
     */
    public Ingredient(String name, int quantity, boolean isGlutenFree, boolean isVegan, boolean isVegetarian) {
        this.name = name;
        this.quantity = quantity;
        this.isGlutenFree = isGlutenFree;
        this.isVegan = isVegan;
        this.isVegetarian = isVegetarian;
    }

    /**
     * created from Ingredients loader
     * @param code
     * @param name
     * @param unit
     * @param isVeg
     * @param isVegan
     * @param isGF
     */
    public Ingredient(String code, String name, UnitType unit, ThreeValueLogic isVeg, ThreeValueLogic isVegan, ThreeValueLogic isGF) {
    }

    /**
     * Changes the quantity of the ingredient
     *
     * @param amount The amount of the ingredient that is being added/subtracted
     */
    public void changeQuantity(int amount) {
        quantity -= amount;
    }

}

