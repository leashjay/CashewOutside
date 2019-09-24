package seng202.team3.model;

import seng202.team3.util.ItemType;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class to keep track of menu items and their ingredients. Only a sample --
 * needs lots more detail -- such as some methods :-)
 */

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.NONE)
public class MenuItem {

    /**
     * Short name for menu item
     */
    @XmlElement(name = "id")
    private String id;

    /** Full name for menu item */
    @XmlElement(name = "name")
    private String name;

    /**
     * Total cost of menu item without the mark up
     */
    @XmlElement(name = "cost")
    private float totalCost;

    /** List of ingredients and their quantities needed to make the menu item */
    @XmlElement
    private HashMap<Ingredient, Float> ingredients;

    /** Type of cuisine */
    @XmlAttribute(name = "type")
    private ItemType type;


    /**
     * isVegan flag for menu item
     */
    @XmlAttribute(name = "isVegan")
    private ThreeValueLogic isVegan;


    /**
     * isVegetarian flag for menu item
     */
    @XmlAttribute(name = "isVeg")
    private ThreeValueLogic isVegetarian;

    /**
     * isGlutenFree flag for menu item
     */
    @XmlAttribute(name = "isGF")
    private ThreeValueLogic isGlutenFree;

    private ArrayList<ItemType> itemTypes;

    private UnitType foodType;

    /** The price the business sells the item for */
    private float salePrice;

    /** The number of servings the menu item has */
    @XmlAttribute(name = "serves")
    private int numServings;


    /**
     * No arg constructor for JAXB
     */
    public MenuItem() {
    }

    /**
     * Constructor for MenuItem class
     * @param id A short description of the menu item
     * @param name a long description of the menu item
     * @param ingredients A hashset of the ingredients in the menu item and their quantities
     * @param type Shows what type the menu item is e.g, beverage, snack, main...
     */
    public MenuItem(String id, String name, HashMap<Ingredient, Float> ingredients, ItemType type) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.type = type;
    }

    /**
     * Getter for menu item id
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for menu item's full name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for list of ingredients
     * @return ingredients
     */
    public HashMap<Ingredient, Float> getIngredients() {
        return ingredients;
    }


    /**
     * Getter for type of cuisine
     *
     * @return type
     */
    public ItemType getType() {
        return type;
    }

    /**
     * Returns cost which the business sells the item for
     * @return a float showing the cost the business sells the item for
     */
    public float getSalePrice(){return totalCost;}

   public int getServings(){return numServings;}

    /**
     * Adds the given ingredient to the recipe
     * @param ingredient the ingredient to be added
     * @param quantity the quantity of that ingredient to be added
     */
    public void addIngredientToRecipe(Ingredient ingredient, Float quantity) {
        ingredients.put(ingredient, quantity);
    }

    /**
     * Removes an ingredient from the recipe
     * @param ingredientToRemove The ingredient to be removed from the recipe
     */
    public void removeIngredientFromRecipe(Ingredient ingredientToRemove) {
        ingredients.remove(ingredientToRemove);
    }

    /**
     * Calculates the price it takes to create the given recipe
     * @return a float showing the price to create the given recipe
     */
    public float getCostPrice() {
        totalCost = 0;

        for (Map.Entry<Ingredient, Float> entry : ingredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            totalCost += ingredient.getCost() * entry.getValue();
        }

        return totalCost;
    }


    //TODO Possibly change this into a singular method with parameters
    /**
     * Method to check if a menu item is gluten free
     * @return Three value logic showing if the menu item is gluten free
     */
    public ThreeValueLogic isGlutenFree(){
        isGlutenFree = ThreeValueLogic.YES;
        for (Map.Entry<Ingredient, Float> entry : ingredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            if(isGlutenFree == ThreeValueLogic.YES && ingredient.getIsGlutenFree() == ThreeValueLogic.UNKNOWN){
                isGlutenFree = ThreeValueLogic.UNKNOWN;
            }
            if(ingredient.getIsGlutenFree() == ThreeValueLogic.NO){
                isGlutenFree = ThreeValueLogic.NO;
            }

        }
        return isGlutenFree;
    }

    /**
     * Method to check if a menu item is vegetarian
     * @return Three value logic showing if the menu item is vegetarian
     */
    public ThreeValueLogic isVegetarian(){
        isVegetarian = ThreeValueLogic.YES;
        for (Map.Entry<Ingredient, Float> entry : ingredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            if(isVegetarian == ThreeValueLogic.YES && ingredient.getIsVegetarian() == ThreeValueLogic.UNKNOWN){
                isVegetarian = ThreeValueLogic.UNKNOWN;
            }
            if(ingredient.getIsVegetarian() == ThreeValueLogic.NO){
                isVegetarian = ThreeValueLogic.NO;
            }

        }
        return isVegetarian;
    }

    /**
     * Three value logic showing if the menu item is Vegan
     * @return
     */
    public ThreeValueLogic isVegan(){
        isVegan = ThreeValueLogic.YES;
        for (Map.Entry<Ingredient, Float> entry : ingredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            if(isVegan == ThreeValueLogic.YES && ingredient.getIsVegan() == ThreeValueLogic.UNKNOWN){
                isVegan = ThreeValueLogic.UNKNOWN;
            }
            if(ingredient.getIsVegetarian() == ThreeValueLogic.NO){
                isVegan = ThreeValueLogic.NO;
            }

        }
        return isVegan;
    }



}
