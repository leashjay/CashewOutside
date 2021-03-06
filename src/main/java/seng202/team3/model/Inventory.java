package seng202.team3.model;


import seng202.team3.parsing.IngredientAdapter;
import seng202.team3.parsing.InventoryLoader;
import seng202.team3.util.UnitType;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Holds information about the inventory
 */
@XmlRootElement(name = "inventory")
@XmlAccessorType(XmlAccessType.NONE)
public class Inventory {

    /**
     * Inventory description
     */
    @XmlElement
    private String description;

    /** List of ingredients in the inventory */
    @XmlElement(name = "ingredients")
    @XmlJavaTypeAdapter(IngredientAdapter.class)
    private HashMap<String, Ingredient> ingredients;

    /** A number that indicates that an ingredient with the unit type COUNT has low stock */
    public static Float LOWSTOCKCOUNT = 10f;

    /** A number that indicates that an ingredient with the unit type GRAMS has low stock */
    public static Float LOWSTOCKGRAMS = 1000f;

    /** A number that indicates that an ingredient with the unit type ML has low stock */
    public static Float LOWSTOCKML = 1000f;

    public static Float LOWSTOCKUNKNOWN = 1000f;

    /**
     * Inventory Loader object to call import and export method
     */
    private InventoryLoader inventoryLoader;

    /**
     * No-arg constructor for JAXB
     */
    public Inventory() { }

    /**
     * Constructor for Inventory class
     *
     * @param desc a description of the inventory
     * @param ingredients a HashMap where the ingredients, along with their code are stored
     */
    public Inventory(String desc, HashMap<String, Ingredient> ingredients) {
        this.description = desc;
        this.ingredients = ingredients;
    }


    /**
     * Getter for list of ingredients
     * @return ingredients
     */
    public HashMap<String, Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Removes an ingredient from the ingredients HashMap
     * @param removedIngredient The ingredient that's to be removed from the list
     */
    public void removeIngredient(String removedIngredient) {
        ingredients.remove(removedIngredient);
    }

    /**
     * Adds an ingredient to the ingredients HashMap
     * @param addedIngredient The ingredient that's to be added to the list
     */
    public void addIngredient(Ingredient addedIngredient) {
        ingredients.put(addedIngredient.getCode(), addedIngredient);
    }

    /**
     * Adds Ingredients from an XML file to the ingredients HashMap
     * @param file The path to the XML file that is being used
     */
    public void addIngredientsFromXML(String file) throws JAXBException {
        inventoryLoader = new InventoryLoader();
        Inventory inventory = inventoryLoader.loadIngredientsData(file);
        if (inventory != null) {
            HashMap<String, Ingredient> newIngredients = inventory.getIngredients();
            compareAndAddIngredient(newIngredients);
        }
    }

    /**
     * Helper method for addIngredientsFromXML to update existing ingredient's quantity
     * or add new entry of ingredient to HashMap of ingredients.
     *
     * @param newIngredients
     */
    public void compareAndAddIngredient(HashMap<String, Ingredient> newIngredients) {
        for (Ingredient newIng : newIngredients.values()) {
            if (ingredients.get(newIng.getCode()) != null) {
                Ingredient existingIng = ingredients.get(newIng.getCode());
                if (existingIng.getQuantity() != 0) {
                    newIng.setQuantity(existingIng.getQuantity() + newIng.getQuantity());
                }
            }
            ingredients.put(newIng.getCode(), newIng);
        }
    }

    /**
     * Replaces an ingredient object in the HashMap with a new modified version
     *
     * @param modifiedIngredient The newly modified ingredient to be added to the HashMap
     */
    // Identifies ingredient to be replaced by getCode(), meaning you can't modify the code. better way of doing this?
    public void modifyIngredient(Ingredient modifiedIngredient) {
        ingredients.replace(modifiedIngredient.getCode(), modifiedIngredient);
    }

    /**
     * Increases the quantity of an Ingredient object in the ingredient HashMap
     *
     * @param ingredient The ingredient whose stock is being increased
     * @param quantityGained The amount of stock being added
     */
    public void addStock(Ingredient ingredient, Float quantityGained) {
        ingredient.setQuantity(ingredient.getQuantity() + quantityGained);
    }

    /**
     * Decreases the quantity of an Ingredient object in the ingredient HashMap
     *
     * @param ingredient ingredient whose stock is being decreased
     * @param quantityLost The amount of stock that's been taken away
     */
    public void removeStock(Ingredient ingredient, Float quantityLost) {
        ingredient.setQuantity(ingredient.getQuantity() - quantityLost);
        if (ingredient.getQuantity() < 0) {
            ingredient.setQuantity(0);
        }
    }

    /**
     * Check the stock level for the ingredients
     *
     * @return a HashMap containing the ingredient and the quantity
     */
    public HashMap<Ingredient, Float> checkStock() {
        HashMap<Ingredient, Float> map = new HashMap<>();
        for (Map.Entry<String, Ingredient> entry : ingredients.entrySet()) {
            map.put(entry.getValue(), entry.getValue().getQuantity());
        }
        return map;
    }

    /**
     * Checks if an ingredient has low stock
     *
     * @param ingredient the ingredient which stock is getting checked
     * @return a boolean indicating whether the ingredient has low stock
     */
    private Boolean checkLowStock(Ingredient ingredient) {
        if (ingredient.getUnit() == UnitType.COUNT && ingredient.getQuantity() <= LOWSTOCKCOUNT) {
            return true;
        } else if (ingredient.getUnit() == UnitType.ML && ingredient.getQuantity() <= LOWSTOCKML) {
            return true;
        } else if (ingredient.getUnit() == UnitType.GRAM && ingredient.getQuantity() <= LOWSTOCKGRAMS) {
            return true;
        }
        return false;
    }

    /**
     * Checks the stock level of the ingredients to to find whether they have low stock
     *
     * @return a HashMap containing the ingredient and the quantity
     */
    public HashMap<Ingredient, Float>lowStockReport() {
        HashMap<Ingredient, Float> lowStockMap = new HashMap<>();
        for (Map.Entry<String, Ingredient> entry : ingredients.entrySet()) {
            if (checkLowStock(entry.getValue())) {
                lowStockMap.put(entry.getValue(), entry.getValue().getQuantity());
            }
        }
        return lowStockMap;
    }

    /**
     * Checks if an ingredient is in the ingredients HashMap.
     *
     * @param searchedIngredient the ingredient that is being searched for in the HashMap
     * @return boolean indicating whether the ingredient is in the HashMap
     */
    public Boolean searchStock(Ingredient searchedIngredient) {
        for (Map.Entry<String, Ingredient> entry : ingredients.entrySet()) {
            if (entry.getValue() == searchedIngredient) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method called to update stock levels when business has received an order
     * Adds ingredient to hashmap if not already there and updates quantity.
     * @param order the order to be received
     */
    public void receiveOrder(SupplierOrder order){
        HashMap<Ingredient, Float >itemsToAdd = order.getOrderItems();
        for(Map.Entry<Ingredient, Float> entry : itemsToAdd.entrySet()){
            Ingredient ingredient = entry.getKey();
            if(searchStock(ingredient)) {
                addIngredient(ingredient);
            }
            addStock(ingredient, entry.getValue());
        }
        order.setBeenReceived(true);
        order.setReceivedDate(new Date());
    }


}
