package seng202.team3.model;

import seng202.team3.util.ItemType;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class to keep track of menu items and their ingredients. Only a sample --
 * needs lots more detail -- such as some methods :-)
 */

@XmlRootElement(name = "item")
public class MenuItem {

    /**
     * Short name for menu item
     */
    @XmlElement(name = "id")
    private String id;

    /** Full name for menu item */
    @XmlElement(name = "name")
    private String name;

    /** List of ingredients and their quantities needed to make the menu item */
    @XmlElement(name = "ingredient")
    private HashMap<Ingredient, Float> ingredients;

    /** Type of cuisine */
    @XmlAttribute(name = "type")
    private ItemType type;


    private ArrayList<ItemType> itemTypes;

    private UnitType foodType;

    /**
     * The price the business sells the item for
     */
    private float salePrice;

    /**
     * The number of servings the menu item has
     */
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

    //public void addIngredient(Ingredient ingredient, Float quantity) {
   //     ingredients.add(it);
    //}

    /**
     * Adds the given ingredient to the recipe
     * @param ingredient the ingredient to be added
     * @param quantity the quantity of that ingredient to be added
     */
    public void addIngredientToRecipe(Ingredient ingredient, Float quantity) {
        ingredients.put(ingredient, quantity);
        //IF ingredient is not glueten free
    }

    /**
     * Removes an ingredient from the recipe
     * @param ingredientToRemove The ingredient to be removed from the recipe
     */
    public void removeIngredientFromReipe(Ingredient ingredientToRemove) {
        ingredients.remove(ingredientToRemove);
    }

    /**
     * Calculates the price it takes to create the given recipe
     * @return a float showing the price to create the given recipe
     */
    public float getCostPrice() {
        float totalPrice = 0;

        for (Map.Entry<Ingredient, Float> entry : ingredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            totalPrice += ingredient.getCost() * entry.getValue();
        }

        return totalPrice;
    }


    //TODO Possibly change this into a singular method with parameters
    /**
     * Method to check if an ingredient is gluten free
     * @return Three value logic showing if the menu item is gluten free
     */
    public ThreeValueLogic isGlutenFree(){
        ThreeValueLogic isGlutenFree = ThreeValueLogic.YES;
        for (Map.Entry<Ingredient, Float> entry : ingredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            if(isGlutenFree == ThreeValueLogic.YES && ingredient.getIsGF() == ThreeValueLogic.UNKNOWN){
                isGlutenFree = ThreeValueLogic.UNKNOWN;
            }
            if(ingredient.getIsGF() == ThreeValueLogic.NO){
                isGlutenFree = ThreeValueLogic.NO;
            }

        }
        return isGlutenFree;
    }

    /**
     * Method to check if an ingredient is vegetarian
     * @return Three value logic showing if the menu item is vegetarian
     */
    public ThreeValueLogic isVegetarian(){
        ThreeValueLogic isVegetarian = ThreeValueLogic.YES;
        for (Map.Entry<Ingredient, Float> entry : ingredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            if(isVegetarian == ThreeValueLogic.YES && ingredient.getIsVeg() == ThreeValueLogic.UNKNOWN){
                isVegetarian = ThreeValueLogic.UNKNOWN;
            }
            if(ingredient.getIsVeg() == ThreeValueLogic.NO){
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
        ThreeValueLogic isVegan = ThreeValueLogic.YES;
        for (Map.Entry<Ingredient, Float> entry : ingredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            if(isVegan == ThreeValueLogic.YES && ingredient.getIsVegan() == ThreeValueLogic.UNKNOWN){
                isVegan = ThreeValueLogic.UNKNOWN;
            }
            if(ingredient.getIsVeg() == ThreeValueLogic.NO){
                isVegan = ThreeValueLogic.NO;
            }

        }
        return isVegan;
    }



}


//    public String ingredients() {
//        String recipeText;
//        recipeText = "[" + id + "(" + name + "):";
//        for(String s:ingredients) {
//            recipeText += " " + s;
//        }
//        recipeText += "]";
//        return recipeText;
//    }
