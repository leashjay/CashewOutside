package seng202.team3.model;

import seng202.team3.util.ItemType;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.view.BusinessApp;

import javax.xml.bind.annotation.*;
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
    private HashMap<Ingredient, Float> ingredients = new HashMap<>();

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

    /** The number of servings the menu item has */
    @XmlAttribute(name = "serves")
    private int numServings;

    /**
     * Markup to calculate sales price
     */
    private float markup = (float) 1.1;

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
        this.numServings = 1;
        this.markup = (float) 1.1;
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
    public float getSalePrice() {
        totalCost = getCostPrice();
        return totalCost * markup;
    }


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
     * Check if ingredient is in truck inventory (loaded from Business class)
     *
     * @param ingredient ingredient to be checked
     * @return true if ingredient is in truck inventory, false otherwise
     */
    public boolean isIngredientInStock(Ingredient ingredient) {
        Inventory truckInventory = BusinessApp.getBusiness().getTruck().getInventory();
        if (truckInventory.getIngredients().get(ingredient.getCode()) == null) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * Calculates the price it takes to create the given recipe
     * @return a float showing the price to create the given recipe
     */
    public float getCostPrice() {
        totalCost = 0;

        for (Map.Entry<Ingredient, Float> entry : ingredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            Float cost = 0f;
            if (isIngredientInStock(ingredient)) {
                cost = BusinessApp.getBusiness().getTruck().getInventory().getIngredients().get(ingredient.getCode()).getCost();
            } else {
                cost = ingredient.getCost();
            }
            totalCost += (cost * entry.getValue());
        }
        return totalCost;
    }

    public void setSalePrice(float newPrice) {
        this.totalCost = newPrice;
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
            if (ingredient.getIsGlutenFree() == ThreeValueLogic.YES) {
                isGlutenFree = ThreeValueLogic.YES;
            } else if (ingredient.getIsGlutenFree() == ThreeValueLogic.NO) {
                isGlutenFree = ThreeValueLogic.NO;
                break;
            } else if (ingredient.getIsGlutenFree() == ThreeValueLogic.UNKNOWN) {
                isGlutenFree = ThreeValueLogic.UNKNOWN;
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
            if (ingredient.getIsVegetarian() == ThreeValueLogic.YES) {
                isVegetarian = ThreeValueLogic.YES;
            } else if (ingredient.getIsVegetarian() == ThreeValueLogic.NO) {
                isVegetarian = ThreeValueLogic.NO;
                break;
            } else if (ingredient.getIsVegan() == ThreeValueLogic.UNKNOWN) {
                isVegetarian = ThreeValueLogic.UNKNOWN;
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
            if (ingredient.getIsVegan() == ThreeValueLogic.YES) {
                isVegan = ThreeValueLogic.YES;
            } else if (ingredient.getIsVegan() == ThreeValueLogic.NO) {
                isVegan = ThreeValueLogic.NO;
                break;
            } else if (ingredient.getIsVegan() == ThreeValueLogic.UNKNOWN) {
                isVegan = ThreeValueLogic.UNKNOWN;
            }
        }
        return isVegan;
    }

    /**
     * Get HashMap of ingredient and their corresponding quantity in inventory
     *
     * @param inventory truck Inventory
     * @return
     */
    public HashMap<String, Float> getIngredientWithQuantityInStock(Inventory inventory) {
        HashMap<String, Float> quantities = new HashMap<String, Float>();
        for (Map.Entry<Ingredient, Float> entry : ingredients.entrySet()) {
            if (inventory.getIngredients().containsKey(entry.getKey().getCode())) {
                quantities.put(entry.getKey().getCode(), inventory.getIngredients().get(entry.getKey().getCode()).getQuantity());
            } else {
                quantities.put(entry.getKey().getCode(), 0f);
            }
        }
        return quantities;
    }

    /**
     * Calculate serving size of menu item with the stock that we have right now.
     *
     * @param inventory
     */
    public void calculateServing(Inventory inventory) {
        numServings = 0;
        Float quantityInStock = 100f;
        Boolean firstPass = false;

        HashMap<String, Float> quantities = getIngredientWithQuantityInStock(inventory);

        while (!quantities.values().contains(0f)) {
            for (Map.Entry<Ingredient, Float> entry : ingredients.entrySet()) {
                String entryCode = entry.getKey().getCode();
                if (quantities.get(entryCode) == 0f) {
                    numServings = 0;
                    break;
                } else {
                    quantities.put(entryCode, quantities.get(entryCode) - entry.getValue());
                }
            }
            numServings += 1;
        }
    }


    /**
     * checks if the trucks inventory of ingredients have enough quantity (stock)
     * @return has enough stock
     */
    public boolean hasEnoughStock() {
        Inventory truckInventory = BusinessApp.getBusiness().getTruck().getInventory();
        for (Map.Entry<Ingredient, Float> ingredientFloatEntry : this.ingredients.entrySet()) {
            Ingredient ingredient = ingredientFloatEntry.getKey();
            Ingredient truckIngredient = truckInventory.getIngredients().get(ingredient.getCode());
            Float amountRequired = ingredientFloatEntry.getValue();
            if (truckIngredient == null) {
                return false;
            } else {
                if (truckIngredient.getQuantity() < amountRequired) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * decreases the trucks stock according to this MenuItem
     */
    public void decreaseStock() {
        Inventory truckInventory = BusinessApp.getBusiness().getTruck().getInventory();
        for (Map.Entry<Ingredient, Float> ingredientFloatEntry : this.ingredients.entrySet()) {
            Ingredient ingredient = ingredientFloatEntry.getKey();
            Ingredient truckIngredient = truckInventory.getIngredients().get(ingredient.getCode());
            Float amountRequired = ingredientFloatEntry.getValue();
            truckIngredient.decreaseQuantity(amountRequired);
        }
    }
}
