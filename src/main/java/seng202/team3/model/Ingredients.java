package seng202.team3.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Wrapper class for JAXB to load Ingredients.xml
 * which contains Array of objects of type Ingredient
 */
public class Ingredients {

    @XmlElement(name = "ingredient")
    public Ingredient[] ingredients;
}
