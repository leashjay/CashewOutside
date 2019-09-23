package seng202.team3.model;

import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;

import javax.xml.bind.annotation.*;

/**
 * Class to represent ingredients. Mostly a data class, but that's the breaks...
 */


@XmlRootElement(name = "ingredient")
@XmlAccessorType(XmlAccessType.NONE)
public class Ingredient {

    /** A short name to use in menus and elsewhere */
    @XmlElement
    private String code;

    /** The full name */
    @XmlElement
    private String name;

    /**
     * Unit of measurement for ingredient
     */
    @XmlAttribute
    private UnitType unit;

    /** Quantity of ingredient in stock*/
    @XmlElement
    private float quantity;

    /** Information about vegan attribute */
    @XmlAttribute
    private ThreeValueLogic isVeg = ThreeValueLogic.UNKNOWN;

    /** Information about vegetarian attribute */
    @XmlAttribute
    private ThreeValueLogic isVegan = ThreeValueLogic.UNKNOWN;

    /** Information about gluten free attribute */
    @XmlAttribute(name = "isgf")
    private ThreeValueLogic isGF = ThreeValueLogic.UNKNOWN;

    /** Cost of ingredient */
    @XmlAttribute
    private float cost;


    /**
     * No-arg constructor for JAXB
     */
    public Ingredient() { }

    /**
     * Constructor for Ingredient class
     *
     * @param code the code of the ingredient
     * @param name the name of the ingredient
     * @param quantity quantity of ingredient in stock
     * @param unit the unit type of the ingredient (GRAM, ML, COUNT, UNKNOWN)
     * @param cost cost of ingredient
     */
    public Ingredient(String code, String name, float quantity, UnitType unit, float cost) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.cost = cost;
    }

    /**
     * Constructor for Ingredient class
     *
     * @param code    the code of the ingredient
     * @param name    the name of the ingredient
     * @param unit    the unit type of the ingredient (GRAM, ML, COUNT, UNKNOWN)
     * @param isVeg   ThreeTypeLogic deciding if the ingredient is vegetarian
     * @param isVegan ThreeTypeLogic deciding if the ingredient is vegan
     * @param isGF    ThreeTypeLogic deciding if the ingredient is gluten free
     */

    public Ingredient(String code, String name, UnitType unit, ThreeValueLogic isVeg, ThreeValueLogic isVegan,
                      ThreeValueLogic isGF, float cost) {
        this.code = code;
        this.name = name;
        this.unit = unit;
        this.isVeg = isVeg;
        this.isVegan = isVegan;
        this.isGF = isGF;
        this.cost = cost;
    }

    /**
     * Getter for ingredient's short name
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter for ingredient's long name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for ingredient's unit type
     * @return unit
     */
    public UnitType getUnit() {
        return unit;
    }

    /**
     * Getter for quantity of ingredient
     * @return quantity
     */
    public float getQuantity() {
        return quantity;
    }

    /**
     * Getter for ingredient's vegetarian attribute
     * @return isVeg
     */
    public ThreeValueLogic getIsVegetarian() {
        return isVeg;
    }

    /**
     * Getter for ingredient's vegan attribute
     * @return isVegan
     */
    public ThreeValueLogic getIsVegan() {
        return isVegan;
    }

    /**
     * Getter for ingredient's gluten free attribute
     * @return isGF
     */
    public ThreeValueLogic getIsGlutenFree() {
        return isGF;
    }

    /**
     * Getter for ingredient's cost attribute
     * @return cost
     */
    public float getCost() {
        return cost;
    }

    /**
     * Sets the name of the ingredient to the String given
     * @param newName the new name of the ingredient
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Sets the code for the ingredient to the String given
     * @param newCode the new code for the ingredient
     */
    public void setCode(String newCode) {
        code = newCode;
    }

    /**
     * Sets the unit type of the ingredient
     * @param newUnit the new unit type for the ingredient
     */
    public void setUnit(UnitType newUnit) {
        unit = newUnit;
    }

    public UnitType convertToUnit(String unit) {
        UnitType newUnit = UnitType.UNKNOWN;
        if (unit.toUpperCase() == "GRAM") {
            newUnit = UnitType.COUNT;
        } else if (unit.toUpperCase() == "ML") {
            newUnit = UnitType.COUNT;
        } else if (unit.toUpperCase() == "COUNT") {
            newUnit = UnitType.COUNT;
        }

        return newUnit;
    }

    /**
     * Sets whether the ingredient is vegetarian
     * @param newIsVeg an enum which stores whether an ingredient is vegetarian
     */
    public void setIsVeg(ThreeValueLogic newIsVeg) {
        isVeg = newIsVeg;
    }

    /**
     * Sets whether the ingredient is vegan
     * @param newIsVegan an enum which stores whether an ingredient is vegan
     */
    public void setIsVegan(ThreeValueLogic newIsVegan) {
        isVegan = newIsVegan;
    }

    /**
     * Sets whether the ingredient is gluten free
     * @param newIsGF an enum which stores whether an ingredient is gluten free
     */
    public void setIsGF(ThreeValueLogic newIsGF) {
        isGF = newIsGF;
    }

    /**
     * Sets the cost of the ingredient to the given float
     * @param newCost the new cost of the ingredient
     */
    public void setCost(float newCost) {
        Float calcCost = newCost;
        cost = calcCost;
    }

    public void setCost(float newCost, float oldQuantity) {
        Float calcCost = ((oldQuantity * newCost) + (getQuantity() * getCost())) / (getQuantity() + oldQuantity);
        cost = calcCost;
    }

    /**
     * Sets the quantity of the ingredient to the given float
     * @param newQuantity the new quantity of the ingredient
     */
    public void setQuantity(float newQuantity) {
        quantity = newQuantity;
    }

}