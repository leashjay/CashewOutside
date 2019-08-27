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

    @XmlElement(name = "id")
    private String id;

    @XmlElement(name = "name")
    private String name;

    // just the ingredient names
    @XmlElement(name = "ingredient")
    private List<String> ingredients;

    @XmlAttribute(name = "type")
    private ItemType type;


    public MenuItem() {
        ;
    }

    /**
     * Constructor for class.
     */
    public MenuItem(String id, String name, List<String> ingredients, ItemType type) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.type = type;
    }

    /**
     * Getter for menu item id
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for list of ingredients
     *
     * @return ingredients
     */
    public List<String> getIngredients() {
        return ingredients;
    }



    public void addIngredient(String it) {
        ingredients.add(it);
    }

    public String ingredients() {
        String recipeText;
        recipeText = "[" + id + "(" + name + "):";
        for(String s:ingredients) {
            recipeText += " " + s;
        }
        recipeText += "]";
        return recipeText;
    }
}