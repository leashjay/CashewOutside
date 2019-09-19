package seng202.team3.wrapper;

import seng202.team3.model.Ingredient;

import javax.xml.bind.annotation.XmlElement;

/**
 * Wrapper class for JAXB to load ingredients XML file
 * which contains Array of objects of type Ingredient
 */
public class Ingredients {

    @XmlElement(name = "ingredient")
    public Ingredient[] ingredients;
}
