package model;

import java.util.ArrayList;

/**
 * Holds information about the inventory
 */
public class Inventory {
    public ArrayList<Ingredient> ingredients;

    /**
     * A constructor for Inventory class
     */
    public Inventory() {
        super();
    }

    /**
     * Removes an ingredient form the ingredients ArrayList
     *
     * @param removedIngredient The ingredient that's to be removed from the list
     */
    public void removeIngredient(Ingredient removedIngredient) {
        ingredients.remove(removedIngredient);
    }

    /**
     * Adds an ingredient to the ingredients ArrayList
     *
     * @param addedIngredient The ingredient that's to be added to the list
     */
    public void addIngredient(Ingredient addedIngredient) {
        ingredients.add(addedIngredient);
    }

}

