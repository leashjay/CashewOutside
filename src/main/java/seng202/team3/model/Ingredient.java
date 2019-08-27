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
    @XmlElement
    private String code;

    /** The full name */
    @XmlElement
    private String name;

    @XmlAttribute
    private UnitType unit;

    @XmlAttribute
    private String quantity;

    @XmlAttribute
    private ThreeValueLogic isVeg = ThreeValueLogic.UNKNOWN;

    @XmlAttribute
    private ThreeValueLogic isVegan = ThreeValueLogic.UNKNOWN;

    @XmlAttribute(name = "isgf")
    private ThreeValueLogic isGF = ThreeValueLogic.UNKNOWN;

    public Ingredient() {
    }

    public Ingredient(String code, String name, UnitType unit, ThreeValueLogic isVeg, ThreeValueLogic isVegan,
            ThreeValueLogic isGF) {
        this.code = code;
        this.name = name;
        this.unit = unit;
        this.isVeg = isVeg;
        this.isVegan = isVegan;
        this.isGF = isGF;
    }

    public String code() {
        return code;
    }

    public String name() {
        return name;
    }

    public UnitType unit() {
        return unit;
    }

    public String getQuantity() {
        return quantity;
    }

    public ThreeValueLogic isVeg() {
        return isVeg;
    }

    public ThreeValueLogic isVegan() {
        return isVegan;
    }

    public ThreeValueLogic isGF() {
        return isGF;
    }
}