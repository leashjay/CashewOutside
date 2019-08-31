package seng202.team3.model;

import seng202.team3.util.ItemType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

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

    /** List of ingredients needed to make the menu item */
    @XmlElement(name = "ingredient")
    private List<Ingredient> ingredients;

    /** Type of cuisine */
    @XmlAttribute(name = "type")
    private ItemType type;

    /**
     * Temporary constructor
     */
    public MenuItem() {
    }

    /**
     * Constructor for MenuItem class
     * @param id
     * @param name
     * @param ingredients
     * @param type
     */
    public MenuItem(String id, String name, List<Ingredient> ingredients, ItemType type) {
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
    public List<Ingredient> getIngredients() {
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

    public void addIngredient(Ingredient it) {
        ingredients.add(it);
    }

    public String ingredients() {
        String recipeText;
        recipeText = "[" + id + "(" + name + "):";
        for (Ingredient ing : ingredients) {
            recipeText += " " + ing.getName();
        }
        recipeText += "]";
        return recipeText;
    }
}