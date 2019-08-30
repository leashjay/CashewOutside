package seng202.team3.model;


import seng202.team3.parsing.IngredientAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashMap;

/**
 * Holds information about the inventory
 */
@XmlRootElement(name = "inventory")
@XmlAccessorType(XmlAccessType.FIELD)
public class Inventory {

    /**
     * List of ingredients in the inventory
     */
    @XmlElement(name = "ingredients")
    @XmlJavaTypeAdapter(IngredientAdapter.class)
    private HashMap<String, Ingredient> ingredients;

    /** Inventory description */
    @XmlElement(name = "description")
    private String desc;


    /**
     * Temporary constructor
     */
    public Inventory() {
        ;
    }

    /**
     * Constructor for Inventory class
     * @param desc
     * @param ingredients
     */
    public Inventory(String desc, HashMap<String, Ingredient> ingredients) {
        this.desc = desc;
        this.ingredients = ingredients;
    }

    /**
     * Getter for inventory description
     *
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Getter for list of ingredients
     *
     * @return ingredients
     */
    public HashMap<String, Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Removes an ingredient form the ingredients ArrayList
     * @param removedIngredient The ingredient that's to be removed from the list
     */
    public void removeIngredient(Ingredient removedIngredient) {
        ingredients.remove(removedIngredient);
    }

    /**
     * Adds an ingredient to the ingredients ArrayList
     * @param addedIngredient The ingredient that's to be added to the list
     */
//    public void addIngredient(Ingredient addedIngredient) {
//        ingredients.a(addedIngredient);
//    }

}

