package seng202.team3.model;

import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Class to represent ingredients. Mostly a data class, but that's the breaks...
 */

@XmlRootElement(name = "ingredient")
public class Ingredient {
    List<Supplier> suppliers;

    /** A short name to use in menus and elsewhere */
    @XmlElement(name = "code")
    private String code;

    /** The full name */
    @XmlElement(name = "name")
    private String name;

    /**
     * Ingredient measurement type
     */
    @XmlAttribute
    private UnitType unit;

    /** Quantity of ingredient */
    @XmlAttribute
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

    /**
     * Temporary constructor
     */
    public Ingredient() {
        ;
    }

    /**
     * Constructor for Ingredient class
     *
     * @param code
     * @param name
     * @param unit
     * @param isVeg
     * @param isVegan
     * @param isGF
     */
    public Ingredient(String code, String name, UnitType unit, ThreeValueLogic isVeg, ThreeValueLogic isVegan,
            ThreeValueLogic isGF) {
        this.code = code;
        this.name = name;
        this.unit = unit;
        this.isVeg = isVeg;
        this.isVegan = isVegan;
        this.isGF = isGF;
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
     *
     * @return quantity
     */
    public float getQuantity() {
        return quantity;
    }

    /**
     * Getter for ingredient's vegetarian attribute
     *
     * @return isVeg
     */
    public ThreeValueLogic getIsVeg() {
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
    public ThreeValueLogic getIsGF() {
        return isGF;
    }
}