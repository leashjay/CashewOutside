package seng202.team3.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Holds information about the inventory
 */
@XmlRootElement(name = "ingredients")
@XmlAccessorType(XmlAccessType.FIELD)
public class Inventory {

    @XmlElement(name = "ingredient")
    public List<Ingredient> ingredients;
    @XmlElement(name = "description")
    private String desc;

    /**
     * A constructor for Inventory class
     */
    public Inventory() {
        super();
    }

    public String getDesc() {
        return desc;
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

